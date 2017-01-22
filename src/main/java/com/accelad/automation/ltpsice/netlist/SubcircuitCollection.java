package com.accelad.automation.ltpsice.netlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class SubcircuitCollection implements Iterable<Subcircuit> {

    private final List<Subcircuit> subcircuits = new ArrayList<>();

    public boolean add(Subcircuit e) {
        return subcircuits.add(e);
    }

    public boolean remove(Object o) {
        return subcircuits.remove(o);
    }

    @Override
    public Iterator<Subcircuit> iterator() {
        return subcircuits.iterator();
    }

    public Optional<Subcircuit> findSubcircuit(String name) {
        return subcircuits.stream()
                .filter(cir -> name.equals(cir.getName()))
                .findAny();
    }
}
