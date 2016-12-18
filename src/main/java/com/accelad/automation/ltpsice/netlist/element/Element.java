package com.accelad.automation.ltpsice.netlist.element;

import com.accelad.automation.ltpsice.netlist.InstanceName;

public interface Element {
    public InstanceName getInstanceName();

    public void accept(ElementVisitor visitor);
}
