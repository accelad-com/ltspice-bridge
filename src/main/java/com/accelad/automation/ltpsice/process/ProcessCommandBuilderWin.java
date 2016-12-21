package com.accelad.automation.ltpsice.process;

import java.util.ArrayList;
import java.util.List;

public class ProcessCommandBuilderWin implements ProcessCommandBuilder {

    public List<String> createProcessCommands(
    		ExecutableFile executableFile, 
    		CircuitFile circuit,
            List<CommandLineSwitch> lineSwitchs) {
        List<String> commands = new ArrayList<>();
        commands.add(executableFile.getAbsolutePath());

        lineSwitchs.stream()
                .map(CommandLineSwitch::toString)
                .forEach(commands::add);

        commands.add(circuit.getLtSpiceFolder());
        return commands;
    }

}
