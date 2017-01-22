package com.accelad.automation.ltpsice.output.log;

class HandlerChangeTseed extends LogLineHandler {

    private static final String OFFSET_HEADER = "Changing Tseed ";

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(OFFSET_HEADER)) {
            return true;
        }
        return false;
    }
}