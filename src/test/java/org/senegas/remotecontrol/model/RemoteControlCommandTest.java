package org.senegas.remotecontrol.model;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RemoteControlCommandTest {
    private RemoteControlButtonCommand buttonCommandX;
    private RemoteControlButtonCommand buttonCommandY;
    private MacroRemoteControlCommand macroCommand;

    @BeforeEach
    void setUp() {
        // Use enum values directly
        buttonCommandX = (RemoteControlButtonCommand) RemoteControlButton.X.getCommand();
        buttonCommandY = (RemoteControlButtonCommand) RemoteControlButton.Y.getCommand();

        macroCommand = new MacroRemoteControlCommand();
    }

    @Test
    void testButtonCommandExecution() {
        // Capture output for assertion (use a logging or capture mechanism in real cases)
        assertDoesNotThrow(() -> buttonCommandX.execute());
        assertDoesNotThrow(() -> buttonCommandY.execute());
    }

    @Test
    void testButtonCommandRetrievesCorrectButton() {
        assertEquals(RemoteControlButton.X, buttonCommandX.getButton());
        assertEquals(RemoteControlButton.Y, buttonCommandY.getButton());
    }

    @Test
    void testMacroCommandExecution() {
        // Add button commands to macro
        macroCommand.addCommand(RemoteControlButton.ZERO.getCommand());
        macroCommand.addCommand(RemoteControlButton.ZERO.getCommand());
        macroCommand.addCommand(RemoteControlButton.SEVEN.getCommand());

        assertEquals(3, macroCommand.getCommands().size());

        // Execute the macro (should execute all added commands)
        assertDoesNotThrow(() -> macroCommand.execute());
    }

    @Test
    void testLoadMacroFromResources() throws Exception {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("macro.yaml")) {
            MacroRemoteControlCommand macroCommand = MacroRemoteControlCommand.loadMacro(input);

            // Verify the macro contains the expected commands
            List<RemoteControlCommand> commands = macroCommand.getCommands();
            assertEquals(6, commands.size(), "Macro should contain 6 commands");

            assertInstanceOf(RemoteControlButtonCommand.class, commands.get(0));
            assertEquals(RemoteControlButton.X, ((RemoteControlButtonCommand) commands.get(0)).getButton());

            assertInstanceOf(RemoteControlButtonCommand.class, commands.get(1));
            assertEquals(RemoteControlButton.Y, ((RemoteControlButtonCommand) commands.get(1)).getButton());

            assertInstanceOf(RemoteControlButtonCommand.class, commands.get(2));
            assertEquals(RemoteControlButton.Z, ((RemoteControlButtonCommand) commands.get(2)).getButton());

            assertInstanceOf(RemoteControlButtonCommand.class, commands.get(3));
            assertEquals(RemoteControlButton.ZERO, ((RemoteControlButtonCommand) commands.get(3)).getButton());

            assertInstanceOf(RemoteControlButtonCommand.class, commands.get(4));
            assertEquals(RemoteControlButton.ZERO, ((RemoteControlButtonCommand) commands.get(4)).getButton());

            assertInstanceOf(RemoteControlButtonCommand.class, commands.get(5));
            assertEquals(RemoteControlButton.SEVEN, ((RemoteControlButtonCommand) commands.get(5)).getButton());

            assertDoesNotThrow(() -> macroCommand.execute());
        }
    }

    @Test
    void testLoadMacroFromString() {
        String yamlContent = "macro:\n- X\n- Y\n- Z\n- ZERO\n- ZERO\n- SEVEN\n";
        InputStream input = new ByteArrayInputStream(yamlContent.getBytes(StandardCharsets.UTF_8));

        MacroRemoteControlCommand macroCommand = MacroRemoteControlCommand.loadMacro(input);
        assertDoesNotThrow(() -> macroCommand.execute());
    }
}

