package com.accelad.automation.ltpsice.output.raw;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class HandlerTitleTest {

    private static final String TITLE_HEADER = "Title: ";
    private static final String TEST_TITLE = "a short title";
    private HandlerTitle handler;
    private LTSpiceRaw.Builder builder;

    @Before
    public void initBeforeTest() {
        builder = mock(LTSpiceRaw.Builder.class);
        handler = new HandlerTitle(builder);
    }

    @Test
    public void shouldReturnTheTitleWhenALineStartingWithTheTitleHeaderIsGiven() {
        String line = TITLE_HEADER + TEST_TITLE;

        handler.operationSpec(line);

        verify(builder).withTitle(TEST_TITLE);
    }

    @Test
    public void shouldReturnTrueWhenALineStartingWithTheTitleHeaderIsGiven() {
        String line = TITLE_HEADER + TEST_TITLE;

        boolean result = handler.operationSpec(line);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenALineStartingWithSomethingElseThanTheTitleHeaderIsGiven() {
        String line = "Blabla: " + TEST_TITLE;

        boolean result = handler.operationSpec(line);

        verify(builder, never()).withTitle(anyString());
        assertFalse(result);
    }

}
