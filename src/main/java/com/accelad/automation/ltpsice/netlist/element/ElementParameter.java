package com.accelad.automation.ltpsice.netlist.element;

public class ElementParameter {

    private final String parameterName;
    private final String parameterValue;

    public ElementParameter(String parameterName, String parameterValue) {
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
    }

    public String getName() {
        return parameterName;
    }

    public String getValue() {
        return parameterValue;
    }
}
