package com.accelad.automation.ltpsice.output.log;

class HandlerTotalIteration extends LogLineHandler {

    private static final String TOT_ITER_HEADER = "totiter = ";

    private final Log log;

    public HandlerTotalIteration(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(TOT_ITER_HEADER)) {
            String valueAsText = line.substring(TOT_ITER_HEADER.length());
            int totalIterationValue = Integer.parseInt(valueAsText);
            log.setTotiter(totalIterationValue);
            return true;
        }
        return false;
    }
}