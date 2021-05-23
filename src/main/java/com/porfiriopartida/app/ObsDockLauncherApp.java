package com.porfiriopartida.app;

import com.porfiriopartida.obsdeck.ObsDockLauncher;
import com.porfiriopartida.exception.ConfigurationValidationException;
import org.junit.platform.commons.util.StringUtils;

import java.io.File;
import java.io.IOException;

public class ObsDockLauncherApp {

    public static void main(String[] args) throws IOException, ConfigurationValidationException {
        String externalConfigFile = null;


        for (int i = 0; i < args.length; i++) {
            if("--file".equals(args[i])){
                externalConfigFile = args[i + 1];
            }
        }
        ObsDockLauncher obsDockLauncher = new ObsDockLauncher();

        if (!StringUtils.isBlank(externalConfigFile) && new File(externalConfigFile).exists()) {
            obsDockLauncher.setConfigFile(externalConfigFile);
        } else {
            System.err.println("Invalid file given. Usage: --file path/to/config/file.txt");
            System.exit(1);
        }

        obsDockLauncher.start();
    }
}
