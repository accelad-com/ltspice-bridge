package com.accelad.automation.ltpsice.output.log;

class HandlerMethod extends LogLineHandler {

    private static final String HEADER = "method = ";

    private final Log log;

    public HandlerMethod(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(HEADER)) {
            String method = line.substring(HEADER.length());
            log.setMethod(method);
            return true;
        }
        return false;
    }
}