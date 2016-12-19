package com.accelad.automation.ltpsice.output.log;

import java.io.InputStream;

import org.junit.Test;

public class LogParserTest {

    @Test
    public void should_testname() throws Exception {
        InputStream testFileFile = this.getClass().getResourceAsStream("/circuit.log");

        LogParser parser = new LogParser();
        Log log = parser.read(testFileFile);

    }

}
