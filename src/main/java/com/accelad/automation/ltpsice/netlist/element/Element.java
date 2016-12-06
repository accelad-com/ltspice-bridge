package com.accelad.automation.ltpsice.netlist.element;

import com.accelad.automation.ltpsice.netlist.InstanceName;

public interface Element {
    InstanceName getInstanceName();
    void accept(ElementVisitor visitor);
}
