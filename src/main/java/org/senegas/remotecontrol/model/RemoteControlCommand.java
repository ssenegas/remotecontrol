package org.senegas.remotecontrol.model;

import org.senegas.remotecontrol.ardw.CommandQueue;

public class RemoteControlCommand implements Command {
    private final RemoteControlButton button;

    public RemoteControlCommand(RemoteControlButton button) {
        this.button = button;
    }

    @Override
    public void execute() {
        System.out.println(button.getDisplayName() + " executed");

        // Let's put it in the queue.
        // On the other side of the queue the ArdwProgram will retrieve the button and process it.
        CommandQueue queue = CommandQueue.INSTANCE;
        queue.addCommand(this);
    }

    @Override
    public RemoteControlButton getButton() {
        return button;
    }
}

