package com.accelad.automation.ltpsice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

public class Runner {

    public static void main(String[] args) throws FileNotFoundException {
        File circuitPath = new CircuitLocator().locate();

        Circuit circuit = new CircuitReader().read(new FileInputStream(circuitPath));

        // SensitivityAnalysis sensitivityAnalysis = new SensitivityAnalysis();
        // sensitivityAnalysis.analyse(circuit);

        for (double i = .1; i < 0.11; i += 0.1) {
            Circuit modified = new CircuitModifier().modify(circuit, "I1", i);
            Analyzer analyzer = new Analyzer();
            Optional<Double> value = analyzer.analyze(modified);

            String val = value.map(v -> Double.toString(v)).orElse("not found");
            System.out.println(i + "\t" + val);

        }
    }

    private String getPulse4B_1() {
        return "V1 N016 0 PWL(1m 12 2m 5 17m 5 67m 6.5 2067m 6.5 2167m 12)";
    }

    private String getPulse4B_2() {
        return "V1 N016 0 PWL(1m 12 2m 3 17m 3 67m 5 1067m 5 1167m 12)";
    }

    private String getPulse4B_3() {
        return "V1 N016 0 PWL(1m 12 2m 4.5 17m 4.5 67m 6.5 10067m 6.5 10167m 12)";
    }

    private String getPulse4B_4() {
        return "V1 N016 0 PWL(1m 12 2m 3 17m 3 67m 5 1067m 5 1167m 12)";
    }

    private String getPulse4B_5() {
        return "V1 N016 0 PWL(1m 12 2m 6 17m 6 67m 6.5 10067m 6.5 10167m 12)";
    }

    private String getPulse4B_6() {
        return "V1 N016 0 PWL(1m 12 2m 3 17m 3 67m 3 167m 3 267m 12)";
    }

    private String getPulse4B_7() {
        return "V1 N016 0 PWL(1m 12 2m 3 17m 3 67m 3 1067m 3 2067m 12)";
    }

    private String getPulse4B_8() {
        return "V1 N016 0 PWL(1m 12 2m 3 17m 3 67m 3 10067m 3 20067m 12)";
    }

}
