package com.accelad.automation.ltpsice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CircuitReader {

    public Circuit read(InputStream is) {

        List<String> lines = new ArrayList<String>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Circuit(lines);
    }

}
