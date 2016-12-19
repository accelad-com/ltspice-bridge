package com.accelad.automation.ltpsice.output.raw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class LTSpiceRawParserTest {

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy hh:mm:ss");

    @Test
    public void testRead() throws IOException, ParseException {
        InputStream testFileFile = this.getClass().getResourceAsStream("/circuit.raw");

        LTSpiceRawParser reader = new LTSpiceRawParser();
        LTSpiceRaw raw = reader.read(testFileFile);

        assertEquals("* This is my title", raw.getTitle());
        assertEquals(dateFormat.parse("27-11-16 17:33:40"), raw.getDate());
        assertEquals("Operating Point", raw.getPlotName());
        assertTrue(raw.getFlags().contains(Flag.REAL));
        assertEquals(0.0, raw.getOffset(), 1e-12);
        assertEquals("Linear Technology Corporation LTspice XVII", raw.getCommand());
        assertEquals(5, raw.getTraces().size());
        assertEquals("V(a)", raw.getTraces().get(0).getName());
        assertEquals("V(b)", raw.getTraces().get(1).getName());
        assertEquals("I(Rr2)", raw.getTraces().get(2).getName());
        assertEquals("I(Rr1)", raw.getTraces().get(3).getName());
        assertEquals("I(Vv1)", raw.getTraces().get(4).getName());

        assertEquals(10.0, raw.getTraces().get(0).getValues().get(0), 1e-12);
        assertEquals(5.0, raw.getTraces().get(1).getValues().get(0), 1e-12);
        assertEquals(0.004999, raw.getTraces().get(2).getValues().get(0), 1e-6);
        assertEquals(0.004999, raw.getTraces().get(3).getValues().get(0), 1e-6);
        assertEquals(-0.004999, raw.getTraces().get(4).getValues().get(0), 1e-6);
    }

}
