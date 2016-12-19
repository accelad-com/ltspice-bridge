package com.accelad.automation.ltpsice;

import java.util.Collection;
import java.util.Optional;

import com.accelad.automation.ltpsice.output.log.Log;
import com.accelad.automation.ltpsice.output.log.Measure;
import com.accelad.automation.ltpsice.output.raw.LTSpiceRaw;
import com.accelad.automation.ltpsice.process.LTSpiceState;

public class LTSpiceResult {

    private final LTSpiceState state;
    private final LTSpiceRaw raw;
    private final Log log;

    public LTSpiceResult(LTSpiceState state, LTSpiceRaw raw, Log log) {
        this.state = state;
        this.raw = raw;
        this.log = log;
    }

    public LTSpiceState getState() {
        return state;
    }

    public LTSpiceRaw getRaw() {
        return raw;
    }

    public Log getLog() {
        return log;
    }

    public Collection<Measure> getMeasures() {
        return log.getMeasures();
    }

    public Optional<Measure> findMeasureByName(String name) {
        return log.findMeasureByName(name);
    }

}
