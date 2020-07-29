package com.lopapa.obsdeck.transition;

import com.lopapa.obsdeck.utils.CSVUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransitionFacade {
    List<SceneTransitionCommand> sceneTransitionCommands;
    public TransitionFacade(){
        sceneTransitionCommands = new ArrayList<>();
    }
    public void addTransitionCommand(SceneTransitionCommand sceneTransitionCommand){
        if(validate(sceneTransitionCommand)){
            this.sceneTransitionCommands.add(sceneTransitionCommand);
        }
    }
    public void addTransitionCommands(List<SceneTransitionCommand> sceneTransitionCommands){
        for(SceneTransitionCommand sceneTransitionCommand : sceneTransitionCommands){
            addTransitionCommand(sceneTransitionCommand);
        }
    }
    public void save() throws IOException {
        //CSVUtil.toCsv(this.sceneTransitionCommands, "myFile.txt");
    }

    private boolean validate(SceneTransitionCommand sceneTransitionCommand) {
        if(sceneTransitionCommand == null){
            return false;
        }
        if(sceneTransitionCommand.getScene() == null){
            return false;
        }
        if(sceneTransitionCommand.getNameStrategy() == null){
            return false;
        }
        if(sceneTransitionCommand.getKey() == null){
            return false;
        }
        if(sceneTransitionCommand.getScene().length() == 0){
            return false;
        }
        if(sceneTransitionCommand.getKey().length() == 0){
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
        for(SceneTransitionCommand command : sceneTransitionCommands){
            if(command == null){continue;}
            switch(command.getNameStrategy()){
                case CONTAINS:
                    if(key.contains(command.getKey())){
                        return command.getScene();
                    }
                    break;
                case ENDS_WITH:
                    if(key.endsWith(command.getKey())){
                        return command.getScene();
                    }
                    break;
                case STARTS_WITH:
                    if(key.startsWith(command.getKey())){
                        return command.getScene();
                    }
                    break;
                case EQUALS:
                    if(key.equals(command.getKey())){
                        return command.getScene();
                    }
                    break;
                case EQUALS_IGNORE_CASE:
                    if(key.equalsIgnoreCase(command.getKey())){
                        return command.getScene();
                    }
                    break;
            }
        }
        return null;
    }
}
