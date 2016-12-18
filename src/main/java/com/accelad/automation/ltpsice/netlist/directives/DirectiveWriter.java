package com.accelad.automation.ltpsice.netlist.directives;

public class DirectiveWriter implements DirectiveVisitor {

    private static final String SEPARATOR = " ";
    private String text = "";

    public String getElementText() {
        return text;
    }

    @Override
    public void visit(UserDefinedParameter param) {
        text = ".PARAM" + SEPARATOR + param.getName() + SEPARATOR + param.getValue().getValue();
    }

    @Override
    public void visit(UserDefinedFunction function) {
        StringBuilder builder = new StringBuilder();
        builder.append(".FUNC" + SEPARATOR + function.getName() + "(");

        for (int i = 0; i < function.getParameters().size(); i++) {
            builder.append(function.getParameters().get(i));
            if (i != function.getParameters().size() - 1) {
                builder.append(",");
            }
        }
        builder.append(")" + SEPARATOR + function.getValue().getValue());
        text = builder.toString();
    }

    @Override
    public void visit(Measure meas) {
        text = ".MEASURE" + SEPARATOR + meas.getName() + SEPARATOR
                + meas.getExpression();

    }

    @Override
    public void visit(OperatingPoint operatingPoint) {
        text = ".OP";
    }

    @Override
    public void visit(Transient trans) {
        StringBuilder builder = new StringBuilder();
        builder.append(".TRAN" + SEPARATOR + trans.getStopTime());

        for (String modifier : trans.getModifiers()) {
            builder.append(SEPARATOR + modifier);
        }
        text = builder.toString();
    }

    @Override
    public void visit(Library library) {
        text = ".LIB" + " " + library.getPath();
    }

}
