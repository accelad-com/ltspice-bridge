package com.accelad.automation.ltpsice.output.raw;

import com.accelad.automation.ltpsice.output.raw.LTSpiceRaw.Builder;
class HandlerTitle extends RawLineHandler {

    private static final String TITLE_HEADER = "Title: ";

    private final LTSpiceRaw.Builder builder;

    public HandlerTitle(Builder builder) {
        this.builder = builder;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(TITLE_HEADER)) {
            String title = line.substring(TITLE_HEADER.length());
            builder.withTitle(title);
            return true;
        }
        return false;
    }
}