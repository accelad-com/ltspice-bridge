package com.accelad.automation.ltpsice.netlist.directives;

public interface Directive {

    public void accept(DirectiveVisitor visitor);

}
