package com.accelad.automation.ltpsice.netlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.accelad.automation.ltpsice.netlist.element.Element;

public class ElementCollection implements Iterable<Element> {

    private final List<Element> elements = new ArrayList<>();

    public void add(Element e) {
        elements.add(e);
    }

    public void remove(Element o) {
        elements.remove(o);
    }

    @Override
    public Iterator<Element> iterator() {
        return elements.iterator();
    }

    public Optional<Element> findElementByName(InstanceName name) {
        return elements.stream()
                .filter(element -> name.equals(element.getInstanceName()))
                .findAny();
    }

    public void removeElementByName(InstanceName name) {
        elements.stream()
                .filter(element -> name.equals(element.getInstanceName()))
                .findAny()
                .ifPresent(elements::remove);
    }

    public void addAll(List<Element> elements) {
        this.elements.addAll(elements);
    }

}
