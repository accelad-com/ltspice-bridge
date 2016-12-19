package com.accelad.automation.ltpsice.output.log;

class HandlerDirectNewtonIterationSuccess extends LogLineHandler {

    private static final String SUCESS_HEADER = "Direct Newton iteration for .op point succeeded.";
    private static final String FAILED_HEADER = "Direct Newton iteration failed to find .op point.  (Use \".option noopiter\" to skip.)";

    private final Log log;

    public HandlerDirectNewtonIterationSuccess(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (SUCESS_HEADER.equals(line)) {
            log.setDirectNewtonIterationSuccess(true);
            return true;
        }
        else if (FAILED_HEADER.equals(line)) {
            log.setDirectNewtonIterationSuccess(false);
            return true;
        }
        return false;
    }
}