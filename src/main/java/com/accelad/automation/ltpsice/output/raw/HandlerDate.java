package com.accelad.automation.ltpsice.output.raw;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accelad.automation.ltpsice.output.raw.LTSpiceRaw.Builder;

class HandlerDate extends RawLineHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerDate.class);
    private static final String DATE_HEADER = "Date: ";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy",
            Locale.US);
    private final LTSpiceRaw.Builder builder;

    public HandlerDate(Builder builder) {
        this.builder = builder;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(DATE_HEADER)) {
            String dateString = line.substring(DATE_HEADER.length());
            try {
                builder.withDate(dateFormat.parse(dateString));
                return true;
            } catch (ParseException e) {
                LOGGER.warn("Incorrect date format found in the raw file", e);
            }
        }

        return false;
    }
}