package com.apollocurrency.aplwallet.apl.util.env;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Loads properties from different places. Please do not use logger here, it is
 * not ready yet.
 *
 * @author alukin@gmail.com
 */
public class PropertiesLoader {

    public static final String DEFAULT_APL_DEFAULT_PROPERTIES_FILE_NAME = "apl-default.properties";
    public static final String DEFAULT_APL_PROPERTIES_FILE_NAME = "apl.properties";
    public static final String DEFAULT_APL_INSTALLER_PROPERTIES_FILE_NAME = "apl-installer.properties";
    public static final String DEFAULT_CONFIG_DIR = "conf";

    private String[] propFileNames = {DEFAULT_APL_DEFAULT_PROPERTIES_FILE_NAME,
        DEFAULT_APL_PROPERTIES_FILE_NAME,
        DEFAULT_APL_PROPERTIES_FILE_NAME
    };
    private String configDir = "";
    private boolean ignoreResources = false;

    private final DirProvider dirProvider;
    private final Properties properties = new Properties();

    public PropertiesLoader(DirProvider dirProvider, boolean ignoreResources, String configDir) {
        this.dirProvider = dirProvider;
        if(configDir!=null){
          this.configDir = configDir;
        }
        this.ignoreResources = ignoreResources;
        init();
    }

    private void init() {
        if (!ignoreResources) {
            loadResources();
        }
        loadProperties(propFileNames);
        copyResources(dirProvider.getUserConfigDirectory());
    }

    public void copyResources(String destDir) {
        File d = new File(destDir);
        if (d.exists()) {
            System.err.println("Can not copy to "+destDir+". Directory already exists");
        } else {
            d.mkdirs();
            for (String n : propFileNames) {
                String fn = DEFAULT_CONFIG_DIR +"/"+ n;
                try (InputStream is = ClassLoader.getSystemResourceAsStream(fn)) {
                    FileOutputStream os = new FileOutputStream(destDir + "/" + n);
                    byte[] buf = new byte[4096];
                    int sz;
                    while ((sz = is.read(buf, 0, 4096)) > 0) {
                        os.write(buf, 0, sz);
                    }
                    os.close();
                } catch (Exception e) {
                    System.err.println("Can not find resource: " + fn);
                }
            }
        }
    }

    public void loadResources() {

        Properties p = new Properties();
        for (String n : propFileNames) {
            String fn = DEFAULT_CONFIG_DIR +"/"+ n;
            try (InputStream is = ClassLoader.getSystemResourceAsStream(fn)) {
                p.clear();
                p.load(is);
                properties.putAll(p);
            } catch (IOException e) {
                System.err.println("Can not find resource: " + fn);
            }
        }
    }

    private void loadProperties(String[] propertiesFiles) {
        List<String> searchDirs = new ArrayList<>();
        if (configDir.isEmpty()) { //load just from confDir
            searchDirs.add(configDir);
        } else { //go trough standard search order and load all
            searchDirs.add(dirProvider.getBinDirectory() + "/conf");
            searchDirs.add(dirProvider.getSysConfigDirectory());
            searchDirs.add(dirProvider.getUserConfigDirectory());
        }
        for (String d : searchDirs) {
            for (String f : propertiesFiles) {
                String p = d + "/" + f;
                try (FileInputStream is = new FileInputStream(p)) {
                    Properties prop = new Properties();
                    prop.load(is);
                    properties.putAll(prop);
                } catch (Exception ex) {
                }
            }
        }
    }

    public void loadSystemProperties(List<String> propertiesNamesList) {
        propertiesNamesList.forEach(propertyName -> {
            String propertyValue;
            if ((propertyValue = System.getProperty(propertyName)) != null) {
                Object oldPropertyValue = properties.setProperty(propertyName, propertyValue);
                if (oldPropertyValue == null) {
                    System.out.printf("System property set: {} -{}", propertyName, propertyValue);
                } else {
                    System.out.printf("Replace property {} - {} by new system property: {}", propertyName, oldPropertyValue, propertyValue);
                }
            } else {
                System.err.printf("System property {} not defined", propertyName);
            }
        });
    }

    public Properties getProperties() {
        return properties;
    }

}