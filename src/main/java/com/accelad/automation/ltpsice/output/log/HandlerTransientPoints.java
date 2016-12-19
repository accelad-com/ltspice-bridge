package com.accelad.automation.ltpsice.output.log;

class HandlerTransientPoints extends LogLineHandler {

    private static final String TRANSIENT_POINTS_HEADER = "tranpoints = ";

    private final Log log;

    public HandlerTransientPoints(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(TRANSIENT_POINTS_HEADER)) {
            String valueAsText = line.substring(TRANSIENT_POINTS_HEADER.length());
            int transientPoints = Integer.parseInt(valueAsText);
            log.setTranpoints(transientPoints);
            return true;
        }
        return false;
    }
}