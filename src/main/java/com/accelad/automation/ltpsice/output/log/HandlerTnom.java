package com.accelad.automation.ltpsice.output.log;

class HandlerTnom extends LogLineHandler {

    private static final String TNOM_HEADER = "tnom = ";

    private final Log log;

    public HandlerTnom(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(TNOM_HEADER)) {
            String tnomAsText = line.substring(TNOM_HEADER.length());
            double tnom = Double.parseDouble(tnomAsText);
            log.setTnom(tnom);
            return true;
        }
        return false;
    }
}