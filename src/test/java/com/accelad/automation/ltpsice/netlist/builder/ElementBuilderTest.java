package com.accelad.automation.ltpsice.netlist.builder;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.Node;
import com.accelad.automation.ltpsice.netlist.Value;

import com.accelad.automation.ltpsice.netlist.builder.ElementBuilder;
import com.accelad.automation.ltpsice.netlist.element.Element;
import com.accelad.automation.ltpsice.netlist.element.Resistor;

public class ElementBuilderTest {

    @Test
    public void shouldReturnAResistorWhenALineDescribingAResistorIsGiven() throws Exception {

        String line = "R1 N007 0 4.7k";
        ElementBuilder builder = new ElementBuilder();
        Optional<Element> optional = builder.build(line);
        Element element = optional.get();
        if (element instanceof Resistor) {
            Resistor resistor = (Resistor) element;
            assertEquals(new InstanceName("1"), resistor.getInstanceName());
            assertEquals(new Node("N007"), resistor.getPositive());
            assertEquals(new Node("0"), resistor.getNegative());
            assertEquals(new Value("4.7k"), resistor.getValue());
        }



    }
}
