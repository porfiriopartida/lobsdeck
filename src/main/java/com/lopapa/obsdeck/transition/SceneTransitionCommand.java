package com.lopapa.obsdeck.transition;

import java.io.Serializable;

public class SceneTransitionCommand implements Serializable {
    private NameStrategy nameStrategy;
    private String key;
    private String scene;

    public String toString(){
        return key +","+scene+","+nameStrategy.toString();
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

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }
}
