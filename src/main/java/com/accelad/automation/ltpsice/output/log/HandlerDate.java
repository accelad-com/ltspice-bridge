package com.accelad.automation.ltpsice.output.log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class HandlerDate extends LogLineHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerDate.class);
    private static final String DATE_HEADER = "Date: ";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy",
            Locale.US);
    private final Log log;

    public HandlerDate(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(DATE_HEADER)) {
            String dateString = line.substring(DATE_HEADER.length());
            try {
                Date date = dateFormat.parse(dateString);
                log.setDate(date);
                return true;
            } catch (ParseException e) {
                LOGGER.warn("Incorrect date format found in the raw file", e);
            }
        }

        return false;
    }
}