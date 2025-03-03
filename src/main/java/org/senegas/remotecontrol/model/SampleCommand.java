package org.senegas.remotecontrol.model;

public class SampleCommand implements Command {
    private final String message;

    public SampleCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        System.out.println(message);
    }
}
