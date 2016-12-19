package com.accelad.automation.ltpsice.output.log;

import java.util.ArrayList;
import java.util.List;

public class GminStepping {

    private List<Double> values = new ArrayList<>();
    private boolean success;

    public GminStepping(List<Double> values, boolean success) {
        this.values = values;
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Double> getValues() {
        return values;
    }

}
