package com.accelad.automation.ltpsice.netlist.builder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.accelad.automation.ltpsice.netlist.element.Element;

public class ElementBuilder {
    private Map<String, ElementBuilderDelegate> delegates = new HashMap<>();

    public ElementBuilder() {
        delegates.put("R", new ResistorBuilder());
    }

    public Optional<Element> build(String text) {
        String trimmedText = text.trim();
        String firstLetter = text.substring(0, 1);
        Optional<ElementBuilderDelegate> delegate = Optional.ofNullable(delegates.get(firstLetter));
        return delegate.map(d -> d.build(trimmedText));
    }
}
