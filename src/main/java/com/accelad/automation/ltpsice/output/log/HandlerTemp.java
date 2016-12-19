package com.accelad.automation.ltpsice.output.log;

class HandlerTemp extends LogLineHandler {

    private static final String TEMP_HEADER = "temp = ";

    private final Log log;

    public HandlerTemp(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(TEMP_HEADER)) {
            String tempAsText = line.substring(TEMP_HEADER.length());
            double temp = Double.parseDouble(tempAsText);
            log.setTemp(temp);
            return true;
        }
        return false;
    }
}