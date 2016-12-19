package com.accelad.automation.ltpsice.output.raw;

import com.accelad.automation.ltpsice.output.raw.LTSpiceRaw.Builder;
class HandlerPlotname extends RawLineHandler {
    private static final String PLOTNAME_HEADER = "Plotname: ";
    private final LTSpiceRaw.Builder builder;

    public HandlerPlotname(Builder builder) {
        this.builder = builder;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(PLOTNAME_HEADER)) {
            String name = line.substring(PLOTNAME_HEADER.length());
            builder.withPlotName(name);
            return true;
        }

        return false;
    }
}