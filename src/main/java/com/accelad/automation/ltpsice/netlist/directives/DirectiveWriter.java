package com.accelad.automation.ltpsice.netlist.directives;

public class DirectiveWriter implements DirectiveVisitor {

    private static final String SEPARATOR = " ";
    private String text = "";

    public String getElementText() {
        return text;
    }

    @Override
    public void visit(Param c) {
        text = ".PARAM" + SEPARATOR + c.getName() + SEPARATOR + c.getValue().getValue();
    }

}
