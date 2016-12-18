package com.accelad.automation.ltpsice.netlist.directives;

import com.accelad.automation.ltpsice.netlist.Value;

public class UserDefinedParameter implements Directive {

    private String name;
    private Value value;

    public UserDefinedParameter(String name, Value value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public void accept(DirectiveVisitor visitor) {
        visitor.visit(this);
    }
}
