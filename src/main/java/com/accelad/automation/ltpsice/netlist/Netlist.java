package com.accelad.automation.ltpsice.netlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.accelad.automation.ltpsice.netlist.directives.Directive;
import com.accelad.automation.ltpsice.netlist.element.Element;

public class Netlist {

    private Title title = new Title("");
    private ElementCollection elements = new ElementCollection();
    private SubcircuitCollection subcircuitCollection = new SubcircuitCollection();
    private List<Directive> directives = new ArrayList<>();

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public ElementCollection getElements() {
        return elements;
    }

    public void addElement(Element e) {
        elements.add(e);
    }

    public void removeElement(Element o) {
        elements.remove(o);
    }

    public SubcircuitCollection getSubcircuits() {
        return subcircuitCollection;
    }

    public boolean addSubcircuit(Subcircuit e) {
        return subcircuitCollection.add(e);
    }

    public boolean removeSubCircuit(Subcircuit o) {
        return subcircuitCollection.remove(o);
    }

    public Optional<Subcircuit> findSubcircuit(String name) {
        return subcircuitCollection.findSubcircuit(name);
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
