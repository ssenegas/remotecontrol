package org.senegas.remotecontrol.model;

import java.awt.*;

public enum RemoteControlButton {
    X("X", Color.BLACK, Color.LIGHT_GRAY),
    Y("Y", Color.BLACK, Color.LIGHT_GRAY),
    Z("Z", Color.BLACK, Color.LIGHT_GRAY),

    S("S", Color.BLACK, Color.LIGHT_GRAY),
    MINUS("-", Color.BLACK, Color.WHITE),
    PLUS("+", Color.BLACK, Color.WHITE),

    M("M", Color.BLACK, Color.LIGHT_GRAY),
    ON("ON", Color.BLACK, Color.WHITE),
    OFF("OFF", Color.BLACK, Color.WHITE),

    ONE("1", Color.WHITE, Color.BLACK),
    TWO("2", Color.WHITE, Color.BLACK),
    THREE("3", Color.WHITE, Color.BLACK),

    FOUR("4", Color.WHITE, Color.BLACK),
    FIVE("5", Color.WHITE, Color.BLACK),
    SIX("6", Color.WHITE, Color.BLACK),

    SEVEN("7", Color.WHITE, Color.BLACK),
    EIGHT("8", Color.WHITE, Color.BLACK),
    NINE("9", Color.WHITE, Color.BLACK),

    P("P", Color.BLACK, Color.LIGHT_GRAY),
    ZERO("0", Color.WHITE, Color.BLACK),
    RETURN("R", Color.BLACK, Color.LIGHT_GRAY);

    private final String displayName;
    private final Color textColor;
    private final Color backgroundColor;
    private final RemoteControlButtonCommand command;

    RemoteControlButton(String displayName, Color textColor, Color backgroundColor) {
        this.displayName = displayName;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.command = new RemoteControlButtonCommand(this); // Each button has its own command
    }

    public String getDisplayName() {
        return displayName;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public RemoteControlButtonCommand getCommand() {
        return command;
    }
}
