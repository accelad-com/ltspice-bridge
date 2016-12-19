package com.accelad.automation.ltpsice.process;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.List;

import org.junit.Test;

public class LTSpiceProcessTest {

    private static final int EXPECTED_NUMBER_OF_COMMANDS = 3;
    private static final String EXPECTED_PATH = "the path to be returned";
    private static final String BATCH_MODE_FLAG = "-b";

    @Test
    public void shouldReturnAListWithTheCommands() {
        File circuit = new File("C:\temp\test.net");
        ExecutableFile executableFile = mock(ExecutableFile.class);
        when(executableFile.getAbsolutePath()).thenReturn(EXPECTED_PATH);

        LTSpiceProcess process = new LTSpiceProcess(executableFile, circuit);

        List<String> commands = process.createProcessCommands(executableFile, circuit);
        assertEquals(EXPECTED_NUMBER_OF_COMMANDS, commands.size());
        assertEquals(EXPECTED_PATH, commands.get(0));
        assertEquals(BATCH_MODE_FLAG, commands.get(1));
        assertEquals(circuit.getAbsolutePath(), commands.get(2));
    }

}
