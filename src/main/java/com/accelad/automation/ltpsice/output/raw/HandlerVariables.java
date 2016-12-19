package com.accelad.automation.ltpsice.output.raw;

import java.io.IOException;

import com.accelad.automation.ltpsice.output.raw.LTSpiceRaw.Builder;

class HandlerVariables extends RawLineHandler {
    private static final String VARIABLES_HEADER = "Variables:";

    private final LTSpiceRaw.Builder builder;
    private final RawFileInputStreamReader br;

    public HandlerVariables(Builder builder,
            RawFileInputStreamReader br) {
        this.builder = builder;
        this.br = br;
    }

    @Override
    public boolean operationSpec(String line) throws IOException {

        int variableCount = builder.getVariableCount();

        if (line.startsWith(VARIABLES_HEADER)) {
            for (int i = 0; i < variableCount; i++) {
                String variableLine = br.readLine();
                String[] params = variableLine.split("\t");
                int index = Integer.parseInt(params[1]);
                String fullName = params[2];
                String name = params[2];
                String type = params[3];
                builder.withSignal(new Signal(index, fullName, name, type));
            }
            return true;
        }

        return false;
    }
}