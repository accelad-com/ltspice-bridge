package com.accelad.automation.ltpsice.netlist.directives;

import com.accelad.automation.ltpsice.netlist.Value;

public class UserDefinedFunction implements Directive {

    private String name;
    private Parameters parameters = new Parameters();
    private Value value;

    public UserDefinedFunction(String name, Parameters parameters, Value value) {
        this.name = name;
        this.parameters.addAll(parameters);
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Value getValue() {
        return value;
    }

    public Parameters getParameters() {
        return parameters;
    }

    @Override
    public void accept(DirectiveVisitor visitor) {
        visitor.visit(this);
    }
}
