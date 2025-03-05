package org.senegas.remotecontrol.ardw;

import org.senegas.remotecontrol.model.RemoteButton;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CommandQueue {

    private static CommandQueue INSTANCE = new CommandQueue();

    public static CommandQueue get() {
        return INSTANCE;
    }

    private CommandQueue() {
        // hide constructor
    }

    private ConcurrentLinkedQueue<RemoteButton> commands = new ConcurrentLinkedQueue<>();

    public void addCommand(RemoteButton button) {
        commands.add(button);
    }

    public boolean isEmpty() {
        return commands.isEmpty();
    }

    public RemoteButton retrieveCommand() {
        return commands.remove();
    }

}
