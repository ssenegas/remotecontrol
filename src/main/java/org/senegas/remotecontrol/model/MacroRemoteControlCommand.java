package org.senegas.remotecontrol.model;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
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
        List<Map<String, Object>> macroSteps = (List<Map<String, Object>>) yamlData.get("macro");

        List<RemoteControlCommand> commands = macroSteps.stream()
                .map(MacroRemoteControlCommand::parseStep)
                .collect(Collectors.toList());

        return new MacroRemoteControlCommand(commands);
    }

    private static RemoteControlCommand parseStep(Map<String, Object> step) {
        String type = (String) step.get("type");
        if ("button".equals(type)) {
            String buttonName = (String) step.get("value");
            return RemoteControlButton.valueOf(buttonName).getCommand();
        } else if ("delay".equals(type)) {
            int delay = (int) step.get("value");
            return new RemoteControlDelayCommand(delay);
        }
        throw new IllegalArgumentException("Unknown macro step type: " + type);
    }
}
