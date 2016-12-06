package com.accelad.automation.ltpsice;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class CircuitLocator {

    public File locate() {
        String path = null;
        try {
            URL circuitPath = LTSpiceProcess.class.getResource("/circuit.net");
            URI uri = circuitPath.toURI();
            path = uri.getPath();
            return new File(path.substring(1));
        } catch (URISyntaxException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return null;
    }

}
