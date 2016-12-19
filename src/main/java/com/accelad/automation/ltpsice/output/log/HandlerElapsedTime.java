package com.accelad.automation.ltpsice.output.log;

class HandlerElapsedTime extends LogLineHandler {

    private static final String TOTAL_ELAPSED_TIME = "Total elapsed time: ";

    private final Log log;

    public HandlerElapsedTime(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(TOTAL_ELAPSED_TIME)) {
            String timeString = line.substring(TOTAL_ELAPSED_TIME.length()).split(" ")[0];
            double elapsedTime = Double.parseDouble(timeString);
            log.setElapsedTime(elapsedTime);
            return true;
        }
        return false;
    }
}