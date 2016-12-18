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
    private File file;

    public LTSpiceExecutor(File file) {
        this.file = file;
    }

    public String getAbsolutePath() {
        return file.getAbsolutePath();
    }

    public LTSpiceState runSimulation(Netlist netlist) {

        try {
            File circuitPath = File.createTempFile("circuit", ".net", TEMP_DIR);
            saveNetlistToFile(netlist, circuitPath);
            LTSpiceProcess process = new LTSpiceProcess(this, circuitPath);
            LTSpiceState result = process.run();

            if (result == LTSpiceState.SIMULATION_OK) {
                circuitPath.delete();
            }
            else {
                saveNetlistForDebug(netlist);
            }
            return result;
        } catch (IOException e) {
            LOGGER.warn("Impossible to save the netlist in the file.", e);
        }
        return LTSpiceState.SIMULATION_FAILED;
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
