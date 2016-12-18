package com.accelad.automation.ltpsice.netlist.element;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.Node;
import com.accelad.automation.ltpsice.netlist.Value;

public  class IndependentCurrentSource implements Element {
    
    private InstanceName instanceName;
    private Node positiveNode;
    private Node negativeNode;
    private Value value;

    public IndependentCurrentSource(InstanceName instanceName, Node positiveNode, Node negativeNode,
            Value value) {
        this.instanceName = instanceName;
        this.positiveNode = positiveNode;
        this.negativeNode = negativeNode;
        this.value = value;
    }

    @Override
    public InstanceName getInstanceName() {
        return instanceName;
    }

    public Node getPositive() {
        return positiveNode;
    }

    public Node getNegative() {
        return negativeNode;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public void accept(ElementVisitor visitor) {
        visitor.visit(this);
    }

}
