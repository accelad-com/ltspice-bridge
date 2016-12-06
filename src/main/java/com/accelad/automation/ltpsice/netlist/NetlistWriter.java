package com.accelad.automation.ltpsice.netlist;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.accelad.automation.ltpsice.netlist.directives.Directive;
import com.accelad.automation.ltpsice.netlist.directives.DirectiveWriter;
import com.accelad.automation.ltpsice.netlist.element.BehaviouralCurrentSource;
import com.accelad.automation.ltpsice.netlist.element.BehaviouralVoltageSource;
import com.accelad.automation.ltpsice.netlist.element.BipolarJunctionTransistor;
import com.accelad.automation.ltpsice.netlist.element.Capacitor;
import com.accelad.automation.ltpsice.netlist.element.CurrentControlledCurrentSource;
import com.accelad.automation.ltpsice.netlist.element.CurrentControlledVoltageSource;
import com.accelad.automation.ltpsice.netlist.element.Diode;
import com.accelad.automation.ltpsice.netlist.element.Element;
import com.accelad.automation.ltpsice.netlist.element.ElementVisitor;
import com.accelad.automation.ltpsice.netlist.element.IndependentCurrentSource;
import com.accelad.automation.ltpsice.netlist.element.IndependentVoltageSource;
import com.accelad.automation.ltpsice.netlist.element.Inductor;
import com.accelad.automation.ltpsice.netlist.element.Resistor;
import com.accelad.automation.ltpsice.netlist.element.Subcircuit;
import com.accelad.automation.ltpsice.netlist.element.VoltageControlledCurrentSource;
import com.accelad.automation.ltpsice.netlist.element.VoltageControlledVoltageSource;

public class NetlistWriter {

    private static final String DIRECTIVE_COMMENT = "* ";
    private static final String DIRECTIVE_END = ".END";
    private static final String NEWLINE = "\r\n";
    private static final ElementWriter elementWriter = new ElementWriter();
    private static final DirectiveWriter directiveWriter = new DirectiveWriter();

    public void write(Netlist netlist, Writer w) throws IOException {
        BufferedWriter writer = new BufferedWriter(w);
        writer.write(DIRECTIVE_COMMENT + netlist.getTitle().getLine() + NEWLINE);

        writeElements(netlist.getElements(), writer);

        writer.write(NEWLINE);

        for (com.accelad.automation.ltpsice.netlist.Subcircuit subcircuit : netlist.getSubcircuits()) {
            writer.write(".SUBCKT");
            writer.write(" ");
            writer.write(subcircuit.getName());
            writer.write(" ");
            for (Node node : subcircuit.getNodes()) {
                writer.write(node.getName());
                writer.write(" ");
            }
            writer.write(NEWLINE);
            writeElements(subcircuit.getElements(), writer);

            for (Directive directive : subcircuit.getDirectives()) {
                directive.accept(directiveWriter);
                String txt = directiveWriter.getElementText();
                writer.write(txt);
                writer.write(NEWLINE);
            }
            writer.write(".ENDS");
            writer.write(NEWLINE);
        }
        writer.write(".OP");
        writer.write(NEWLINE);

        writer.write(DIRECTIVE_END);
        writer.write(NEWLINE);
        writer.flush();
    }

    private void writeElements(List<Element> elements, BufferedWriter writer) throws IOException {
        for (Element element : elements) {
            element.accept(elementWriter);
            String line = elementWriter.getFormattedText();
            writer.write(line);
            writer.write(NEWLINE);
        }
    }

    public static class ElementWriter implements ElementVisitor {
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
            formattedText = 'X' + c.getInstanceName().getName() + SEPARATOR;
            for (Node node : c.getNodes()) {
                formattedText += node.getName() + SEPARATOR;
            }
            formattedText += c.getModel().getValue();
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
}
