package com.accelad.automation.ltpsice.output.raw;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class HandlerVariableCountTest {

    private static final String VARIABLE_COUNT_HEADER = "No. Variables: ";
    private static final int TEST_VARIABLE_COUNT = 2;
    private HandlerVariableCount handler;
    private LTSpiceRaw.Builder builder;

    @Before
    public void initBeforeTest() {
        builder = mock(LTSpiceRaw.Builder.class);
        handler = new HandlerVariableCount(builder);
    }

    @Test
    public void shouldReturnTheVariableCountWhenALineStartingWithTheVariableCountHeaderIsGiven() {
        String line = VARIABLE_COUNT_HEADER + TEST_VARIABLE_COUNT;

        handler.operationSpec(line);

        verify(builder).withVariableCount(TEST_VARIABLE_COUNT);
    }

    @Test
    public void shouldReturnTrueWhenALineStartingWithTheVariableCountHeaderIsGiven() {
        String line = VARIABLE_COUNT_HEADER + TEST_VARIABLE_COUNT;

        boolean result = handler.operationSpec(line);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenALineStartingWithSomethingElseThanTheVariableCountHeaderIsGiven() {
        String line = "Blabla: " + TEST_VARIABLE_COUNT;

        boolean result = handler.operationSpec(line);

        verify(builder, never()).withVariableCount(any(Integer.class));
        assertFalse(result);
    }

}
