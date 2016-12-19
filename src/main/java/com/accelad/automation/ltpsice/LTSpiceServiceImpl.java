package com.accelad.automation.ltpsice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accelad.automation.ltpsice.netlist.Netlist;
import com.accelad.automation.ltpsice.output.log.Log;
import com.accelad.automation.ltpsice.output.log.LogParser;
import com.accelad.automation.ltpsice.output.raw.LTSpiceRaw;
import com.accelad.automation.ltpsice.output.raw.LTSpiceRawParser;
import com.accelad.automation.ltpsice.process.ExecutorResult;
import com.accelad.automation.ltpsice.process.LTSpiceException;
import com.accelad.automation.ltpsice.process.LTSpiceExecutableFileLocator;
import com.accelad.automation.ltpsice.process.LTSpiceExecutor;
import com.accelad.automation.ltpsice.process.LTSpiceExecutorProvider;
import com.accelad.automation.ltpsice.process.LTSpiceState;

public class LTSpiceServiceImpl implements LTSpiceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LTSpiceExecutor.class);

    private final LTSpiceExecutorProvider executorProvider;
    private final LTSpiceExecutableFileLocator locator;

    public LTSpiceServiceImpl() {
        this(new LTSpiceExecutorProvider(), new LTSpiceExecutableFileLocator());
    }

    public LTSpiceServiceImpl(LTSpiceExecutorProvider executorProvider,
            LTSpiceExecutableFileLocator locator) {
        this.executorProvider = executorProvider;
        this.locator = locator;
    }

    @Override
    public boolean isAvailable() {
        return locator.locateExecutableFile().isPresent();
    }

    @Override
    public LTSpiceResult runSimulation(Netlist netlist) throws LTSpiceException {
        LTSpiceExecutor executorInstance = executorProvider.getExecutor();
        ExecutorResult executorResult = executorInstance.runSimulation(netlist);
        LogParser logParser = new LogParser();

        Log log = executorResult.getLogFile()
                .map(file -> readLogFile(logParser, file))
                .orElse(new Log());

        LTSpiceRawParser rawParser = new LTSpiceRawParser();
        LTSpiceRaw raw = executorResult.getRawFile()
                .map(file -> readRawFile(rawParser, file))
                .orElse(new LTSpiceRaw());

        LTSpiceState state = executorResult.getState();
        return new LTSpiceResult(state, raw, log);

    }

    private LTSpiceRaw readRawFile(LTSpiceRawParser rawParser, File file) {
        try {
            return rawParser.read(new FileInputStream(file));
        } catch (IOException e) {
            LOGGER.warn("Impossible to open raw file", e);
            return null;
        }
    }

    private Log readLogFile(LogParser logParser, File file) {
        try {
            return logParser.read(new FileInputStream(file));
        } catch (IOException e) {
            LOGGER.warn("Impossible to open log file", e);
            return null;
        }
    }

}
