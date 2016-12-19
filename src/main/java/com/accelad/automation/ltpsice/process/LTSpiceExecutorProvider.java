package com.accelad.automation.ltpsice.process;

import java.util.function.Supplier;

public class LTSpiceExecutorProvider {
    private final LTSpiceExecutableFileLocator locator;

    public LTSpiceExecutorProvider() {
        this(new LTSpiceExecutableFileLocator());
    }

    public LTSpiceExecutorProvider(LTSpiceExecutableFileLocator locator) {
        this.locator = locator;
    }

    public LTSpiceExecutor getExecutor() throws LTSpiceException {
        return locator.locateExecutableFile()
                .map(LTSpiceExecutor::new)
                .orElseThrow(createLTSpiceNotFoundException());
    }

    private Supplier<? extends LTSpiceException> createLTSpiceNotFoundException() {
        return () -> new LTSpiceException(
                "Impossible to locate LTSpice. Please check if it has been installed on this computer.");
    }

}
