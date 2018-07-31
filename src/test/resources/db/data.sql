DELETE FROM PUBLIC.UPDATE_STATUS;
DELETE FROM PUBLIC.TRANSACTION;
DELETE FROM PUBLIC.BLOCK;
ALTER SEQUENCE PUBLIC.block_seq RESTART WITH 1;
ALTER SEQUENCE PUBLIC.transaction_seq RESTART WITH 1;
ALTER SEQUENCE PUBLIC.update_status_seq RESTART WITH 1;

INSERT INTO PUBLIC.BLOCK(DB_ID, ID, VERSION, TIMESTAMP, PREVIOUS_BLOCK_ID, TOTAL_AMOUNT, TOTAL_FEE, PAYLOAD_LENGTH, PREVIOUS_BLOCK_HASH, CUMULATIVE_DIFFICULTY, BASE_TARGET, NEXT_BLOCK_ID, HEIGHT, GENERATION_SIGNATURE, BLOCK_SIGNATURE, PAYLOAD_HASH, GENERATOR_ID) VALUES
  (104830,	-468651855371775066,	3,	16758920,	9108206803338182346,	0,	100000000,	1255,	X'cabec48dd4d9667e562234245d06098f3f51f8dc9881d1959496fd73d7266282',	X'046543d9a8161629',	9331842,	-1868632362992335764,	104595,	X'002bc5d6612e35e00e0a8141382eab45c20243d9dad4823348bfe85147b95acf',	X'e920b526c9200ae5e9757049b3b16fcb050b416587b167cb9d5ca0dc71ec970df48c37ce310b6d20b9972951e9844fa817f0ff14399d9e0f82fde807d0957c31',	X'37f76b234414e64d33b71db739bd05d2cf3a1f7b344a88009b21c89143a00cd0',	9211698109297098287),
  (105015,	-7242168411665692630,	3,	16763026,	-3475222224033883190,	0,	100000000,	1257,	X'cadbeabccc87c5cf1cf7d2cf7782eb34a58fb2811c79e1d0a3cc60099557f4e0',	X'046601a7a1c313ca',	7069966,	5841487969085496907,	104671,	X'fbf795ff1d4138f11ea3d38842aa319f8a21589eb46ea8cfc71850f8b55508ef',	X'978b50eb629296b450f5298b61601685cbe965d4995b03707332fdc335a0e708a453bd7969bd9d336fbafcacd89073bf55c3b3395acf6dd0f3204c2a5d4b402e',	X'2cba9a6884de01ff23723887e565cbde21a3f5a0a70e276f3633645a97ed14c6',	9211698109297098287);

