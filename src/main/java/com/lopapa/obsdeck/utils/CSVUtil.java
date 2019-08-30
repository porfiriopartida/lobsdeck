package com.lopapa.obsdeck.utils;

import com.lopapa.obsdeck.transition.NameStrategy;
import com.lopapa.obsdeck.transition.SceneTransitionCommand;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
    public static List<SceneTransitionCommand> fromCsv(String pathToCsv) throws IOException {
        return CSVUtil.fromCsv(pathToCsv, true);
    }
    public static List<SceneTransitionCommand> fromCsv(String pathToCsv, boolean hasHeaders) throws IOException {
        List<SceneTransitionCommand> sceneTransitionCommands = new ArrayList<SceneTransitionCommand>();
        File csvFile = new File(pathToCsv);
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
                    String[] data = row.split(",");
                    SceneTransitionCommand sceneTransitionCommand = new SceneTransitionCommand();
                    sceneTransitionCommand.setKey(data[0]);
                    sceneTransitionCommand.setScene(data[1]);
                    sceneTransitionCommand.setNameStrategy(NameStrategy.valueOf(data[2]));
                    sceneTransitionCommands.add(sceneTransitionCommand);
                    System.out.println(sceneTransitionCommand);
                }
            } finally {
                if(csvReader != null){
                    csvReader.close();
                }
            }
        } else {
            throw new FileNotFoundException(String.format("Cannot find csv to read from: %s", pathToCsv));
        }
        return sceneTransitionCommands;
    }
    public static void toCsv(List<SceneTransitionCommand> sceneTransitionCommandList, String pathToCsv) throws IOException {
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
                csvWriter.append("Scene");
                csvWriter.append(",");
                csvWriter.append("Validation");
                csvWriter.append("\n");

                for (SceneTransitionCommand sceneTransitionCommand : sceneTransitionCommandList) {
                    csvWriter.append(sceneTransitionCommand.toString());
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
