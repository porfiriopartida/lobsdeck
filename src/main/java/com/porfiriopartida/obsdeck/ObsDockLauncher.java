package com.porfiriopartida.obsdeck;

import com.porfiriopartida.exception.ConfigurationValidationException;
import com.porfiriopartida.obsdeck.utils.ObsUtils;
import com.porfiriopartida.screen.application.ScreenApplication;
import net.twasi.obsremotejava.Callback;
import net.twasi.obsremotejava.OBSRemoteController;

import java.io.IOException;
import java.util.Observable;

public class ObsDockLauncher extends ScreenApplication {
    private OBSRemoteController controller;
    private Callback callback = response -> {};

    public void start() throws IOException, ConfigurationValidationException {
        controller = new OBSRemoteController("ws://localhost:4444", false);
        if (controller.isFailed()) {
            ObsUtils.status("Controller failed to connect.");
        }
        super.start();
    }

    @Override
    public void update(Observable observable, Object arg) {
        String windowName = arg.toString();
        ObsUtils.status("windowName: %s", windowName);
        String newSceneName = getCommand(windowName);
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
}
