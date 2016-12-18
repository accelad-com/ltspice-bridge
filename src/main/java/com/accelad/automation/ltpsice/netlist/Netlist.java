package com.accelad.automation.ltpsice.netlist;

import java.util.ArrayList;
import java.util.List;

import com.accelad.automation.ltpsice.netlist.directives.Directive;
import com.accelad.automation.ltpsice.netlist.element.Element;

public class Netlist {

    private Title title = new Title("");
    private List<Element> elements = new ArrayList<>();
    private List<Subcircuit> subcircuits = new ArrayList<>();
    private List<Directive> directives = new ArrayList<>();

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void addElement(Element e) {
        elements.add(e);
    }

    public void removeElement(Element o) {
        elements.remove(o);
    }

    public List<Subcircuit> getSubcircuits() {
        return subcircuits;
    }

    public boolean addSubcircuit(Subcircuit e) {
        return subcircuits.add(e);
    }

    public boolean removeSubCircuit(Subcircuit o) {
        return subcircuits.remove(o);
    }

    public boolean addDirective(Directive e) {
        return directives.add(e);
    }

    public boolean removeDirective(Directive o) {
        return directives.remove(o);
    }

    public List<Directive> getDirectives() {
        return directives;
    }

    public void accept(NetlistVisitor visitor) {
        visitor.accept(this);
    }
}
