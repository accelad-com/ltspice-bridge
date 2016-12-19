package com.accelad.automation.ltpsice.output.raw;

import com.accelad.automation.ltpsice.output.raw.LTSpiceRaw.Builder;
class HandlerCommand extends RawLineHandler {

    private static final String COMMAND_HEADER = "Command: ";

    private final LTSpiceRaw.Builder builder;

    public HandlerCommand(Builder builder) {
        this.builder = builder;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(COMMAND_HEADER)) {
            String text = line.substring(COMMAND_HEADER.length());
            builder.withCommand(text);
            return true;
        }

        return false;
    }
}