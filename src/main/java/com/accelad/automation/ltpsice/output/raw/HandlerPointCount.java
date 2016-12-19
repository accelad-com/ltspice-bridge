package com.accelad.automation.ltpsice.output.raw;

import com.accelad.automation.ltpsice.output.raw.LTSpiceRaw.Builder;
class HandlerPointCount extends RawLineHandler {

    private static final String NO_POINTS_HEADER = "No. Points: ";

    private final LTSpiceRaw.Builder builder;

    public HandlerPointCount(Builder builder) {
        this.builder = builder;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(NO_POINTS_HEADER)) {
            String count = line.substring(NO_POINTS_HEADER.length()).trim();
            int pointCount = Integer.parseInt(count);
            builder.withPointCount(pointCount);
            return true;
        }
        return false;
    }
}