package com.accelad.automation.ltpsice.netlist.element;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.Model;
import com.accelad.automation.ltpsice.netlist.Node;

public class Mosfet implements Element {

    private final InstanceName instanceName;
    private final Node drain;
    private final Node gate;
    private final Node source;
    private final Model model;

    public Mosfet(InstanceName instanceName, Node drain, Node gate,
            Node source, Model model) {
        this.instanceName = instanceName;
        this.drain = drain;
        this.gate = gate;
        this.source = source;
        this.model = model;
    }

    @Override
    public InstanceName getInstanceName() {
        return instanceName;
    }

    public Node getDrain() {
        return drain;
    }

    public Node getGate() {
        return gate;
    }

    public Node getSource() {
        return source;
    }

    public Model getModel() {
        return model;
    }

    @Override
    public void accept(ElementVisitor visitor) {
        visitor.visit(this);
    }

}
