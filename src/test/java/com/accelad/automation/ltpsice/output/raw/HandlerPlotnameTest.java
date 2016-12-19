package com.accelad.automation.ltpsice.output.raw;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class HandlerPlotnameTest {

    private static final String PLOTNAME_HEADER = "Plotname: ";
    private static final String TEST_PLOTNAME = "a plotname";
    private HandlerPlotname handler;
    private LTSpiceRaw.Builder builder;

    @Before
    public void initBeforeTest() {
        builder = mock(LTSpiceRaw.Builder.class);
        handler = new HandlerPlotname(builder);
    }

    @Test
    public void shouldReturnThePlotnameWhenALineStartingWithThePlotnameHeaderIsGiven() {
        String line = PLOTNAME_HEADER + TEST_PLOTNAME;

        handler.operationSpec(line);

        verify(builder).withPlotName(TEST_PLOTNAME);
    }

    @Test
    public void shouldReturnTrueWhenALineStartingWithThePlotnameHeaderIsGiven() {
        String line = PLOTNAME_HEADER + TEST_PLOTNAME;

        boolean result = handler.operationSpec(line);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenALineStartingWithSomethingElseThanThePlotnameHeaderIsGiven() {
        String line = "Blabla: " + TEST_PLOTNAME;

        boolean result = handler.operationSpec(line);

        verify(builder, never()).withPlotName(anyString());
        assertFalse(result);
    }

}
