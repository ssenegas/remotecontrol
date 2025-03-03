package org.senegas.remotecontrol.model;

public enum RemoteButton {
    X("X", new SampleCommand("X Pressed")), 
    Y("Y", new SampleCommand("Y Pressed")), 
    Z("Z", new SampleCommand("Z Pressed")),
    S("S", new SampleCommand("S Pressed")), 
    MINUS("-", new SampleCommand("- Pressed")), 
    PLUS("+", new SampleCommand("+ Pressed")),
    M("M", new SampleCommand("M Pressed")), 
    ON("ON", new SampleCommand("ON Pressed")), 
    OFF("OFF", new SampleCommand("OFF Pressed")),
    ONE("1", new SampleCommand("1 Pressed")), 
    TWO("2", new SampleCommand("2 Pressed")), 
    THREE("3", new SampleCommand("3 Pressed")),
    FOUR("4", new SampleCommand("4 Pressed")), 
    FIVE("5", new SampleCommand("5 Pressed")), 
    SIX("6", new SampleCommand("6 Pressed")),
    SEVEN("7", new SampleCommand("7 Pressed")), 
    EIGHT("8", new SampleCommand("8 Pressed")), 
    NINE("9", new SampleCommand("9 Pressed")),
    P("P", new SampleCommand("P Pressed")), 
    ZERO("0", new SampleCommand("0 Pressed")), 
    RETURN("R", new SampleCommand("RETURN Pressed"));

    private final String displayName;
    private final Command command;

    RemoteButton(String displayName, Command command) {
        this.displayName = displayName;
        this.command = command;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Command getCommand() {
        return command;
    }
}
