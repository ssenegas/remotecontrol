package org.senegas.remotecontrol.model;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

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
}

