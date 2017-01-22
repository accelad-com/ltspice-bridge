package com.accelad.automation.ltpsice.netlist.directives;

public class ModelName {

    private final String value;

    public ModelName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}
