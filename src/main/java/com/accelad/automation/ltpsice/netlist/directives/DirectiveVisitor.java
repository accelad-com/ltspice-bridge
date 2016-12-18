package com.accelad.automation.ltpsice.netlist.directives;

public interface DirectiveVisitor {
    public void visit(UserDefinedParameter param);

    public void visit(UserDefinedFunction function);

    public void visit(Measure meas);

    public void visit(OperatingPoint operatingPoint);

    public void visit(Transient trans);

    public void visit(Library library);

}
