/*
 * Copyright © 2018 Apollo Foundation
 */

package com.apollocurrency.aplwallet.apl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.apollocurrency.aplwallet.apl.crypto.Crypto;
import com.apollocurrency.aplwallet.apl.util.Convert;
import com.apollocurrency.aplwallet.apl.util.JSON;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
public class KeyStoreTest {
    private static final String PASSPHRASE = "random passphrase generated by passphrase generator";
    private static final String ACCOUNT1 = "APL-299N-Y6F7-TZ8A-GYAB8";
    private static final String ACCOUNT2 = "APL-Z6D2-YTAB-L6BV-AAEAY";
    private static final String encryptedKeySeedJSON =
            "{\n" +
                    "  \"encryptedSecretBytes\" : \"8qWMzLfNJt4wT0q2n7YuyMouj08hbfzx9z9HuIBZf2tGHqajPXfHpwzV6EwKYTWMDa2j3copDxujx2SLmFXwdA==\",\n" +
                    "  \"accountRS\" : \"APL-299N-Y6F7-TZ8A-GYAB8\",\n" +
                    "  \"account\" : -2079221632084206348,\n" +
                    "  \"size\" : 32,\n" +
                    "  \"version\" : 0,\n" +
                    "  \"nonce\" : \"PET2LeUQDMfgrCIvM0j0tA==\",\n" +
                    "  \"timestamp\" : 1539036932840\n" +
                    "}";
    private static final String SECRET_BYTES_1 = "44a2868161a651682bdf938b16c485f359443a2c53bd3e752046edef20d11567";
    private static final String SECRET_BYTES_2 = "146c55cbdc5f33390d207d6d08030c3dd4012c3f775ed700937a893786393dbf";
    private byte[] secretBytes = generateSecretBytes();
    private byte[] nonce = new byte[16];

    private byte[] generateSecretBytes() {
        byte secretBytes[] = new byte[32];
        Random random = new Random();
        random.nextBytes(secretBytes);
        return secretBytes;
    }

    private Path tempDirectory;
    private SimpleKeyStoreImpl keyStore;

    @Before
    public void setUp() throws Exception {
//        Crypto.getSecureRandom().nextBytes(nonce);
        tempDirectory = Files.createTempDirectory("keystore-test");
        keyStore = new SimpleKeyStoreImpl(tempDirectory, (byte) 0);
        Files.write(tempDirectory.resolve("---" + ACCOUNT1), encryptedKeySeedJSON.getBytes());
    }

    @After
    public void tearDown() throws Exception {
        Files.list(tempDirectory).forEach(tempFilePath -> {
            try {
                Files.delete(tempFilePath);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Files.delete(tempDirectory);
    }

    @Test
    public void testSaveKeySeed() throws Exception {
        SimpleKeyStoreImpl keyStoreSpy = spy(keyStore);

        keyStoreSpy.saveSecretBytes(PASSPHRASE, Convert.parseHexString(SECRET_BYTES_2));

        verify(keyStoreSpy, times(1)).storeJSONSecretBytes(any(Path.class), any(EncryptedSecretBytesDetails.class));
        verify(keyStoreSpy, times(1)).findSecretPaths(anyLong());

        Assert.assertEquals(2, Files.list(tempDirectory).count());

        String rsAcc = Convert.rsAccount(Convert.getId(Crypto.getPublicKey(Crypto.getKeySeed(Convert.parseHexString(SECRET_BYTES_2)))));

        Path encryptedKeyPath =
                Files.list(tempDirectory).filter(path -> path.getFileName().toString().endsWith(rsAcc)).findFirst().orElseThrow(()->new RuntimeException("No encrypted key found for " + rsAcc  + " account"));

        EncryptedSecretBytesDetails keySeedDetails = JSON.getMapper().readValue(encryptedKeyPath.toFile(), EncryptedSecretBytesDetails.class);

        byte[] actualKeySeed = Crypto.aesDecrypt(keySeedDetails.getEncryptedSecretBytes(), Crypto.getKeySeed(PASSPHRASE,
                keySeedDetails.getNonce(), Convert.longToBytes(keySeedDetails.getTimestamp())));

        Assert.assertEquals(SECRET_BYTES_2, Convert.toHexString(actualKeySeed));
        Assert.assertEquals(ACCOUNT2, rsAcc);
    }


    @Test
    public void testGetKeySeed() throws Exception {


        SimpleKeyStoreImpl keyStoreSpy = spy(keyStore);

        long accountId = Convert.parseAccountId(ACCOUNT1);
        byte[] actualKeySeed = keyStoreSpy.getKeySeed(PASSPHRASE, accountId);
        String rsAcc = Convert.rsAccount(accountId);

        verify(keyStoreSpy, times(1)).verifyExistOnlyOne(any(List.class), eq(accountId));
        verify(keyStoreSpy, times(1)).findSecretPaths(accountId);

        Assert.assertEquals(1, Files.list(tempDirectory).count());
        Path encryptedKeyPath = Files.list(tempDirectory).findFirst().get();
        Assert.assertTrue(encryptedKeyPath.getFileName().toString().endsWith(rsAcc));

        Assert.assertEquals(SECRET_BYTES_1, Convert.toHexString(actualKeySeed));

    }
    @Test(expected = SecurityException.class)
    public void testGetKeySeedUsingIncorrectPassphrase() throws Exception {
        long accountId = Convert.parseAccountId(ACCOUNT1);
        keyStore.getKeySeed("pass", accountId);

    }
    @Test(expected = RuntimeException.class)
    public void testGetKeySeedUsingIncorrectAccount() throws Exception {
        long accountId = 0;
        keyStore.getKeySeed(PASSPHRASE, accountId);
    }

    @Test(expected = RuntimeException.class)
    public void testSaveDuplicateKeySeed() throws IOException {
        keyStore.saveSecretBytes(PASSPHRASE, Convert.parseHexString(SECRET_BYTES_1));
    }


}

