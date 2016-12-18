package com.accelad.automation.ltpsice.process;

public class BatchModeFlag implements CommandLineSwitch {

    private static final String BATCH_MODE_FLAG = "-b";

    @Override
    public String toString() {
        return BATCH_MODE_FLAG;
    }
}
