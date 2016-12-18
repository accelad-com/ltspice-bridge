package com.accelad.automation.ltpsice.netlist.directives;

@FunctionalInterface
public interface Directive {
    public void accept(DirectiveVisitor visitor);
}
