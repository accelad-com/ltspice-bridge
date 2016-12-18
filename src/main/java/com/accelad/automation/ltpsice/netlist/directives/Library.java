package com.accelad.automation.ltpsice.netlist.directives;

public class Library implements Directive {

    private String path;

    public Library(String libraryPath) {
        this.path = libraryPath;
    }

    public String getPath() {
        return path;
    }

    @Override
    public void accept(DirectiveVisitor visitor) {
        visitor.visit(this);
    }

}
