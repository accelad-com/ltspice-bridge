package com.accelad.automation.ltpsice.output.log;

class HandlerMatrixSize extends LogLineHandler {

    private static final String HEADER = "matrix size = ";

    private final Log log;

    public HandlerMatrixSize(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(HEADER)) {
            String valueAsText = line.substring(HEADER.length());
            int matrixSize = Integer.parseInt(valueAsText);
            log.setMatrixSize(matrixSize);
            return true;
        }
        return false;
    }
}