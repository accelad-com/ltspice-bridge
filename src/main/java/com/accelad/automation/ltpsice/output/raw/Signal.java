package com.accelad.automation.ltpsice.output.raw;

public class Signal {
    private final int index;
    private final String fullName;
    private final String name;
    private final String type;

    public Signal(int index, String fullName, String name, String type) {
        this.index = index;
        this.fullName = fullName;
        this.name = name;
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public String getFullName() {
        return fullName;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Signal [index=" + index + ", fullName=" + fullName + ", name=" + name + ", type="
                + type + "]";
    }

}
