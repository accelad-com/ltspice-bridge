package com.accelad.automation.ltpsice.process;

import java.util.List;

public interface ProcessCommandBuilder {

    List<String> createProcessCommands(
    		ExecutableFile executableFile, 
    		CircuitFile circuit,
            List<CommandLineSwitch> lineSwitchs);

}