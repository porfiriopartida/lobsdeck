package com.porfiriopartida.screen.utils;

import com.porfiriopartida.exception.ConfigurationValidationException;
import com.porfiriopartida.screen.config.NameStrategy;
import com.porfiriopartida.screen.config.ScreenDetectionCommand;
import com.porfiriopartida.obsdeck.utils.ObsUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
    public static List<ScreenDetectionCommand> fromCsv(String pathToCsv) throws IOException, ConfigurationValidationException {
        return CSVUtil.fromCsv(pathToCsv, true);
    }
    public static List<ScreenDetectionCommand> fromCsv(String pathToCsv, boolean hasHeaders) throws IOException, ConfigurationValidationException {
        List<ScreenDetectionCommand> screenDetectionCommands = new ArrayList<ScreenDetectionCommand>();
        File csvFile = new File(pathToCsv);
        int currentRow = 0;
        if (csvFile.isFile()) {
            BufferedReader csvReader = null;
            try {
                FileReader fileReader = new FileReader(csvFile.getAbsoluteFile());
                csvReader = new BufferedReader(fileReader);
                String row;
                if(hasHeaders){
                    String headers = csvReader.readLine();
                    ObsUtils.status(headers);
                }
                while ((row = csvReader.readLine()) != null) {
                    ++currentRow;
                    String[] data = row.split(",");
                    ScreenDetectionCommand screenDetectionCommand = new ScreenDetectionCommand();

                    if(data.length > 1){
                        screenDetectionCommand.setKey(data[0]);
                        screenDetectionCommand.setNameStrategy(NameStrategy.valueOf(data[1]));
                    } else {
                        StringBuilder exceptionMessage = new StringBuilder();
                        exceptionMessage.append("Expected 'Window Title','STRATEGY (CONTAINS|STARTS_WITH)'");
                        exceptionMessage.append("\n");
                        exceptionMessage.append(String.format("Line: %s", currentRow));
                        exceptionMessage.append("\n");
                        exceptionMessage.append(String.format("Row: %s", row));
                        throw new ConfigurationValidationException(exceptionMessage.toString());
                    }
                    if(data.length > 2){
                        screenDetectionCommand.setCommand(data[2]);
                    }
                    screenDetectionCommands.add(screenDetectionCommand);
                    System.out.println(screenDetectionCommand);
                }
            } finally {
                if(csvReader != null){
                    csvReader.close();
                }
            }
        } else {
            throw new FileNotFoundException(String.format("Cannot find csv to read from: %s", pathToCsv));
        }
        return screenDetectionCommands;
    }
    public static void toCsv(List<ScreenDetectionCommand> screenDetectionCommandList, String pathToCsv) throws IOException {
        File csvFile = new File(pathToCsv);
        if (csvFile.isFile()) {
            csvFile.delete();
        }
        if(csvFile.createNewFile()){
            ObsUtils.status(csvFile.getAbsolutePath());
            FileWriter csvWriter = null;
            try {

                csvWriter = new FileWriter(csvFile.getAbsoluteFile());
                csvWriter.append("Key");
                csvWriter.append(",");
                csvWriter.append("Validation");
                csvWriter.append(",");
                csvWriter.append("Command");
                csvWriter.append("\n");

                for (ScreenDetectionCommand screenDetectionCommand : screenDetectionCommandList) {
                    csvWriter.append(screenDetectionCommand.toString());
                    csvWriter.append("\n");
                }
                csvWriter.flush();
            }finally {
                if(csvWriter != null){
                    csvWriter.close();
                }
            }
        }
    }
}
