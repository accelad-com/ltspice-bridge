package com.accelad.automation.ltpsice;

import java.util.List;

public class Circuit {

    private List<String> lines;

    public Circuit(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getLines() {
        return lines;
    }

}
