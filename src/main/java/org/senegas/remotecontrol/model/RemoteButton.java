package org.senegas.remotecontrol.model;

import java.awt.*;

public enum RemoteButton {
    X("X", Color.BLACK, Color.LIGHT_GRAY, new SampleCommand("X Pressed")),
    Y("Y", Color.BLACK, Color.LIGHT_GRAY, new SampleCommand("Y Pressed")),
    Z("Z", Color.BLACK, Color.LIGHT_GRAY, new SampleCommand("Z Pressed")),

    S("S", Color.BLACK, Color.LIGHT_GRAY, new SampleCommand("S Pressed")),
    MINUS("-", Color.BLACK, Color.WHITE, new SampleCommand("- Pressed")),
    PLUS("+", Color.BLACK, Color.WHITE, new SampleCommand("+ Pressed")),

    M("M", Color.BLACK, Color.LIGHT_GRAY, new SampleCommand("M Pressed")),
    ON("ON", Color.BLACK, Color.WHITE, new SampleCommand("ON Pressed")),
    OFF("OFF", Color.BLACK, Color.WHITE, new SampleCommand("OFF Pressed")),

    ONE("1", Color.WHITE, Color.BLACK, new SampleCommand("1 Pressed")),
    TWO("2", Color.WHITE, Color.BLACK, new SampleCommand("2 Pressed")),
    THREE("3", Color.WHITE, Color.BLACK, new SampleCommand("3 Pressed")),

    FOUR("4", Color.WHITE, Color.BLACK, new SampleCommand("4 Pressed")),
    FIVE("5", Color.WHITE, Color.BLACK, new SampleCommand("5 Pressed")),
    SIX("6", Color.WHITE, Color.BLACK, new SampleCommand("6 Pressed")),

    SEVEN("7", Color.WHITE, Color.BLACK, new SampleCommand("7 Pressed")),
    EIGHT("8", Color.WHITE, Color.BLACK, new SampleCommand("8 Pressed")),
    NINE("9", Color.WHITE, Color.BLACK, new SampleCommand("9 Pressed")),

    P("P", Color.BLACK, Color.LIGHT_GRAY, new SampleCommand("P Pressed")),
    ZERO("0", Color.WHITE, Color.BLACK, new SampleCommand("0 Pressed")),
    RETURN("R", Color.BLACK, Color.LIGHT_GRAY, new SampleCommand("RETURN Pressed"));

    private final String displayName;
    private final Color textColor;
    private final Color backgroundColor;
    private final Command command;

    RemoteButton(String displayName, Color textColor, Color backgroundColor, Command command) {
        this.displayName = displayName;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.command = command;
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

    public Command getCommand() {
        return command;
    }
}
