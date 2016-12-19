package com.accelad.automation.ltpsice.output.log;

class HandlerTransientIteration extends LogLineHandler {

    private static final String TRANSIENT_ITERATION_HEADER = "traniter = ";

    private final Log log;

    public HandlerTransientIteration(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(TRANSIENT_ITERATION_HEADER)) {
            String valueAsText = line.substring(TRANSIENT_ITERATION_HEADER.length());
            int transientIteration = Integer.parseInt(valueAsText);
            log.setTraniter(transientIteration);
            return true;
        }
        return false;
    }
}