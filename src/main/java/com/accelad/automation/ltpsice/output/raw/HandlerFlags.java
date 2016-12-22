package com.accelad.automation.ltpsice.output.raw;

import java.util.Arrays;

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
            String flagsString = line.substring(FLAGS_HEADER.length());
            String[] flagNamesArray = flagsString.split(" ");

            ListOfFlags listOfFlags = new ListOfFlags();
            Arrays.stream(flagNamesArray)
                    .map(String::toUpperCase)
                    .map(Flag::valueOf)
                    .forEach(listOfFlags::add);

            builder.withFlags(listOfFlags);
            return true;
        }

        return false;
    }
}