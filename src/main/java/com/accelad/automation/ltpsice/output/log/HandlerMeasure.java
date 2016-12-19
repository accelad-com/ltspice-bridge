package com.accelad.automation.ltpsice.output.log;

class HandlerMeasure extends LogLineHandler {
    private final Log log;

    public HandlerMeasure(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.contains(": ") && line.contains("=")) {
            String[] parameters = line.split(" ");
            String name = parameters[0].substring(0, parameters[0].length() - 1);
            String definition = parameters[0].split("=")[0];
            double value = Double.parseDouble(parameters[1].split("=")[1]);

            Measure measure = new Measure(name, definition, value);
            log.addMeasure(measure);
            return true;
        }
        return false;
    }
}