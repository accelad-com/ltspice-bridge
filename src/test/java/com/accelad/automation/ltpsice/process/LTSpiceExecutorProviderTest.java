package com.accelad.automation.ltpsice.process;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class LTSpiceExecutorProviderTest {

    @Test
    public void shouldLocateTheLTSpiceExecutableFileOnTheSystem() throws LTSpiceException {
        LTSpiceExecutorProvider provider = new LTSpiceExecutorProvider();
        LTSpiceExecutor executor = provider.getExecutor();
        assertNotNull(executor);
    }

}
