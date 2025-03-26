package org.senegas.remotecontrol.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RemoteControlCommandTest {
    private RemoteControlButtonCommand buttonCommandX;
    private RemoteControlButtonCommand buttonCommandY;
    private MacroRemoteControlCommand macroCommand;

    @BeforeEach
    void setUp() {
        buttonCommandX = (RemoteControlButtonCommand) RemoteControlButton.X.getCommand();
        buttonCommandY = (RemoteControlButtonCommand) RemoteControlButton.Y.getCommand();

        macroCommand = new MacroRemoteControlCommand();
    }

    @Test
    void testButtonCommandExecution() {
        assertDoesNotThrow(() -> buttonCommandX.execute());
        assertDoesNotThrow(() -> buttonCommandY.execute());
    }

    @Test
    void testButtonCommandRetrievesCorrectButton() {
        assertEquals(RemoteControlButton.X, buttonCommandX.getButton());
        assertEquals(RemoteControlButton.Y, buttonCommandY.getButton());
    }

    @Test
    void testDelayCommand() {
        RemoteControlDelayCommand remoteControlDelayCommand = new RemoteControlDelayCommand(2000);
        assertDoesNotThrow(() -> remoteControlDelayCommand.execute());
    }

    @Test
    void testMacroCommandExecutionWithDelay() {
        macroCommand.addCommand(RemoteControlButton.ZERO.getCommand());
        macroCommand.addCommand(new RemoteControlDelayCommand(2000));
        macroCommand.addCommand(RemoteControlButton.ONE.getCommand());

        assertEquals(3, macroCommand.getCommands().size());

        assertDoesNotThrow(() -> macroCommand.execute());
    }

    @Test
    void testMacroCommandExecution() {
        macroCommand.addCommand(RemoteControlButton.ZERO.getCommand());
        macroCommand.addCommand(RemoteControlButton.ZERO.getCommand());
        macroCommand.addCommand(RemoteControlButton.SEVEN.getCommand());

        assertEquals(3, macroCommand.getCommands().size());

        assertDoesNotThrow(() -> macroCommand.execute());
    }

    @Test
    void testLoadMacroFromResources() throws Exception {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("macro.yaml")) {
            MacroRemoteControlCommand macroCommand = MacroRemoteControlCommand.loadMacro(input);

            List<RemoteControlCommand> commands = macroCommand.getCommands();
            assertEquals(7, commands.size(), "Macro should contain 6 commands");

            assertInstanceOf(RemoteControlButtonCommand.class, commands.get(0));
            assertEquals(RemoteControlButton.X, ((RemoteControlButtonCommand) commands.get(0)).getButton());

            assertInstanceOf(RemoteControlButtonCommand.class, commands.get(1));
            assertEquals(RemoteControlButton.Y, ((RemoteControlButtonCommand) commands.get(1)).getButton());

            assertInstanceOf(RemoteControlDelayCommand.class, commands.get(2));
            assertEquals(null, ((RemoteControlDelayCommand) commands.get(2)).getButton());

            assertInstanceOf(RemoteControlButtonCommand.class, commands.get(3));
            assertEquals(RemoteControlButton.Z, ((RemoteControlButtonCommand) commands.get(3)).getButton());

            assertInstanceOf(RemoteControlDelayCommand.class, commands.get(4));
            assertEquals(null, ((RemoteControlDelayCommand) commands.get(4)).getButton());

            assertInstanceOf(RemoteControlButtonCommand.class, commands.get(5));
            assertEquals(RemoteControlButton.ON, ((RemoteControlButtonCommand) commands.get(5)).getButton());

            assertInstanceOf(RemoteControlButtonCommand.class, commands.get(6));
            assertEquals(RemoteControlButton.OFF, ((RemoteControlButtonCommand) commands.get(6)).getButton());

            assertDoesNotThrow(() -> macroCommand.execute());
        }
    }

    @Test
    void testLoadMacroFromString() {
        String yamlContent = """
    macro:
      - { type: "button", value: "X" }
      - { type: "button", value: "Y" }
      - { type: "delay", value: 2000 }
      - { type: "button", value: "Z" }
      - { type: "delay", value: 1000 }
      - { type: "button", value: "ON" }
      - { type: "button", value: "OFF" }
    """;
        InputStream input = new ByteArrayInputStream(yamlContent.getBytes(StandardCharsets.UTF_8));

        MacroRemoteControlCommand macroCommand = MacroRemoteControlCommand.loadMacro(input);
        assertDoesNotThrow(() -> macroCommand.execute());
    }
}

