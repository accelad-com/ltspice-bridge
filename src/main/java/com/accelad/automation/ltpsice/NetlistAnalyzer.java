package com.accelad.automation.ltpsice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.AccessController;
import java.util.Optional;

import com.accelad.automation.ltpsice.netlist.Netlist;
import com.accelad.automation.ltpsice.netlist.NetlistWriter;
import com.accelad.automation.ltpsice.output.Log;
import com.accelad.automation.ltpsice.output.LogReader;
import com.accelad.automation.ltpsice.output.Measure;

import sun.security.action.GetPropertyAction;

public class NetlistAnalyzer {

    private LTSpiceProcess process;
    private File tmpdir;

    public NetlistAnalyzer() {
        process = new LTSpiceProcess(new PathLocator());
        tmpdir = new File(AccessController
                .doPrivileged(new GetPropertyAction("java.io.tmpdir")));
    }

    public Optional<Double> analyze(Netlist netlist) {

        try {
            File circuitPath = new File(tmpdir, "circuit.net");
            FileWriter fileWriter = new FileWriter(circuitPath);
            new NetlistWriter().write(netlist, fileWriter);
            fileWriter.close();

            System.out.println(circuitPath);


            boolean sucess = process.run(circuitPath);
            if (!sucess) {
                System.out.println("compute failed");
                return Optional.empty();
            }

            circuitPath.delete();

            LogReader reader = new LogReader();

            String fileName = getLogFilename(circuitPath);
            File file = new File(circuitPath.getParent(), fileName);
            Log log = reader.read(new FileInputStream(file));
            return log.findMeasureByName("test")
                    .map(Measure::getValue);

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return Optional.empty();

    }

    private String getLogFilename(File circuitPath) {
        String fileName = circuitPath.getName().replace(".net", ".log");
        return fileName;
    }

}
