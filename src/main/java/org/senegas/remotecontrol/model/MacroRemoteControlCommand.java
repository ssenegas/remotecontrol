package org.senegas.remotecontrol.model;

import java.util.ArrayList;
import java.util.List;

public class MacroRemoteControlCommand implements RemoteControlCommand {
    private final List<RemoteControlCommand> commands = new ArrayList<>();

    public void addCommand(RemoteControlCommand command) {
        commands.add(command);
    }

    @Override
    public void execute() {
        for (RemoteControlCommand command : commands) {
            command.execute();
        }
    }

    List<RemoteControlCommand> getCommands() {
        return this.commands;
    }

    @Override
    public RemoteControlButton getButton() {
        return null;
    }
}
