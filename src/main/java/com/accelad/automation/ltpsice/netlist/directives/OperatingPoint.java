package com.accelad.automation.ltpsice.netlist.directives;

public class OperatingPoint implements Directive {


    @Override
    public void accept(DirectiveVisitor visitor) {
        visitor.visit(this);
    }

}
