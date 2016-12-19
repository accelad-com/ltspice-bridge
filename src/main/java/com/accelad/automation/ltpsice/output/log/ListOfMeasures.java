package com.accelad.automation.ltpsice.output.log;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ListOfMeasures {
    private final Map<String, Measure> measures = new HashMap<>();

    void addMeasure(Measure measure) {
        measures.put(measure.getName(), measure);
    }

    public Optional<Measure> getMeasure(String name) {
        return Optional.ofNullable(measures.get(name));
    }

    public Collection<Measure> getMeasures() {
        return Collections.unmodifiableCollection(measures.values());
    }

}
