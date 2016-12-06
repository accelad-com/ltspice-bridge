package com.accelad.automation.ltpsice.netlist.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.element.Subcircuit;

public class SubcircuitBuilderTest {

    @Test
    public void shouldReturnAResistorWhenALineDescribingAResistorIsGiven() throws Exception {

        String line = "U5 N003 N006 BOOSTER_5V 0 N008 LM2901";
        SubcircuitBuilder builder = new SubcircuitBuilder();
        Subcircuit transistor = builder.build(line);

        assertEquals(new InstanceName("U5"), transistor.getInstanceName());
    }
}
