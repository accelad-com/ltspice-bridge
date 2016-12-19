package com.accelad.automation.ltpsice.process;

import java.io.File;

public class ExecutableFile {

    private File file;

    public ExecutableFile(File file) {
        this.file = file;
    }

    public String getAbsolutePath() {
        return file.getAbsolutePath();
    }

}
