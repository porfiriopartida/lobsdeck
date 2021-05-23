package com.porfiriopartida.screen.config;

import java.io.Serializable;

public class ScreenDetectionCommand implements Serializable {
    private NameStrategy nameStrategy;
    private String key;
    private String command;

    public String toString(){
        return key + "," + nameStrategy.toString() + "," + command;
    }

    public NameStrategy getNameStrategy() {
        return nameStrategy;
    }

    public void setNameStrategy(NameStrategy nameStrategy) {
        this.nameStrategy = nameStrategy;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
