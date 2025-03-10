package org.senegas.remotecontrol.view;

import org.senegas.remotecontrol.model.RemoteControlButton;

import java.util.logging.Logger;

public class RemoteControlController {
    private static final Logger LOGGER = Logger.getLogger(RemoteControlController.class.getName());

    public void buttonPressed(RemoteControlButton button) {
        button.getCommand().execute();
    }
}
