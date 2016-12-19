package com.accelad.automation.ltpsice.output.log;

class HandlerCircuit extends LogLineHandler {

    private static final String CIRCUIT_HEADER = "Circuit: ";

    private final Log log;

    public HandlerCircuit(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(CIRCUIT_HEADER)) {
            String title = line.substring(CIRCUIT_HEADER.length());
            log.setTitle(title);
            return true;
        }
        return false;
    }
}