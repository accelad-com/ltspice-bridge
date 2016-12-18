package com.accelad.automation.ltpsice.netlist;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.accelad.automation.ltpsice.netlist.element.BehaviouralCurrentSource;
import com.accelad.automation.ltpsice.netlist.element.BehaviouralVoltageSource;
import com.accelad.automation.ltpsice.netlist.element.BipolarJunctionTransistor;
import com.accelad.automation.ltpsice.netlist.element.Capacitor;
import com.accelad.automation.ltpsice.netlist.element.CurrentControlledCurrentSource;
import com.accelad.automation.ltpsice.netlist.element.CurrentControlledVoltageSource;
import com.accelad.automation.ltpsice.netlist.element.Diode;
import com.accelad.automation.ltpsice.netlist.element.ElementParameter;
import com.accelad.automation.ltpsice.netlist.element.ElementVisitor;
import com.accelad.automation.ltpsice.netlist.element.IndependentCurrentSource;
import com.accelad.automation.ltpsice.netlist.element.IndependentVoltageSource;
import com.accelad.automation.ltpsice.netlist.element.Inductor;
import com.accelad.automation.ltpsice.netlist.element.Mosfet;
import com.accelad.automation.ltpsice.netlist.element.Resistor;
import com.accelad.automation.ltpsice.netlist.element.Subcircuit;
import com.accelad.automation.ltpsice.netlist.element.VoltageControlledCurrentSource;
import com.accelad.automation.ltpsice.netlist.element.VoltageControlledVoltageSource;

public class ElementWriter implements ElementVisitor {
    private static final String SEPARATOR = " ";
    private String formattedText = "";

    String getFormattedText() {
        return formattedText;
    }

    private String formatTwoNodeElement(Character type, InstanceName instanceName,
            Node firstNode, Node secondNode, String... arguments) {
        return String.format("%c%s %s %s %s", type, instanceName, firstNode, secondNode,
                Arrays.stream(arguments).collect(Collectors.joining(SEPARATOR)));
    }

    private String formatThreeNodeElement(Character type, InstanceName instanceName,
            Node firstNode, Node secondNode, Node thirdNode, String... arguments) {
        return String.format("%c%s %s %s %s %s", type, instanceName, firstNode, secondNode,
                thirdNode, Arrays.stream(arguments).collect(Collectors.joining(SEPARATOR)));
    }

    private String formatFourNodeElement(Character type, InstanceName instanceName,
            Node firstNode, Node secondNode, Node thirdNode, Node fourthNode,
            String... arguments) {
        return String.format("%c%s %s %s %s %s %s", type, instanceName, firstNode, secondNode,
                thirdNode, fourthNode,
                Arrays.stream(arguments).collect(Collectors.joining(SEPARATOR)));
    }

    @Override
    public void visit(BipolarJunctionTransistor c) {
        formattedText = formatThreeNodeElement('Q', c.getInstanceName(), c.getCollector(),
                c.getBase(), c.getEmitter(), c.getModel().getValue());
    }

    @Override
    public void visit(Mosfet c) {
        formattedText = formatThreeNodeElement('M', c.getInstanceName(), c.getDrain(),
                c.getGate(), c.getSource(), c.getModel().getValue());
    }

    @Override
    public void visit(Capacitor c) {
        formattedText = formatTwoNodeElement('C', c.getInstanceName(), c.getPositive(),
                c.getNegative(), c.getValue().getValue());
    }

    @Override
    public void visit(CurrentControlledCurrentSource c) {
        formattedText = formatThreeNodeElement('F', c.getInstanceName(),
                c.getOutputPositiveNode(), c.getOutputNegativeNode(), c.getControlNode(),
                c.getGain().getValue());
    }

    @Override
    public void visit(CurrentControlledVoltageSource c) {
        formattedText = formatThreeNodeElement('H', c.getInstanceName(),
                c.getOutputPositiveNode(), c.getOutputNegativeNode(), c.getControlNode(),
                c.getGain().getValue());
    }

    @Override
    public void visit(Diode c) {
        formattedText = formatTwoNodeElement('D', c.getInstanceName(), c.getAnode(),
                c.getCathode(), c.getModel().getValue());
    }

    @Override
    public void visit(BehaviouralCurrentSource c) {
        formattedText = formatTwoNodeElement('B', c.getInstanceName(), c.getPositive(),
                c.getNegative(), c.getValue().getValue());
    }

    @Override
    public void visit(BehaviouralVoltageSource c) {
        formattedText = formatTwoNodeElement('B', c.getInstanceName(), c.getPositive(),
                c.getNegative(), c.getValue().getValue());
    }

    @Override
    public void visit(IndependentCurrentSource c) {
        formattedText = formatTwoNodeElement('I', c.getInstanceName(), c.getPositive(),
                c.getNegative(), c.getValue().getValue());
    }

    @Override
    public void visit(IndependentVoltageSource c) {
        formattedText = formatTwoNodeElement('V', c.getInstanceName(), c.getPositive(),
                c.getNegative(), c.getValue().getValue());
    }

    @Override
    public void visit(Inductor c) {
        formattedText = formatTwoNodeElement('L', c.getInstanceName(), c.getPositive(),
                c.getNegative(), c.getValue().getValue());
    }

    @Override
    public void visit(Resistor c) {
        formattedText = formatTwoNodeElement('R', c.getInstanceName(), c.getPositive(),
                c.getNegative(), c.getValue().getValue());
    }

    @Override
    public void visit(Subcircuit c) {
        StringBuilder builder = new StringBuilder();
        builder.append('X' + c.getInstanceName().getName() + SEPARATOR);
        for (Node node : c.getNodes()) {
            builder.append(node.getName() + SEPARATOR);
        }
        builder.append(c.getModel().getValue() + SEPARATOR);

        for (ElementParameter parameter : c.getParameters()) {
            builder.append(parameter.getName() + "=" + parameter.getValue() + SEPARATOR);
        }
        formattedText = builder.toString();
    }

    @Override
    public void visit(VoltageControlledCurrentSource c) {
        formattedText = formatFourNodeElement('G', c.getInstanceName(),
                c.getOutputPositiveNode(), c.getOutputNegativeNode(), c.getMeasurePositiveNode(),
                c.getMeasureNegativeNode(),  c.getGain().getValue());
    }

    @Override
    public void visit(VoltageControlledVoltageSource c) {
        formattedText = formatFourNodeElement('E', c.getInstanceName(),
                c.getOutputPositiveNode(), c.getOutputNegativeNode(), c.getMeasurePositiveNode(),
                c.getMeasureNegativeNode(),  c.getGain().getValue());
    }
}