package com.accelad.automation.ltpsice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SensitivityAnalysis {

    private final ModelValueModifier valueModifier = new ModelValueModifier();
    private final Analyzer analyzer = new Analyzer();

    public Map<String, Double> analyse(Circuit circuit) {

        Double typValue = analyzer.analyze(circuit).get();

        List<String> filteredLines = findDiscrete(circuit.getLines());

        Map<String, Double> map = new HashMap<>();

        for (String line : filteredLines) {
            double value = getValue(line);
            String ref = line.split(" ")[0];


            List<String> modified = new ArrayList<>(circuit.getLines());
            modified.set(getLine(ref, modified), valueModifier.modify(line, value * 0.8));
            Circuit newCir = new Circuit(modified);

            Double modifiedValue = analyzer.analyze(newCir).get();

            map.put(ref, modifiedValue / typValue);

            System.out.println(ref + "\t" + value + "\t" + (value * 0.8) + "\t" + typValue + "\t"
                    + modifiedValue + "\t" + modifiedValue / typValue);
        }

        return map;

    }

    private int getLine(String ref, List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            if (line.startsWith(ref)) {
                return i;
            }
        }

        return -1;
    }

    private List<String> findDiscrete(List<String> lines) {
        return lines.stream()
                .filter(l -> l.startsWith("C") || l.startsWith("L") || l.startsWith("R"))
                .collect(Collectors.toList());
    }

    private double getValue(String line) {
        String sVal = line.split(" ")[3];
        sVal = sVal.replaceAll("µ", "u");
        sVal = sVal.replaceAll("Meg", "M");
        sVal = sVal.replaceAll("M", "m");
        return ComponentValueConverter.getInstance().convert(sVal);
    }

}
