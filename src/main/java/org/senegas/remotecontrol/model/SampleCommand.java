package org.senegas.remotecontrol.model;

import org.senegas.remotecontrol.ardw.CommandQueue;

import java.util.Arrays;

public class SampleCommand implements Command {
    private final String message;

    public SampleCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        System.out.println(message);

        // For now let's retrieve tne enum instance like this to avoid too much other code modification.
        var opt = Arrays.stream(RemoteButton.values()).filter((b) -> b.getCommand()==this).findFirst();
        if (opt.isEmpty()) {
            throw new RuntimeException("Unexpected message: "+message);
        }

        // The button has been found. Let's put it in the queue.
        // On the other side of the queue the ArdwProgram will retrieve the button and process it.
        RemoteButton button = opt.get();
        CommandQueue.get().addCommand(button);
    }
}
