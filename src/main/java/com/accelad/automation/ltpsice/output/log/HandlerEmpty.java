package com.accelad.automation.ltpsice.output.log;

class HandlerEmpty extends LogLineHandler {

    @Override
    public boolean operationSpec(String line) {
        if (line.isEmpty()) {
            return true;
        }
        return false;
    }
}