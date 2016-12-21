package com.accelad.automation.ltpsice.process;

import java.util.ArrayList;
import java.util.List;

public class ProcessCommandBuilderLinux implements ProcessCommandBuilder {

    private static final String WINE = "wine";

    @Override
    public List<String> createProcessCommands(
    		ExecutableFile executableFile, 
    		CircuitFile circuit,
            List<CommandLineSwitch> lineSwitchs) {
        List<String> commands = new ArrayList<>();
        commands.add(WINE);
        commands.add(executableFile.getAbsolutePath());

        lineSwitchs.stream()
                .map(CommandLineSwitch::toString)
                .forEach(commands::add);

        commands.add(circuit.getLtSpiceFolder());
        return commands;
    }

}
