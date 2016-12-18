package com.accelad.automation.ltpsice.netlist.directives;

public class Measure implements Directive {

    private String name;

    private String expression;

    public Measure(String name, String expression) {
        this.name = name;
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public String getExpression() {
        return expression;
    }

    @Override
    public void accept(DirectiveVisitor visitor) {
        visitor.visit(this);
    }

}
