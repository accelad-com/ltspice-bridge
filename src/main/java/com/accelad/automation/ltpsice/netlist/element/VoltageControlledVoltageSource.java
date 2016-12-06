package com.accelad.automation.ltpsice.netlist.element;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.Node;
import com.accelad.automation.ltpsice.netlist.Value;

public  class VoltageControlledVoltageSource implements Element {

    private InstanceName instanceName;
    private Node outputPositiveNode;
    private Node outputNegativeNode;
    private Node measurePositiveNode;
    private Node measureNegativeNode;
    private Value gain;

    public VoltageControlledVoltageSource(InstanceName instanceName, Node outputPositiveNode,
            Node outputNegativeNode, Node measurePositiveNode, Node measureNegativeNode,
            Value gain) {
        this.instanceName = instanceName;
        this.outputPositiveNode = outputPositiveNode;
        this.outputNegativeNode = outputNegativeNode;
        this.measurePositiveNode = measurePositiveNode;
        this.measureNegativeNode = measureNegativeNode;
        this.gain = gain;
    }

    @Override
    public InstanceName getInstanceName() {
        return instanceName;
    }

    public Node getOutputPositiveNode() {
        return outputPositiveNode;
    }

    public Node getOutputNegativeNode() {
        return outputNegativeNode;
    }

    public Node getMeasurePositiveNode() {
        return measurePositiveNode;
    }

    public Node getMeasureNegativeNode() {
        return measureNegativeNode;
    }

    public Value getGain() {
        return gain;
    }

//    @Override
//    public String getLetter() {
//        return "E";
//    }

    @Override
    public void accept(ElementVisitor visitor) {
        visitor.visit(this);
    }
}
