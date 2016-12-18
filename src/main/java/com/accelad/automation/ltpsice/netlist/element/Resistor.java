package com.accelad.automation.ltpsice.netlist.element;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.Node;
import com.accelad.automation.ltpsice.netlist.Value;

public  class Resistor implements Element {

    private InstanceName instanceName;
    private Node positive;
    private Node negative;
    private Value value;

    public Resistor(InstanceName instanceName, Node positive, Node negative, Value value) {
        this.instanceName = instanceName;
        this.positive = positive;
        this.negative = negative;
        this.value = value;
    }

    @Override
    public InstanceName getInstanceName() {
        return instanceName;
    }

    public Node getPositive() {
        return positive;
    }

    public Node getNegative() {
        return negative;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public void accept(ElementVisitor visitor) {
        visitor.visit(this);
        
    }

}
