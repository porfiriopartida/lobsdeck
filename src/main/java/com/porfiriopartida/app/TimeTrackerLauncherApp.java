package com.porfiriopartida.app;

import com.porfiriopartida.exception.ConfigurationValidationException;
import com.porfiriopartida.timetracker.WindowTimeTracker;
import org.junit.platform.commons.util.StringUtils;

import java.io.File;
import java.io.IOException;

public class TimeTrackerLauncherApp {

    public static void main(String[] args) throws IOException, ConfigurationValidationException {
        String externalConfigFile = null;


        for (int i = 0; i < args.length; i++) {
            if("--file".equals(args[i])){
                externalConfigFile = args[i + 1];
            }
        }
        WindowTimeTracker windowTimeTracker = new WindowTimeTracker();

        if (!StringUtils.isBlank(externalConfigFile) && new File(externalConfigFile).exists()) {
            windowTimeTracker.setConfigFile(externalConfigFile);
        } else {
            System.err.println("Invalid file given. Usage: --file path/to/config/file.txt");
            System.exit(1);
        }

        windowTimeTracker.start();
    }
}
