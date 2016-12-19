package com.accelad.automation.ltpsice.output.log;

class HandlerMatrixCompilerOne extends LogLineHandler {

    private static final String HEADER = "Matrix Compiler1: ";

    private final Log log;

    public HandlerMatrixCompilerOne(Log log) {
        this.log = log;
    }

    @Override
    public boolean operationSpec(String line) {
        if (line.startsWith(HEADER)) {
            String compilerOne = line.substring(HEADER.length());
            log.setCompiler1(compilerOne);
            return true;
        }
        return false;
    }
}