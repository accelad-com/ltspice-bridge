package com.accelad.automation.ltpsice.output.raw;

import com.accelad.automation.ltpsice.output.raw.LTSpiceRaw.Builder;
class HandlerFlags extends RawLineHandler {
    private static final String FLAGS_HEADER = "Flags: ";
    private final LTSpiceRaw.Builder builder;

    public HandlerFlags(Builder builder) {
        this.builder = builder;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(FLAGS_HEADER)) {
            String flagTxtValue = line.substring(FLAGS_HEADER.length());
            Flag flag = Flag.valueOf(flagTxtValue.toUpperCase());
            builder.withFlags(new ListOfFlags(flag));
            return true;
        }

        return false;
    }
}