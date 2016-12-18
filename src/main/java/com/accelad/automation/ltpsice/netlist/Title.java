package com.accelad.automation.ltpsice.netlist;

public class Title {

    private final String line;

    public Title(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    @Override
    public String toString() {
        return line;
    }

}
