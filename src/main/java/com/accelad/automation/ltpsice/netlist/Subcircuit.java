package com.accelad.automation.ltpsice.netlist;

import java.util.List;

import com.accelad.automation.ltpsice.netlist.directives.Directive;
import com.accelad.automation.ltpsice.netlist.element.Element;

public class Subcircuit {

    private String name;
    private List<Node> nodes;
    private List<Element> elements;
    private List<Directive> directives;


    public Subcircuit(String name, List<Node> nodes, List<Element> elements,
            List<Directive> directives) {
        this.name = name;
        this.nodes = nodes;
        this.elements = elements;
        this.directives = directives;
    }

    public String getName() {
        return name;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Element> getElements() {
        return elements;
    }

    public List<Directive> getDirectives() {
        return directives;
    }

}
