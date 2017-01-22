package com.accelad.automation.ltpsice.netlist.directives;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Parameters implements Iterable<Parameter> {
    private final List<Parameter> listOfParameter = new ArrayList<>();

    public void addAll(Parameters parameters) {
        listOfParameter.addAll(parameters.listOfParameter);
    }

    public void addAll(Collection<? extends Parameter> c) {
        listOfParameter.addAll(c);
    }

    public void add(Parameter e) {
        listOfParameter.add(e);
    }

    public void remove(Parameter o) {
        listOfParameter.remove(o);
    }

    @Override
    public Iterator<Parameter> iterator() {
        return listOfParameter.iterator();
    }
}
