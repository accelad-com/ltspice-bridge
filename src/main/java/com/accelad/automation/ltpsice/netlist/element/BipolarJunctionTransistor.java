package com.accelad.automation.ltpsice.netlist.element;

import java.util.Optional;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.Model;
import com.accelad.automation.ltpsice.netlist.Node;

public class BipolarJunctionTransistor implements Element {

    private final InstanceName instanceName;
    private final Node collector;
    private final Node base;
    private final Node emitter;
    private final Node substrate;
    private final Model model;

    public BipolarJunctionTransistor(InstanceName instanceName, Node collector, Node base,
            Node emitter, Node substrate, Model model) {
        this.instanceName = instanceName;
        this.collector = collector;
        this.base = base;
        this.emitter = emitter;
        this.model = model;
        this.substrate = substrate;
    }

    @Override
    public InstanceName getInstanceName() {
        return instanceName;
    }

    public Node getCollector() {
        return collector;
    }

    public Node getBase() {
        return base;
    }

    public Node getEmitter() {
        return emitter;
    }

    public Optional<Node> getSubstrate() {
        return Optional.ofNullable(substrate);
    }

    public Model getModel() {
        return model;
    }

    @Override
    public void accept(ElementVisitor visitor) {
        visitor.visit(this);
    }

}
