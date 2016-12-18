package com.accelad.automation.ltpsice.netlist.element;

import java.util.List;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.Model;
import com.accelad.automation.ltpsice.netlist.Node;

public  class Subcircuit implements Element {

    private final InstanceName instanceName;
    private final List<Node> nodes;
    private final Model model;
    private final List<ElementParameter> parameters;

    public Subcircuit(InstanceName instanceName, List<Node> nodes, Model model,
            List<ElementParameter> parameters) {
        this.instanceName = instanceName;
        this.model = model;
        this.nodes = nodes;
        this.parameters = parameters;
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

    public List<ElementParameter> getParameters() {
        return parameters;
    }

    @Override
    public void accept(ElementVisitor visitor) {
        visitor.visit(this);
    }

}
