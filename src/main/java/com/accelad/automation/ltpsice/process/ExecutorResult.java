package com.accelad.automation.ltpsice.process;

import java.io.File;
import java.util.Optional;

public class ExecutorResult {
    private final LTSpiceState state;
    private final File logFile;
    private final File rawFile;

    public ExecutorResult(LTSpiceState state) {
        this(state, null, null);
    }

    public ExecutorResult(LTSpiceState state, File logFile, File rawFile) {
        this.state = state;
        this.logFile = logFile;
        this.rawFile = rawFile;
    }

    public LTSpiceState getState() {
        return state;
    }

    public Optional<File> getLogFile() {
        return Optional.ofNullable(logFile);
    }

    public Optional<File> getRawFile() {
        return Optional.ofNullable(rawFile);
    }

}
