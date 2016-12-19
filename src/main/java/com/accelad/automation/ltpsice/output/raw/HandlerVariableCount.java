package com.accelad.automation.ltpsice.output.raw;

import com.accelad.automation.ltpsice.output.raw.LTSpiceRaw.Builder;
class HandlerVariableCount extends RawLineHandler {

    private static final String NO_VARIABLES_HEADER = "No. Variables: ";

    private final LTSpiceRaw.Builder builder;

    public HandlerVariableCount(Builder builder) {
        this.builder = builder;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(NO_VARIABLES_HEADER)) {
            String count = line.substring(NO_VARIABLES_HEADER.length());
            int variableCount = Integer.parseInt(count);
            builder.withVariableCount(variableCount);
            return true;
        }
        return false;
    }
}