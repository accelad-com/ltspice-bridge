package com.accelad.automation.ltpsice.output.log;

class HandlerOPPointFoundByInspection extends LogLineHandler {

    private static final String HEADER = ".OP point found by inspection.";

    private final Log log;

    public HandlerOPPointFoundByInspection(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (HEADER.equals(line)) {
            log.setOpPointFoundByInspection(true);
            return true;
        }
        return false;
    }
}