INSERT INTO PUBLIC.TRANSACTION(DB_ID, ID, DEADLINE, RECIPIENT_ID, TRANSACTION_INDEX, AMOUNT, FEE, FULL_HASH, HEIGHT, BLOCK_ID, SIGNATURE, TIMESTAMP, TYPE, SUBTYPE, SENDER_ID, BLOCK_TIMESTAMP, REFERENCED_TRANSACTION_FULL_HASH, PHASED, ATTACHMENT_BYTES, VERSION, HAS_MESSAGE, HAS_ENCRYPTED_MESSAGE, HAS_PUBLIC_KEY_ANNOUNCEMENT, EC_BLOCK_HEIGHT, EC_BLOCK_ID, HAS_ENCRYPTTOSELF_MESSAGE, HAS_PRUNABLE_MESSAGE, HAS_PRUNABLE_ENCRYPTED_MESSAGE, HAS_PRUNABLE_ATTACHMENT) VALUES
  (1481,	-1536976186224925700,	1440,	null,	0,	0,	100000000,	X'fc23d4474d90abeae5dd6d599381a75a2a06e61f91ff2249067a10e6515d202f',	104595,	-468651855371775066,X'61a224ae2d8198bfcee91c83e449d6325a2caa974f6a477ab59d0072b9b7e50793575534ab29c7be7d3dbef46f5e9e206d0bf5801bebf06847a28aa16c6419a1',	16758888,	8,	1,	9211698109297098287,	16758920,	null,	FALSE,	X'01054c494e5558035838360002a427a6d86645d0c32527e50fe292a0b1cf3983ef083f9fc392359e34d90012a65d5bd927c2cd09466433c107e523ff01bc00e414108d01e515f56ddbc054abce83fa4bd30bdf4623928e768536f8e56d9695ebadfbe34b5d1d59aa63545f5238a4817ec09389687df5ec116423b0e572a5ee9c47eaab432b19805a610beecb495595636a14009524caee8f1c73db084f1842bf895440233bff67c8f09674056113efd58da69f8411df3df174438bd2e8280e4eac97d6f89a6d756c1feddccc6d593d59578aab46ad9024b0ba742c547418ea7b2adbed80c8f673cd2cff31fefb6ab068c03232d79dfd83977a05bb0fb286f81ddbc0a9c75e6fce81747223a8fe5e506f9a9d7a7fd08d51b63ba25b4872886857b59607e24e842aa39e9d0d78a3db3ad97b03e64fb135ef55f5f396e29c8a4e146087b853f9a1be0a647201836da32ef5b0bff1a3bc599bff155cbfe8a24ad5ee7ab711bf9de7682876c8b7986025e68c8ee63f63505d3ec21f53a98e9de78f39b69c8438028a0e569f81c9ac7bc7d2dc0ea4f4406a696938fe422bad1076342267ee13d657aa9e68d07aafba6b33fc3e90d72ea5147bc21d223b862c56d989a568a7a2609b272261df3af318f340283490ff4d909768deee8987e363bba10c489d746e4e706daf02b78ba5886f59c204bc2237702d1c2191a6c6b0d3095c9c3d462e4e1cae02f0f53b5e94c2150002b51c553a2e69bc868926235c2fc01ba04b69070324a0c94d9c0d32f65ad4bb475c2b2887800caed2f4023f6510c363a5c4a7da0d8ba7cf85e921990fa7eba87c053ee753157c7541b291483a3f444b0e5d91dcb0f74def9dbe46c910546d0b616ebd9241e7f09aa619cb84b95560307d7e6b07e4fa47c508a621683717485542883203f1f17279b5e93173fa01b19bc707b1ee899bd1118322befed65b6eb28df579d56e61ca6b90abe5408f21544e3e6195ab23876baab07db967de04e815a9395987775acbe57bb7ac8d7366ad62a655bb4598edb4d3d2dce3d326fbeef97b654c686e9abd2c613ea740701a5a4d647e1ebf3bda0fc29fdbb5dfc7dc22842f32e552b0f999076d5f644809ff752224b71fe2f85ad8ac4766d57756d52953bbfb6e6b2134b173bf4995218429371ce3989cd764482396acb05eeaf2e138f38bae9107a9b6db626c6647be5d4a1e6f02f17326700ddeec0b8037671252f0e5c475e06964b6c5a5ff51bc07b494ee84ef5be7d84146f949fe6639409c3fe7550597e45c93ec276721781d9e8677fe4501b583a2b6d96d583c6397c8c5ef14ab6932581d81a8a3518da882fb920dd47c4af25ed755697a7cb181936ae0f21f3c2976f3168202e02fc4b351dcbb7f0c9e5b50a7f1f1d1841dd4de09ca374e3d01fc4fa6cb9271c727a194a2b701ec5e7d882790bb800cc2f86339ad708869ea291105312e302e382000a2c1e47afd4b25035a025091ec3c33ec1992d09e7f3c05875d79e660139220a4',	1,	FALSE,	FALSE,	FALSE,	103874,	1281949812948953897,	FALSE,	FALSE,	FALSE,	FALSE),
  (1539,	-4081443370478530685,	1440,	null,	0,	0,	100000000,	X'830fc103e1cc5bc7a3dd989ad0d7a8c66307a9ef23f05d2a18b661ee0a464088',	104671,	-7242168411665692630,	X'551f99bc4eceaae7c7007ac077ed163f4d95f8acc0119e38b726b5c8b494cf09c5059292de17efbc4ec14848e3944ecd0a5d0ca2591177266e04d426ce25a1c1',	16763004,	8,	0,	9211698109297098287,	16763026,	null,	FALSE,	X'01054c494e555805414d4436340002a427a6d86645d0c32527e50fe292a0b1cf3983ef083f9fc392359e34d90012a65d5bd927c2cd09466433c107e523ff01bc00e414108d01e515f56ddbc054abce83fa4bd30bdf4623928e768536f8e56d9695ebadfbe34b5d1d59aa63545f5238a4817ec09389687df5ec116423b0e572a5ee9c47eaab432b19805a610beecb495595636a14009524caee8f1c73db084f1842bf895440233bff67c8f09674056113efd58da69f8411df3df174438bd2e8280e4eac97d6f89a6d756c1feddccc6d593d59578aab46ad9024b0ba742c547418ea7b2adbed80c8f673cd2cff31fefb6ab068c03232d79dfd83977a05bb0fb286f81ddbc0a9c75e6fce81747223a8fe5e506f9a9d7a7fd08d51b63ba25b4872886857b59607e24e842aa39e9d0d78a3db3ad97b03e64fb135ef55f5f396e29c8a4e146087b853f9a1be0a647201836da32ef5b0bff1a3bc599bff155cbfe8a24ad5ee7ab711bf9de7682876c8b7986025e68c8ee63f63505d3ec21f53a98e9de78f39b69c8438028a0e569f81c9ac7bc7d2dc0ea4f4406a696938fe422bad1076342267ee13d657aa9e68d07aafba6b33fc3e90d72ea5147bc21d223b862c56d989a568a7a2609b272261df3af318f340283490ff4d909768deee8987e363bba10c489d746e4e706daf02b78ba5886f59c204bc2237702d1c2191a6c6b0d3095c9c3d462e4e1cae02f0f53b5e94c2150002b51c553a2e69bc868926235c2fc01ba04b69070324a0c94d9c0d32f65ad4bb475c2b2887800caed2f4023f6510c363a5c4a7da0d8ba7cf85e921990fa7eba87c053ee753157c7541b291483a3f444b0e5d91dcb0f74def9dbe46c910546d0b616ebd9241e7f09aa619cb84b95560307d7e6b07e4fa47c508a621683717485542883203f1f17279b5e93173fa01b19bc707b1ee899bd1118322befed65b6eb28df579d56e61ca6b90abe5408f21544e3e6195ab23876baab07db967de04e815a9395987775acbe57bb7ac8d7366ad62a655bb4598edb4d3d2dce3d326fbeef97b654c686e9abd2c613ea740701a5a4d647e1ebf3bda0fc29fdbb5dfc7dc22842f32e552b0f999076d5f644809ff752224b71fe2f85ad8ac4766d57756d52953bbfb6e6b2134b173bf4995218429371ce3989cd764482396acb05eeaf2e138f38bae9107a9b6db626c6647be5d4a1e6f02f17326700ddeec0b8037671252f0e5c475e06964b6c5a5ff51bc07b494ee84ef5be7d84146f949fe6639409c3fe7550597e45c93ec276721781d9e8677fe4501b583a2b6d96d583c6397c8c5ef14ab6932581d81a8a3518da882fb920dd47c4af25ed755697a7cb181936ae0f21f3c2976f3168202e02fc4b351dcbb7f0c9e5b50a7f1f1d1841dd4de09ca374e3d01fc4fa6cb9271c727a194a2b701ec5e7d882790bb800cc2f86339ad708869ea291105312e302e382000a2c1e47afd4b25035a025091ec3c33ec1992d09e7f3c05875d79e660139220a4',	1,	FALSE,	FALSE,	FALSE,	103950,	3234042379296483074,	FALSE,	FALSE,	FALSE,	FALSE)
;

INSERT INTO PUBLIC.UPDATE_STATUS (DB_ID, transaction_id, updated) VALUES (
    1, -1536976186224925700, TRUE
);