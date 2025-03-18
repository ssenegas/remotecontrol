package org.senegas.remotecontrol.remote;

import org.senegas.remotecontrol.model.RemoteControlCommand;

import java.util.concurrent.ConcurrentLinkedQueue;

public enum CommandQueue {
    INSTANCE;

    private final ConcurrentLinkedQueue<RemoteControlCommand> remoteControlCommands = new ConcurrentLinkedQueue<>();

    public void addCommand(RemoteControlCommand remoteControlCommand) {
        if (remoteControlCommand != null) {
            remoteControlCommands.add(remoteControlCommand);
        }
    }

    public boolean isEmpty() {
        return remoteControlCommands.isEmpty();
    }

    public RemoteControlCommand retrieveCommand() {
        return remoteControlCommands.poll();
    }
}

