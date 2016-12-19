package com.accelad.automation.ltpsice.output.raw;

import java.util.ArrayList;
import java.util.List;

public class Trace {
    private final Signal signal;
    private final List<Double> values = new ArrayList<>();

    public Trace(Signal signal, List<Double> values) {
        this.values.addAll(values);
        this.signal = signal;
    }

    public String getName() {
        return signal.getName();
    }

    public Signal getSignal() {
        return signal;
    }

    public List<Double> getValues() {
        return values;
    }
}
