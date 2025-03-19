package org.senegas.remotecontrol.model;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MacroRemoteControlCommand implements RemoteControlCommand {
    private List<RemoteControlCommand> commands = new ArrayList<>();

    public MacroRemoteControlCommand() {
    }

    public MacroRemoteControlCommand(List<RemoteControlCommand> commands) {
        this.commands = commands;
    }

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

    public static MacroRemoteControlCommand loadMacro(InputStream input) {
        LoaderOptions options = new LoaderOptions();
        options.setAllowDuplicateKeys(false);
        Yaml yaml = new Yaml(new Constructor(Map.class, options));
        Map<String, Object> yamlData = yaml.load(input);
        List<String> buttonNames = (List<String>) yamlData.get("macro");
        List<RemoteControlCommand> commands = buttonNames.stream()
                .map(RemoteControlButton::valueOf)
                .map(RemoteControlButtonCommand::new)
                .collect(Collectors.toList());
        return new MacroRemoteControlCommand(commands);
    }
}
