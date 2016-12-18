package com.accelad.automation.ltpsice.netlist.directives;

import java.util.ArrayList;
import java.util.List;

public class Transient implements Directive {

    private String stopTime;

    private List<String> modifiers = new ArrayList<>();

    public Transient(String stopTime) {
        this.stopTime = stopTime;
    }

    public Transient(String stopTime, List<String> modifiers) {
        this.stopTime = stopTime;
        this.modifiers.addAll(modifiers);
    }

    public List<String> getModifiers() {
        return modifiers;
    }

    public String getStopTime() {
        return stopTime;
    }

    @Override
    public void accept(DirectiveVisitor visitor) {
        visitor.visit(this);
    }

}
