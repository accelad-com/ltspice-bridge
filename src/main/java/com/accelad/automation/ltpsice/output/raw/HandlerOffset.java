package com.accelad.automation.ltpsice.output.raw;

import com.accelad.automation.ltpsice.output.raw.LTSpiceRaw.Builder;
class HandlerOffset extends RawLineHandler {

    private static final String OFFSET_HEADER = "Offset: ";

    private final LTSpiceRaw.Builder builder;

    public HandlerOffset(Builder builder) {
        this.builder = builder;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(OFFSET_HEADER)) {
            String text = line.substring(OFFSET_HEADER.length());
            builder.withOffset(Double.parseDouble(text));
            return true;
        }
        return false;
    }
}