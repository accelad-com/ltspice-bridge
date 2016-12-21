package com.accelad.automation.ltpsice.process;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LTSpiceProcessTest {

    private static final int EXPECTED_NUMBER_OF_COMMANDS = 3;
    private static final String EXPECTED_PATH = "the path to be returned";
    private static final String BATCH_MODE_FLAG = "-b";

    @Test
    public void shouldReturnAListWithTheCommands() {
        CircuitFile circuit = new CircuitFile(new File("C:\\temp\\test.net"), "C:\\temp\\test.net");
        ExecutableFile executableFile = mock(ExecutableFile.class);
        when(executableFile.getAbsolutePath()).thenReturn(EXPECTED_PATH);

        ProcessCommandBuilderWin commandBuilder = new ProcessCommandBuilderWin();

        List<CommandLineSwitch> emptyList = Arrays.asList(new BatchModeFlag());

        List<String> commands = commandBuilder.createProcessCommands(executableFile, circuit,
                emptyList);
        assertEquals(EXPECTED_NUMBER_OF_COMMANDS, commands.size());
        assertEquals(EXPECTED_PATH, commands.get(0));
        assertEquals(BATCH_MODE_FLAG, commands.get(1));
        assertEquals(circuit.getLtSpiceFolder(), commands.get(2));
    }

}
