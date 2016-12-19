package com.accelad.automation.ltpsice.output.raw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ListOfTraces {

    private final List<Trace> traces = new ArrayList<>();

    public ListOfTraces(List<Trace> traces) {
        this.traces.addAll(traces);
    }

    public Trace get(int index) {
        return traces.get(index);
    }

    public Optional<Trace> findTraceByName(String name) {
        return traces.stream()
                .filter(trace -> trace.getName().equals(name))
                .findAny();
    }

    public int size() {
        return traces.size();
    }

    public static ListOfTraces buildFromSignals(List<Signal> signals, double[][] values) {
        List<Trace> traces = new ArrayList<>();
        for (int i = 0; i < signals.size(); i++) {
            Signal signal = signals.get(i);
            double[] data = values[i];
            List<Double> asList = Arrays.stream(data)
                    .boxed()
                    .collect(Collectors.toList());
            Trace trace = new Trace(signal, asList);
            traces.add(trace);
        }
        return new ListOfTraces(traces);
    }

}
