package com.accelad.automation.ltpsice.netlist.element;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.Node;
import com.accelad.automation.ltpsice.netlist.Value;

public  class CurrentControlledCurrentSource implements Element {
    
    private final InstanceName instanceName;
    private final Node outputPositiveNode;
    private final Node outputNegativeNode;
    private final Node controlNode;
    private final Value gain;

    public CurrentControlledCurrentSource(InstanceName instanceName, Node outputPositiveNode,
            Node outputNegativeNode, Node controlNode, Value gain) {
        this.instanceName = instanceName;
        this.outputPositiveNode = outputPositiveNode;
        this.outputNegativeNode = outputNegativeNode;
        this.controlNode = controlNode;
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

    public Node getControlNode() {
        return controlNode;
    }

    public Value getGain() {
        return gain;
    }


//    @Override
//    public String getLetter() {
//        return "F";
//    }

    @Override
    public void accept(ElementVisitor visitor) {
        visitor.visit(this);
        
    }

}
