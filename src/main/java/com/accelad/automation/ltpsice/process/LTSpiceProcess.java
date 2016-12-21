package com.accelad.automation.ltpsice.process;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LTSpiceProcess {
    private static final int PROCESS_STATUS_OK = 0;
    private static final Logger LOGGER = LoggerFactory.getLogger(LTSpiceProcess.class);
    private final ProcessBuilder processBuilder;

    public LTSpiceProcess(ExecutableFile executableFile, CircuitFile circuit) {

        ProcessCommandBuilder commandBuilder;
        if (SystemUtils.IS_OS_WINDOWS) {
            commandBuilder = new ProcessCommandBuilderWin();
        } else if (SystemUtils.IS_OS_LINUX) {
            commandBuilder = new ProcessCommandBuilderLinux();
        } else {
            throw new RuntimeException("the system OS is not supported");
        }

        List<String> commands = commandBuilder.createProcessCommands(executableFile, circuit,
                getCommandLineSwitches());
        processBuilder = new ProcessBuilder(commands);
        processBuilder.inheritIO();
    }

    public LTSpiceState run() {
        try {
            Process process = processBuilder.start();
            int exitValue = process.waitFor();

            switch (exitValue) {
            case PROCESS_STATUS_OK:
                return LTSpiceState.SIMULATION_OK;

            default:
                return LTSpiceState.SIMULATION_FAILED;
            }


        } catch (IOException e) {
            LOGGER.warn("Impossible to start LTSpice process due to I/O error", e);
            return LTSpiceState.SIMULATION_FAILED;
        } catch (InterruptedException e) {
            LOGGER.warn("The LTSpice process has been interrupted", e);
            return LTSpiceState.SIMULATION_FAILED;
        }
    }

    private List<CommandLineSwitch> getCommandLineSwitches() {
        return Arrays.asList(new BatchModeFlag());
    }
}
