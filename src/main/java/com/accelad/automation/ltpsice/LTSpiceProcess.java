package com.accelad.automation.ltpsice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LTSpiceProcess {

    private final PathLocator locator;

    public LTSpiceProcess(PathLocator locator) {
        this.locator = locator;
    }

    public boolean run(File circuit) {
        List<String> commands = new ArrayList<>();
        commands.add(locator.locate().getAbsolutePath());
        commands.add("-b");
        commands.add(circuit.getAbsolutePath());


        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        processBuilder.inheritIO();
        try {
            Process process = processBuilder.start();
            int exitValue = process.waitFor();

            if (exitValue != 0) {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
