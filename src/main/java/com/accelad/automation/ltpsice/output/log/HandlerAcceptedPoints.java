package com.accelad.automation.ltpsice.output.log;

class HandlerAcceptedPoints extends LogLineHandler {

    private static final String HEADER = "accept = ";

    private final Log log;

    public HandlerAcceptedPoints(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(HEADER)) {
            String valueAsText = line.substring(HEADER.length());
            int acceptedPoints = Integer.parseInt(valueAsText);
            log.setAccept(acceptedPoints);
            return true;
        }
        return false;
    }
}