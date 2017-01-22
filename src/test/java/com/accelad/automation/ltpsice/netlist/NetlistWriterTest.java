package com.accelad.automation.ltpsice.netlist;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import com.accelad.automation.ltpsice.netlist.directives.ModelName;
import com.accelad.automation.ltpsice.netlist.element.BehaviouralCurrentSource;
import com.accelad.automation.ltpsice.netlist.element.BehaviouralVoltageSource;
import com.accelad.automation.ltpsice.netlist.element.BipolarJunctionTransistor;
import com.accelad.automation.ltpsice.netlist.element.Capacitor;
import com.accelad.automation.ltpsice.netlist.element.CurrentControlledCurrentSource;
import com.accelad.automation.ltpsice.netlist.element.CurrentControlledVoltageSource;
import com.accelad.automation.ltpsice.netlist.element.Diode;
import com.accelad.automation.ltpsice.netlist.element.IndependentCurrentSource;
import com.accelad.automation.ltpsice.netlist.element.IndependentVoltageSource;
import com.accelad.automation.ltpsice.netlist.element.Inductor;
import com.accelad.automation.ltpsice.netlist.element.Resistor;
import com.accelad.automation.ltpsice.netlist.element.Subcircuit;
import com.accelad.automation.ltpsice.netlist.element.VoltageControlledCurrentSource;
import com.accelad.automation.ltpsice.netlist.element.VoltageControlledVoltageSource;

public class NetlistWriterTest {

    @Test
    public void shouldReturnANetlistFormattedWhenANetlistWithAllPossibleElementIsGiven()
            throws IOException {
        NetlistWriter writer = new NetlistWriter();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Netlist netlist = new Netlist();
        netlist.setTitle(new Title("title"));
        Node nodeA = new Node("a");
        Node nodeB = new Node("b");
        Node nodeC = new Node("c");
        Node nodeD = new Node("d");
        netlist.addElement(new BehaviouralCurrentSource(new InstanceName("0BCS"), nodeA, nodeB, new Value("1")));
        netlist.addElement(new BehaviouralVoltageSource(new InstanceName("0BVS"), nodeA, nodeB, new Value("2")));
        netlist.addElement(new BipolarJunctionTransistor(new InstanceName("0BJT"), nodeA, nodeB,
                nodeC, nodeD, new Model(new ModelName("3"))));
        netlist.addElement(new Capacitor(new InstanceName("0C"), nodeA, nodeB, new Value("4")));
        netlist.addElement(new CurrentControlledCurrentSource(new InstanceName("0CCC"), nodeA, nodeB, nodeC, new Value("5")));
        netlist.addElement(new CurrentControlledVoltageSource(new InstanceName("0CCV"), nodeA, nodeB, nodeC, new Value("6")));
        netlist.addElement(
                new Diode(new InstanceName("0BVS"), nodeA, nodeB, new Model(new ModelName("7"))));
        netlist.addElement(new IndependentCurrentSource(new InstanceName("0ICS"), nodeA, nodeB, new Value("8")));
        netlist.addElement(new IndependentVoltageSource(new InstanceName("0IVS"), nodeA, nodeB, new Value("9")));
        netlist.addElement(new Inductor(new InstanceName("0I"), nodeA, nodeB, new Value("10")));
        netlist.addElement(new Resistor(new InstanceName("0R"), nodeA, nodeB, new Value("11")));
        netlist.addElement(new Subcircuit(new InstanceName("0S"), Arrays
                .asList(nodeA, nodeB, nodeC, nodeD), new Model(new ModelName("12")),
                Collections.emptyList()));
        netlist.addElement(new VoltageControlledCurrentSource(new InstanceName("VCC"), nodeA, nodeB, nodeC, nodeD, new Value("13")));
        netlist.addElement(new VoltageControlledVoltageSource(new InstanceName("VCV"), nodeA, nodeB, nodeC, nodeD, new Value("14")));
        writer.write(netlist, new OutputStreamWriter(baos));

        String expectedResult = "* title\n" + "B0BCS a b 1\n" + "B0BVS a b 2\n" + "Q0BJT a b c 3\n" + "C0C a b 4\n"
                + "F0CCC a b c 5\n" + "H0CCV a b c 6\n" + "D0BVS a b 7\n" + "I0ICS a b 8\n"
                + "V0IVS a b 9\n" + "L0I a b 10\n" + "R0R a b 11\n" + "X0S a b c d 12 \n"
                + "GVCC a b c d 13\n" + "EVCV a b c d 14\n" + "\n" + "\n" + ".END";
        assertEquals(expectedResult, baos.toString().replaceAll("\r", "").trim());
    }
}
