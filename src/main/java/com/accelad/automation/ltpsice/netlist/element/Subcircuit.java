package com.accelad.automation.ltpsice.netlist.element;

import java.util.List;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.Model;
import com.accelad.automation.ltpsice.netlist.Node;

public  class Subcircuit implements Element {

    private final InstanceName instanceName;
    private final List<Node> nodes;
    private final Model model;

    public Subcircuit(InstanceName instanceName, List<Node> nodes, Model model) {
        this.instanceName = instanceName;
        this.model = model;
        this.nodes = nodes;
    }

    @Override
    public InstanceName getInstanceName() {
        return instanceName;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Model getModel() {
        return model;
    }

//    @Override
//    public String getLetter() {
//        return "X";
//    }

    @Override
    public void accept(ElementVisitor visitor) {
        visitor.visit(this);
        
    }

}
