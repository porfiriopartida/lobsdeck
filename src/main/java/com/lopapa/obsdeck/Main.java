package com.lopapa.obsdeck;

import com.lopapa.obsdeck.jna.WindowListener;
import com.lopapa.obsdeck.transition.NameStrategy;
import com.lopapa.obsdeck.transition.SceneTransitionCommand;
import com.lopapa.obsdeck.transition.TransitionFacade;
import com.lopapa.obsdeck.utils.CSVUtil;
import com.lopapa.obsdeck.utils.ObsUtils;
import net.twasi.obsremotejava.Callback;
import net.twasi.obsremotejava.OBSRemoteController;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Main implements Observer {
    private TransitionFacade transitionFacade;
    private OBSRemoteController controller;
    public void start() throws IOException {
        controller = new OBSRemoteController("ws://localhost:4444", true);
        if (controller.isFailed()) {
            ObsUtils.status("Controller failed to connect.");
        }
        WindowListener windowListener = new WindowListener();
        windowListener.addObserver(this);

        transitionFacade = new TransitionFacade();

        //TODO: Allow profile, since this is my "Unity programming setup"
        /*
        SceneTransitionCommand unityScene = new SceneTransitionCommand();
        unityScene.setKey("Unity");
        unityScene.setScene("UnityScene");
        unityScene.setNameStrategy(NameStrategy.STARTS_WITH);

        SceneTransitionCommand vsCodeScene = new SceneTransitionCommand();
        vsCodeScene.setKey("Visual Studio Code");
        vsCodeScene.setScene("VisualStudioScene");
        vsCodeScene.setNameStrategy(NameStrategy.ENDS_WITH);

        SceneTransitionCommand gimpScene = new SceneTransitionCommand();
        gimpScene.setKey("GIMP");
        gimpScene.setScene("Gimp");
        gimpScene.setNameStrategy(NameStrategy.ENDS_WITH);

        SceneTransitionCommand intellijScene = new SceneTransitionCommand();
        intellijScene.setKey("IntelliJ IDEA");
        intellijScene.setNameStrategy(NameStrategy.ENDS_WITH);
        intellijScene.setScene("IntelliJ IDEA");

        SceneTransitionCommand consoleScene = new SceneTransitionCommand();
        consoleScene.setKey("GIMP");
        consoleScene.setScene("Desktop");
        consoleScene.setNameStrategy(NameStrategy.ENDS_WITH);


        SceneTransitionCommand photoScene = new SceneTransitionCommand();
        photoScene.setKey("Photos");
        photoScene.setScene("BackgroundImage");
        photoScene.setNameStrategy(NameStrategy.STARTS_WITH);


        transitionFacade.addTransitionCommand(gimpScene);
        transitionFacade.addTransitionCommand(intellijScene);
        transitionFacade.addTransitionCommand(unityScene);
        transitionFacade.addTransitionCommand(vsCodeScene);
        transitionFacade.addTransitionCommand(photoScene);

        transitionFacade.save();
        */


        List<SceneTransitionCommand> sceneTransitionCommands = CSVUtil.fromCsv("D:\\Java\\workspace\\lobsdeck\\myFile.txt");
        transitionFacade.addTransitionCommands(sceneTransitionCommands);

        new Thread(windowListener).start();
    }
    public static void main(String[] args) throws InterruptedException, IOException {
        new Main().start();
    }

    @Override
    public void update(Observable o, Object arg) {
        String windowName = arg.toString();
        String newSceneName = transitionFacade.getSceneNameByKey(windowName);
        if(newSceneName != null){
            try {
                //controller.setPreviewScene("LeagueQueue", callback);
                controller.setCurrentScene(newSceneName, callback);
                ObsUtils.status("Selected OBS scene: %s", newSceneName);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    Callback callback = response -> {
    };
}
