package org.senegas.remotecontrol.view;

import org.senegas.remotecontrol.model.Command;
import org.senegas.remotecontrol.model.RemoteButton;

import java.util.logging.Logger;

import java.util.HashMap;
import java.util.Map;

public class RemoteController {
    private static final Logger LOGGER = Logger.getLogger(RemoteController.class.getName());

    public void buttonPressed(RemoteButton button) {
        button.getCommand().execute();
    }
}
