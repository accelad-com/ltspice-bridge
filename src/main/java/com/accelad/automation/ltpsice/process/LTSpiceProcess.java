package com.accelad.automation.ltpsice.process;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LTSpiceProcess {
    private static final String BATCH_MODE_FLAG = "-b";
    private static final int PROCESS_STATUS_OK = 0;
    private static final Logger LOGGER = LoggerFactory.getLogger(LTSpiceProcess.class);
    private final ProcessBuilder processBuilder;

    public LTSpiceProcess(LTSpiceExecutor executor, File circuit) {
        List<String> commands = createProcessCommands(executor, circuit);
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

    List<String> createProcessCommands(LTSpiceExecutor executor, File circuit) {
        List<String> commands = new ArrayList<>();
        commands.add(executor.getAbsolutePath());

        getCommandLineSwitches().stream()
                .map(CommandLineSwitch::toString)
                .forEach(commands::add);

        commands.add(circuit.getAbsolutePath());
        return commands;
    }

}
