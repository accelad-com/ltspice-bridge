package com.accelad.automation.ltpsice.output.raw;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.accelad.automation.ltpsice.output.raw.ListOfTraces;
import com.accelad.automation.ltpsice.output.raw.Signal;
import com.accelad.automation.ltpsice.output.raw.Trace;

public class ListOfTracesTest {

    @Test
    public void shouldReturnAListOfTraceWhenAListOfSignalsAndValuesAreGiven() throws Exception {
        Signal firstSignal = new Signal(0, "first", "1st", "mytype");
        Signal secondSignal = new Signal(0, "second", "2nd", "mytype");
        List<Signal> signals = Arrays.asList(firstSignal, secondSignal);
        
        double[][] values = new double[][] { { 0, 1, 2, 3 }, { 1, 1.5, 2.5, 3.5 } };
        ListOfTraces traces = ListOfTraces.buildFromSignals(signals, values);

        assertEquals(2, traces.size());
        Trace trace = traces.get(0);
        assertEquals(3d, trace.getValues().get(3), 1e-12);
    }

}
