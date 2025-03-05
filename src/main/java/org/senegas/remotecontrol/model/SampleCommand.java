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

        // no better way to retrieve the command for now
        var opt = Arrays.stream(RemoteButton.values()).filter((b) -> b.getCommand()==this).findFirst();
        if (opt.isEmpty()) {
            throw new RuntimeException("Unexpected message: "+message);
        }
        RemoteButton button = opt.get();
        CommandQueue.get().addCommand(button);
    }
}
