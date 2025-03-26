package org.senegas.remotecontrol.model;

public class RemoteControlDelayCommand implements RemoteControlCommand {
    private final long duration;

    public RemoteControlDelayCommand(long duration) {
        this.duration = duration;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Waiting for " + duration + " ms...");
            Thread.sleep(duration);
            System.out.println("End.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public RemoteControlButton getButton() {
        return null;
    }
}
