package org.senegas.remotecontrol.model;

public interface RemoteControlCommand {
    void execute();
    RemoteControlButton getButton();
}
