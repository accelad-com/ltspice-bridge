package com.accelad.automation.ltpsice.output.log;

public class Measure {

    private final String name;
    private final String definition;
    private final double value;

    public Measure(String name, String definition, double value) {
        this.name = name;
        this.definition = definition;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getDefinition() {
        return definition;
    }

    public double getValue() {
        return value;
    }

}
