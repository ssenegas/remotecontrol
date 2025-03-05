package org.senegas.remotecontrol.ardw;

import org.senegas.remotecontrol.model.RemoteButton;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CommandQueue {

    private static final CommandQueue INSTANCE = new CommandQueue();

    public static CommandQueue get() {
        return INSTANCE;
    }

    private CommandQueue() {
        // hide constructor
    }

    /* This is the queue between the actions collected by the GUI and the Ardwloop backend
     * # Each time the User presses a button, this queue is fed with one entry
     * # The queue is polled by the Ardwloop program, which does process the button commands it fins inside
     */
    private final ConcurrentLinkedQueue<RemoteButton> commands = new ConcurrentLinkedQueue<>();

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
