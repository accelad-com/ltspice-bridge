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
        return runSimulation(netlist, true);
    }

    @Override
    public LTSpiceResult runSimulation(Netlist netlist, boolean withTraces)
            throws LTSpiceException {
        LTSpiceExecutor executorInstance = executorProvider.getExecutor();
        ExecutorResult executorResult = executorInstance.runSimulation(netlist);

        Log log = executorResult.getLogFile()
                .map(this::readLogFile)
                .orElse(new Log());

        LTSpiceRaw raw;
        if (withTraces) {
            raw = executorResult.getRawFile()
                    .map(this::readRawFile)
                    .orElse(new LTSpiceRaw());

        } else {
            raw = new LTSpiceRaw();
        }
        
        LTSpiceState state = executorResult.getState();
        
        executorResult.getLogFile().ifPresent(File::delete);
        executorResult.getRawFile().ifPresent(File::delete);
        
        return new LTSpiceResult(state, raw, log);

    }

    private LTSpiceRaw readRawFile(File file) {
        LTSpiceRawParser rawParser = new LTSpiceRawParser();
        try {
            FileInputStream is = new FileInputStream(file);
            LTSpiceRaw raw = rawParser.read(is);
            is.close();
            return raw;
        } catch (IOException e) {
            LOGGER.warn("Impossible to open raw file", e);
            return new LTSpiceRaw();
        }
    }

    private Log readLogFile(File file) {
        LogParser logParser = new LogParser();
        try {
            FileInputStream stream = new FileInputStream(file);
            Log log = logParser.read(stream);
            stream.close();
            return log;
        } catch (IOException e) {
            LOGGER.warn("Impossible to open log file", e);
            return new Log();
        }
    }

}
