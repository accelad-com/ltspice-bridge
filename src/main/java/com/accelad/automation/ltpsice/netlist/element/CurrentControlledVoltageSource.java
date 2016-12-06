package com.accelad.automation.ltpsice.netlist.element;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.Node;
import com.accelad.automation.ltpsice.netlist.Value;

public  class CurrentControlledVoltageSource implements Element {

    private InstanceName instanceName;
    private Node outputPositiveNode;
    private Node outputNegativeNode;
    private Node controlNode;
    private Value gain;

    public CurrentControlledVoltageSource(InstanceName instanceName, Node outputPositiveNode,
            Node outputNegativeNode, Node controlNode, Value gain) {
        this.instanceName = instanceName;
        this.outputPositiveNode = outputPositiveNode;
        this.outputNegativeNode = outputNegativeNode;
        this.controlNode = controlNode;
        this.gain = gain;
    }

    public Node getOutputPositiveNode() {
        return outputPositiveNode;
    }

    public Node getOutputNegativeNode() {
        return outputNegativeNode;
    }

    public Node getControlNode() {
        return controlNode;
    }

    public Value getGain() {
        return gain;
    }

//    @Override
//    public String getLetter() {
//        return "H";
//    }

    @Override
    public InstanceName getInstanceName() {
        return instanceName;
    }

    @Override
    public void accept(ElementVisitor visitor) {
        visitor.visit(this);
        
    }

}
