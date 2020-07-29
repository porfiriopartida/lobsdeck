package com.lopapa.obsdeck;

import com.lopapa.obsdeck.jna.WindowListener;
import com.lopapa.obsdeck.transition.SceneTransitionCommand;
import com.lopapa.obsdeck.transition.TransitionFacade;
import com.lopapa.obsdeck.utils.CSVUtil;
import com.lopapa.obsdeck.utils.ObsUtils;
import net.twasi.obsremotejava.Callback;
import net.twasi.obsremotejava.OBSRemoteController;
import org.junit.platform.commons.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ObsDockLauncher implements Observer {
    private TransitionFacade transitionFacade;
    private OBSRemoteController controller;


    public String configFile = "D:\\Java\\workspace\\lobsdeck\\myFile.txt";

    public void start() throws IOException {
        controller = new OBSRemoteController("ws://localhost:4444", false);
        if (controller.isFailed()) {
            ObsUtils.status("Controller failed to connect.");
        }
        WindowListener windowListener = new WindowListener();
        windowListener.addObserver(this);

        transitionFacade = new TransitionFacade();

        List<SceneTransitionCommand> sceneTransitionCommands = CSVUtil.fromCsv(configFile);
        transitionFacade.addTransitionCommands(sceneTransitionCommands);

        new Thread(windowListener).start();
    }
    public static void main(String[] args) throws InterruptedException, IOException {
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

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }
    @Override
    public void update(Observable o, Object arg) {
        String windowName = arg.toString();
        ObsUtils.status("windowName: %s", windowName);
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
