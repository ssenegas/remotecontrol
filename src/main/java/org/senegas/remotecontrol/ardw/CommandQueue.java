package org.senegas.remotecontrol.ardw;

import org.senegas.remotecontrol.model.Command;

import java.util.concurrent.ConcurrentLinkedQueue;

public enum CommandQueue {
    INSTANCE;

    private final ConcurrentLinkedQueue<Command> commands = new ConcurrentLinkedQueue<>();

    public void addCommand(Command command) {
        if (command != null) {
            commands.add(command);
        }
    }

    public boolean isEmpty() {
        return commands.isEmpty();
    }

    public Command retrieveCommand() {
        return commands.poll();
    }
}

