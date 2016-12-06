package com.accelad.automation.ltpsice.output;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Test;

import com.accelad.automation.ltpsice.output.LTSpiceRaw;
import com.accelad.automation.ltpsice.output.LTSpiceRawReader;

public class LTSpiceRawReaderTest {

    @Test
    public void should_Read_A_Raw_File() throws Exception {
        LTSpiceRawReader reader = new LTSpiceRawReader();
        InputStream is = this.getClass().getResourceAsStream("/Schema sawtooth.raw");
        LTSpiceRaw raw = reader.read(is);
        assertEquals(3, raw.getVariableCount());
    }

}
