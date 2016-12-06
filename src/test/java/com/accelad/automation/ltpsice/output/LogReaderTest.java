package com.accelad.automation.ltpsice.output;

import java.io.InputStream;

import org.junit.Test;

public class LogReaderTest {

    @Test
    public void should_Read_A_Raw_File() throws Exception {
        LogReader reader = new LogReader();
        InputStream is = this.getClass().getResourceAsStream("/circuit.log");
        Log log = reader.read(is);
    }
}
