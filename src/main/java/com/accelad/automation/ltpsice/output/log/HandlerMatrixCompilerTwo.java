package com.accelad.automation.ltpsice.output.log;

class HandlerMatrixCompilerTwo extends LogLineHandler {

    private static final String HEADER = "Matrix Compiler2: ";

    private final Log log;

    public HandlerMatrixCompilerTwo(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(HEADER)) {
            String compilertwo = line.substring(HEADER.length());
            log.setCompiler2(compilertwo);
            return true;
        }
        return false;
    }
}