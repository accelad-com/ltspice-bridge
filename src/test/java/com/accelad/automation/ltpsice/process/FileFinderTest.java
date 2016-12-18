package com.accelad.automation.ltpsice.process;

import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import com.accelad.automation.ltpsice.process.FileFinder;

public class FileFinderTest {

    @Test
    public void testFind() throws IOException {

        if (SystemUtils.IS_OS_WINDOWS) {
            FileFinder finder = new FileFinder("calc.exe");
            Files.walkFileTree(Paths.get("C:\\Windows\\System32"), finder);
            assertFalse(finder.getMatchingPaths().isEmpty());
        }

    }

}
