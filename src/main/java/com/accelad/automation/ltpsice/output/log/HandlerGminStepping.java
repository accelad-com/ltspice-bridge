package com.accelad.automation.ltpsice.output.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class HandlerGminStepping extends LogLineHandler {

    private static final String GMIN_HEADER = "Gmin = ";

    private static final String HEADER = "Starting Gmin stepping";
    private static final String SUCCESS = "Gmin stepping succeeded in finding the operating point.";

    private final Log log;
    private final BufferedReader reader;

    public HandlerGminStepping(Log log, BufferedReader reader) {
        this.log = log;
        this.reader = reader;
    }

    @Override
    public boolean operationSpec(String line) throws IOException {
        if (HEADER.equals(line)) {
            GminStepping gminStepping = extractGminStepping();
            log.setGminStepping(gminStepping);
            return true;
        }
        return false;
    }

    private GminStepping extractGminStepping() throws IOException {
        String innerLine;

        List<Double> values = new ArrayList<>();
        while ((innerLine = reader.readLine()) != null) {
            if (innerLine.startsWith(GMIN_HEADER)) {
                String gminValueAsText = innerLine.substring(GMIN_HEADER.length());
                double gminValue = Double.parseDouble(gminValueAsText);
                values.add(gminValue);
            }
            else {
                break;
            }
        }
        boolean success = SUCCESS.equals(innerLine);

        return new GminStepping(values, success);
    }
}