package com.accelad.automation.ltpsice.output.log;

class HandlerSkipOperatingPoint extends LogLineHandler {

    private static final String HEADER = "Per .tran options, skipping operating point for transient analysis.";

    private final Log log;

    public HandlerSkipOperatingPoint(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (HEADER.equals(line)) {
            log.setSkipOperatingPoint(true);
            return true;
        }
        return false;
    }
}