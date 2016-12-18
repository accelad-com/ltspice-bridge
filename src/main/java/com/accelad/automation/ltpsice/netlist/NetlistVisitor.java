package com.accelad.automation.ltpsice.netlist;

@FunctionalInterface
public interface NetlistVisitor {
    public void accept(Netlist netlist);

}
