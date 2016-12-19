package com.accelad.automation.ltpsice.output.raw;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class HandlerCommandTest {

    private static final String COMMAND_HEADER = "Command: ";
    private static final String TEST_COMMAND = "a command";
    private HandlerCommand handler;
    private LTSpiceRaw.Builder builder;

    @Before
    public void initBeforeTest() {
        builder = mock(LTSpiceRaw.Builder.class);
        handler = new HandlerCommand(builder);
    }

    @Test
    public void shouldReturnTheCommandWhenALineStartingWithTheCommandHeaderIsGiven() {
        String line = COMMAND_HEADER + TEST_COMMAND;

        handler.operationSpec(line);

        verify(builder).withCommand(TEST_COMMAND);
    }

    @Test
    public void shouldReturnTrueWhenALineStartingWithTheCommandHeaderIsGiven() {
        String line = COMMAND_HEADER + TEST_COMMAND;

        boolean result = handler.operationSpec(line);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenALineStartingWithSomethingElseThanTheCommandHeaderIsGiven() {
        String line = "Blabla: " + TEST_COMMAND;

        boolean result = handler.operationSpec(line);

        verify(builder, never()).withCommand(anyString());
        assertFalse(result);
    }

}
