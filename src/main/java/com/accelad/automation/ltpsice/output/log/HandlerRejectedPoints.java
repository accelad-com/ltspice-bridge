package com.accelad.automation.ltpsice.output.log;

class HandlerRejectedPoints extends LogLineHandler {

    private static final String HEADER = "rejected = ";

    private final Log log;

    public HandlerRejectedPoints(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(HEADER)) {
            String valueAsText = line.substring(HEADER.length());
            int rejectedPoints = Integer.parseInt(valueAsText);
            log.setRejected(rejectedPoints);
            return true;
        }
        return false;
    }
}