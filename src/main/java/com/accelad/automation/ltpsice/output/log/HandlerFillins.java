package com.accelad.automation.ltpsice.output.log;

class HandlerFillins extends LogLineHandler {

    private static final String HEADER = "fillins = ";

    private final Log log;

    public HandlerFillins(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(HEADER)) {
            String valueAsText = line.substring(HEADER.length());
            int fillins = Integer.parseInt(valueAsText);
            log.setFillins(fillins);
            return true;
        }
        return false;
    }
}