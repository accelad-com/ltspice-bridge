package com.accelad.automation.ltpsice;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class CircuitWriter {

    public void write(Circuit cir, OutputStream out) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String line : cir.getLines()) {
                writer.write(line);
                writer.write("\r\n");
            }
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
