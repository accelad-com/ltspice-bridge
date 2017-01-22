package com.accelad.automation.ltpsice.netlist;

import java.util.List;

import com.accelad.automation.ltpsice.netlist.directives.Directive;
import com.accelad.automation.ltpsice.netlist.element.Element;

public class Subcircuit {

    private final String name;
    private final List<Node> nodes;
    private final ElementCollection elements = new ElementCollection();
    private final List<Directive> directives;


    public Subcircuit(String name, List<Node> nodes, List<Element> elements,
            List<Directive> directives) {
        this.name = name;
        this.nodes = nodes;
        this.elements.addAll(elements);
        this.directives = directives;
    }

    public String getName() {
        return name;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public ElementCollection getElements() {
        return elements;
    }

    public List<Directive> getDirectives() {
        return directives;
    }

}
