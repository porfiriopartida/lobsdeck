package com.porfiriopartida.obsdeck.transition;

import com.porfiriopartida.screen.config.ScreenDetectionCommand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransitionFacade {
    List<ScreenDetectionCommand> screenDetectionCommands;
    public TransitionFacade(){
        screenDetectionCommands = new ArrayList<>();
    }
    public void addTransitionCommand(ScreenDetectionCommand screenDetectionCommand){
        if(validate(screenDetectionCommand)){
            this.screenDetectionCommands.add(screenDetectionCommand);
        }
    }
    public void addTransitionCommands(List<ScreenDetectionCommand> screenDetectionCommands){
        for(ScreenDetectionCommand screenDetectionCommand : screenDetectionCommands){
            addTransitionCommand(screenDetectionCommand);
        }
    }
    public void save() throws IOException {
        //CSVUtil.toCsv(this.sceneTransitionCommands, "myFile.txt");
    }

    private boolean validate(ScreenDetectionCommand screenDetectionCommand) {
        if(screenDetectionCommand == null){
            return false;
        }
        if(screenDetectionCommand.getCommand() == null){
            return false;
        }
        if(screenDetectionCommand.getNameStrategy() == null){
            return false;
        }
        if(screenDetectionCommand.getKey() == null){
            return false;
        }
        if(screenDetectionCommand.getCommand().length() == 0){
            return false;
        }
        if(screenDetectionCommand.getKey().length() == 0){
            return false;
        }
        return true;
    }

    public String getSceneNameByKey(String key){
        //TODO: Implement some sort of caching.
        if(key == null || key.length() == 0){
            return null;
        }
        key = key.trim();
        for(ScreenDetectionCommand command : screenDetectionCommands){
            if(command == null){continue;}
            switch(command.getNameStrategy()){
                case CONTAINS:
                    if(key.contains(command.getKey())){
                        return command.getCommand();
                    }
                    break;
                case ENDS_WITH:
                    if(key.endsWith(command.getKey())){
                        return command.getCommand();
                    }
                    break;
                case STARTS_WITH:
                    if(key.startsWith(command.getKey())){
                        return command.getCommand();
                    }
                    break;
                case EQUALS:
                    if(key.equals(command.getKey())){
                        return command.getCommand();
                    }
                    break;
                case EQUALS_IGNORE_CASE:
                    if(key.equalsIgnoreCase(command.getKey())){
                        return command.getCommand();
                    }
                    break;
            }
        }
        return null;
    }
}
