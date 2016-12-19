package com.accelad.automation.ltpsice.output.log;

class HandlerSolver extends LogLineHandler {

    private static final String HEADER = "solver = ";

    private final Log log;

    public HandlerSolver(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(HEADER)) {
            String solver = line.substring(HEADER.length());
            log.setSolver(solver);
            return true;
        }
        return false;
    }
}