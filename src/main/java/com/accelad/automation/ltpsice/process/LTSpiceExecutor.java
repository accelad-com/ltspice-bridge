package com.accelad.automation.ltpsice.process;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accelad.automation.ltpsice.netlist.Netlist;
import com.accelad.automation.ltpsice.netlist.NetlistWriter;

public class LTSpiceExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(LTSpiceExecutor.class);
    private static final String CIRCUIT_FILE_NAME = "circuit.net";
    private static final File TEMP_DIR = new File(SystemUtils.JAVA_IO_TMPDIR);
    private ExecutableFile executable;

    public LTSpiceExecutor(ExecutableFile executable) {
        this.executable = executable;
    }

    public ExecutableFile getExecutable() {
        return executable;
    }

    public ExecutorResult runSimulation(Netlist netlist) {
        File circuitPath = null;
        try {
            circuitPath = File.createTempFile("circuit", ".net", TEMP_DIR);
        } catch (IOException e1) {
            LOGGER.error("Impossible to save the circuit on the drive", e1);
            return new ExecutorResult(LTSpiceState.SIMULATION_FAILED);
        }

        try {
            saveNetlistToFile(netlist, circuitPath);
            LTSpiceProcess process = new LTSpiceProcess(executable, circuitPath);
            LTSpiceState result = process.run();

            if (result == LTSpiceState.SIMULATION_OK) {
                // circuitPath.delete();
            }
            else {
                saveNetlistForDebug(netlist);
            }
            return buildResult(result, circuitPath);
        } catch (IOException e) {
            LOGGER.warn("Impossible to save the netlist in the file.", e);
        }
        return buildResult(LTSpiceState.SIMULATION_FAILED, circuitPath);
    }

    private ExecutorResult buildResult(LTSpiceState result, File circuitFile) {
        File logFile = getLogFile(circuitFile);
        File rawFile = getRawFile(circuitFile);
        return new ExecutorResult(result, logFile, rawFile);
    }

    private File getLogFile(File circuitFile) {
        String logFileName = circuitFile.getName().replace(".net", ".log");
        return new File(circuitFile.getParentFile(), logFileName);
    }

    private File getRawFile(File circuitFile) {
        String rawFileName = circuitFile.getName().replace(".net", ".raw");
        return new File(circuitFile.getParentFile(), rawFileName);
    }

    private void saveNetlistForDebug(Netlist netlist) throws IOException {
        File debugNetlistFile = new File(TEMP_DIR, CIRCUIT_FILE_NAME);
        saveNetlistToFile(netlist, debugNetlistFile);
        LOGGER.warn("Unable to simulate the circuit : " + debugNetlistFile.getAbsolutePath());
    }

    private void saveNetlistToFile(Netlist netlist, File circuitPath) throws IOException {
        FileWriter fileWriter = new FileWriter(circuitPath);
        NetlistWriter netlistWriter = new NetlistWriter();
        netlistWriter.write(netlist, fileWriter);
        fileWriter.close();
    }

}
