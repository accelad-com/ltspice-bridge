package com.accelad.automation.ltpsice.netlist.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.Model;
import com.accelad.automation.ltpsice.netlist.Node;
import com.accelad.automation.ltpsice.netlist.element.BipolarJunctionTransistor;

public class BipolarJunctionTransistorBuilderTest {
    @Test
    public void shouldReturnAResistorWhenALineDescribingAResistorIsGiven() throws Exception {

        String line = "Q1 BOOSTER_FB N001 N005 0 BC817-40";
        BipolarJunctionTransistorBuilder builder = new BipolarJunctionTransistorBuilder();
        BipolarJunctionTransistor transistor = builder.build(line);

        assertEquals(new InstanceName("1"), transistor.getInstanceName());
        assertEquals(new Node("BOOSTER_FB"), transistor.getCollector());
        assertEquals(new Node("N001"), transistor.getBase());
        assertEquals(new Node("N005"), transistor.getEmitter());
        assertEquals(new Node("0"), transistor.getSubstrate().get());
        assertEquals(new Model("BC817-40"), transistor.getModel());

    }

}
