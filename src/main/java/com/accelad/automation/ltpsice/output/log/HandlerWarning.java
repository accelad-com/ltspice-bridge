package com.accelad.automation.ltpsice.output.log;

class HandlerWarning extends LogLineHandler {

    private static final String HEADER = "WARNING: ";

    private final Log log;

    public HandlerWarning(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(HEADER)) {
            String warning = line.substring(HEADER.length());
            log.addWaring(warning);
            return true;
        }
        return false;
    }
}