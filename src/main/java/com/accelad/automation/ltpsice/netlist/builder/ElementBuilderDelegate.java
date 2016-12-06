package com.accelad.automation.ltpsice.netlist.builder;

import com.accelad.automation.ltpsice.netlist.element.Element;

public interface ElementBuilderDelegate {
    public Element build(String text);
}