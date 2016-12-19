package com.accelad.automation.ltpsice.output.raw;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class HandlerDateTest {

    private static final String DATE_HEADER = "Date: ";
    private static final String TEST_DATE = "Sun Nov 27 17:33:40 2016";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy hh:mm:ss");
    private HandlerDate handler;
    private LTSpiceRaw.Builder builder;

    @Before
    public void initBeforeTest() {
        builder = mock(LTSpiceRaw.Builder.class);
        handler = new HandlerDate(builder);
    }

    @Test
    public void shouldReturnTheDateWhenALineStartingWithTheDateHeaderIsGiven()
            throws ParseException {
        String line = DATE_HEADER + TEST_DATE;

        handler.operationSpec(line);

        verify(builder).withDate(dateFormat.parse("27-11-16 17:33:40"));
    }

    @Test
    public void shouldReturnTrueWhenALineStartingWithTheDateHeaderIsGiven() {
        String line = DATE_HEADER + TEST_DATE;

        boolean result = handler.operationSpec(line);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenALineStartingWithSomethingElseThanTheDateHeaderIsGiven() {
        String line = "Blabla: " + TEST_DATE;

        boolean result = handler.operationSpec(line);

        verify(builder, never()).withDate(any(Date.class));
        assertFalse(result);
    }

}
