package com.accelad.automation.ltpsice.netlist.directives;

import java.util.List;

import com.accelad.automation.ltpsice.netlist.Value;

public class UserDefinedFunction implements Directive {

    private String name;
    private List<String> parameters;
    private Value value;

    public UserDefinedFunction(String name, List<String> parameters, Value value) {
        this.name = name;
        this.parameters = parameters;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Value getValue() {
        return value;
    }

    public List<String> getParameters() {
        return parameters;
    }

    @Override
    public void accept(DirectiveVisitor visitor) {
        visitor.visit(this);
    }
}
