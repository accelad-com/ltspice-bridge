package com.accelad.automation.ltpsice.output.raw;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class HandlerFlagsTest {

    @Test
    public void shouldReturnAListOfFlagWhichContainsTwoFlagsWhenAFormattedTextWithFlagsIsGiven() {
        String theLineToParse = "Flags: real forward";

        LTSpiceRaw.Builder builder = mock(LTSpiceRaw.Builder.class);
        HandlerFlags handlerFlags = new HandlerFlags(builder);
        handlerFlags.operation(theLineToParse);

        ListOfFlags expectedListOfFlags = new ListOfFlags(Flag.REAL, Flag.FORWARD);
        verify(builder).withFlags(eq(expectedListOfFlags));
    }

}
