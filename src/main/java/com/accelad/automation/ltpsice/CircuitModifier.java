package com.accelad.automation.ltpsice;

import java.util.ArrayList;
import java.util.List;

public class CircuitModifier {

    private final ModelValueModifier modifier = new ModelValueModifier();

    public Circuit modify(Circuit cir, String ref, double value) {
        List<String> lines = new ArrayList<>(cir.getLines());
        int lineIndex = locateLine(lines, ref);

        String modifiedLine = modifier.modify(lines.get(lineIndex), value);

        lines.set(lineIndex, modifiedLine);

        return new Circuit(lines);
    }

    private int locateLine(List<String> lines, String ref) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith(ref)) {
                return i;
            }
        }
        return -1;
    }

}
