package com.accelad.automation.ltpsice.netlist.element;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.Model;
import com.accelad.automation.ltpsice.netlist.Node;

public  class Diode implements Element {
    
    private InstanceName instanceName;
    private Node anode;
    private Node cathode;
    private Model model;

    public Diode(InstanceName instanceName, Node anode, Node cathode, Model model) {
        this.instanceName = instanceName;
        this.anode = anode;
        this.cathode = cathode;
        this.model = model;
    }

//    @Override
//    public String getLetter() {
//        return "D";
//    }

    @Override
    public InstanceName getInstanceName() {
        return instanceName;
    }

    public Node getAnode() {
        return anode;
    }

    public Node getCathode() {
        return cathode;
    }

    public Model getModel() {
        return model;
    }

    @Override
    public void accept(ElementVisitor visitor) {
        visitor.visit(this);
        
    }

}
