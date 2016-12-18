package com.accelad.automation.ltpsice.process;

import java.io.File;

public class IniFileFlag implements CommandLineSwitch {

    private static final String FLAG = "-ini";

    private final File iniFilePath;

    public IniFileFlag(File iniFilePath) {
        this.iniFilePath = iniFilePath;
    }

    @Override
    public String toString() {
        return FLAG + " \"" + iniFilePath.getAbsolutePath() + "\"";
    }
}
