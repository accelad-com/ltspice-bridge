package com.accelad.automation.ltpsice.output.raw;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class HandlerPointCountTest {

    private static final String POINT_COUNT_HEADER = "No. Points: ";
    private static final int TEST_POINT_COUNT = 2;
    private HandlerPointCount handler;
    private LTSpiceRaw.Builder builder;

    @Before
    public void initBeforeTest() {
        builder = mock(LTSpiceRaw.Builder.class);
        handler = new HandlerPointCount(builder);
    }

    @Test
    public void shouldReturnThePointCountWhenALineStartingWithThePointCountHeaderIsGiven() {
        String line = POINT_COUNT_HEADER + TEST_POINT_COUNT;

        handler.operationSpec(line);

        verify(builder).withPointCount(TEST_POINT_COUNT);
    }

    @Test
    public void shouldReturnTrueWhenALineStartingWithThePointCountHeaderIsGiven() {
        String line = POINT_COUNT_HEADER + TEST_POINT_COUNT;

        boolean result = handler.operationSpec(line);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenALineStartingWithSomethingElseThanThePointCountHeaderIsGiven() {
        String line = "Blabla: " + TEST_POINT_COUNT;

        boolean result = handler.operationSpec(line);

        verify(builder, never()).withPointCount(any(Integer.class));
        assertFalse(result);
    }

}
