package com.accelad.automation.ltpsice.output.raw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.accelad.automation.ltpsice.output.raw.LTSpiceRaw.Builder;

class HandlerBinary extends RawLineHandler {
    private static final String BINARY_HEADER = "Binary:";

    private final Builder builder;
    private final RawFileInputStreamReader streamReader;

    public HandlerBinary(Builder builder,
            RawFileInputStreamReader streamReader) {
        this.builder = builder;
        this.streamReader = streamReader;
    }

    @Override
    public boolean operationSpec(String line) throws IOException {
        int pointCount = builder.getPointCount();
        int variableCount = builder.getVariableCount();

        if (line.startsWith(BINARY_HEADER)) {
            for (int i = 0; i < pointCount; i++) {
                List<Double> values = new ArrayList<>();
                for (int j = 0; j < variableCount; j++) {
                    if (j == 0) {
                        values.add(streamReader.readDouble());
                    } else {
                        values.add((double) streamReader.readFloat());
                    }
                }

                builder.withPoint(values);
            }
            return true;
        }

        return false;
    }
}