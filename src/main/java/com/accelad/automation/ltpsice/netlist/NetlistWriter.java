package com.accelad.automation.ltpsice.netlist;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.accelad.automation.ltpsice.netlist.directives.Directive;
import com.accelad.automation.ltpsice.netlist.directives.DirectiveWriter;
import com.accelad.automation.ltpsice.netlist.element.Element;

public class NetlistWriter {

    private static final String DIRECTIVE_SUBCIRCUIT_END_HEADER = ".ENDS";
    private static final String DIRECTIVE_SUBCIRCUIT_HEADER = ".SUBCKT";
    private static final String SEPARATOR = " ";
    private static final String DIRECTIVE_COMMENT_HEADER = "* ";
    private static final String DIRECTIVE_END = ".END";
    private static final String NEWLINE = "\r\n";
    private static final ElementWriter elementWriter = new ElementWriter();
    private static final DirectiveWriter directiveWriter = new DirectiveWriter();

    public void write(Netlist netlist, Writer w) throws IOException {
        BufferedWriter writer = new BufferedWriter(w);
        writer.write(DIRECTIVE_COMMENT_HEADER + netlist.getTitle().getLine() + NEWLINE);

        writeElements(netlist.getElements(), writer);

        writer.write(NEWLINE);

        for (Subcircuit subcircuit : netlist.getSubcircuits()) {
            writer.write(DIRECTIVE_SUBCIRCUIT_HEADER);
            writer.write(SEPARATOR);
            writer.write(subcircuit.getName());
            writer.write(SEPARATOR);
            for (Node node : subcircuit.getNodes()) {
                writer.write(node.getName());
                writer.write(SEPARATOR);
            }
            writer.write(NEWLINE);
            writeElements(subcircuit.getElements(), writer);

            for (Directive directive : subcircuit.getDirectives()) {
                directive.accept(directiveWriter);
                String txt = directiveWriter.getElementText();
                writer.write(txt);
                writer.write(NEWLINE);
            }
            writer.write(DIRECTIVE_SUBCIRCUIT_END_HEADER);
            writer.write(NEWLINE);
            writer.write(NEWLINE);
        }

        for (Directive directive : netlist.getDirectives()) {
            directive.accept(directiveWriter);
            String txt = directiveWriter.getElementText();
            writer.write(txt);
            writer.write(NEWLINE);
        }

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


}
