package com.accelad.automation.ltpsice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.AccessController;
import java.util.Optional;

import com.accelad.automation.ltpsice.output.Log;
import com.accelad.automation.ltpsice.output.LogReader;
import com.accelad.automation.ltpsice.output.Measure;

import sun.security.action.GetPropertyAction;

public class Analyzer {


    public Optional<Double> analyze(Circuit circuit) {

        try {

            File tmpdir = new File(AccessController
                    .doPrivileged(new GetPropertyAction("java.io.tmpdir")));

            File circuitPath = new File(tmpdir, "circuit.net");
            FileOutputStream out = new FileOutputStream(circuitPath);
            new CircuitWriter().write(circuit, out);
            out.close();

            LTSpiceProcess process = new LTSpiceProcess(new PathLocator());
            boolean sucess = process.run(circuitPath);
            if (!sucess) {
                return Optional.empty();
            }

            circuitPath.delete();

            LogReader reader = new LogReader();

            String fileName = circuitPath.getName().replace(".net", ".log");
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

}
