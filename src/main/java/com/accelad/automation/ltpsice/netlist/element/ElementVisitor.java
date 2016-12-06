package com.accelad.automation.ltpsice.netlist.element;

public interface ElementVisitor {
    void visit(BehaviouralVoltageSource b);

    void visit(BehaviouralCurrentSource b);

    void visit(BipolarJunctionTransistor c);

    void visit(Capacitor c);

    void visit(CurrentControlledCurrentSource c);

    void visit(CurrentControlledVoltageSource c);

    void visit(Diode c);

    void visit(IndependentCurrentSource c);

    void visit(IndependentVoltageSource c);

    void visit(Inductor c);

    void visit(Resistor c);

    void visit(Subcircuit c);

    void visit(VoltageControlledCurrentSource c);

    void visit(VoltageControlledVoltageSource c);
}
