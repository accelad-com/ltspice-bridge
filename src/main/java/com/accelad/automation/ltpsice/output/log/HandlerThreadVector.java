package com.accelad.automation.ltpsice.output.log;

class HandlerThreadVector extends LogLineHandler {

    private static final String HEADER = "Thread vector: ";

    private final Log log;

    public HandlerThreadVector(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(HEADER)) {
            String threadVector = line.substring(HEADER.length());
            log.setThreadVector(threadVector);
            return true;
        }
        return false;
    }
}