package com.accelad.automation.ltpsice.output.log;

class HandlerHeightenedDefCon extends LogLineHandler {

    private static final String HEADER = "Heightened Def Con from ";

    private final Log log;

    public HandlerHeightenedDefCon(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(HEADER)) {
            String subLine = line.substring(HEADER.length());
            String fromValueAsText = subLine.split(" ")[0];
            String toValueAsText = subLine.split(" ")[2];

            double fromValue = Double.parseDouble(fromValueAsText);
            double toValue = Double.parseDouble(toValueAsText);
            HeightenedDefCon defCon = new HeightenedDefCon(fromValue, toValue);
            log.addDefCon(defCon);
            return true;
        }
        return false;
    }
}