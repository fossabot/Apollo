/*
 * Copyright © 2013-2016 The Nxt Core Developers.
 * Copyright © 2016-2017 Jelurida IP B.V.
 *
 * See the LICENSE.txt file at the top-level directory of this distribution
 * for licensing information.
 *
 * Unless otherwise agreed in a custom licensing agreement with Jelurida B.V.,
 * no part of the Nxt software, including this file, may be copied, modified,
 * propagated, or distributed except according to the terms contained in the
 * LICENSE.txt file.
 *
 * Removal or modification of this copyright notice is prohibited.
 *
 */

/*
 * Copyright © 2018 Apollo Foundation
 */

package com.apollocurrency.aplwallet.apl.util.env;


import java.io.File;
import java.nio.file.Paths;

public class WindowsUserDirProvider extends DirProvider {

    private static final String APL_USER_HOME = Paths.get(System.getProperty("user.home"), "AppData", "Roaming", "APOLLO").toString();

    public WindowsUserDirProvider(boolean isServiceMode) {
        super(isServiceMode);
    }
            //Apl.APPLICATION.toUpperCase()).toString();
//TODO: User's home should be user's home, and nothing else
    @Override
    public String getAppHomeDir() {
        return APL_USER_HOME;
    }

    @Override
    public File getLogFileDir() {
        return super.getLogFileDir();
    }
}