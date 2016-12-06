package com.accelad.automation.ltpsice;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.Model;
import com.accelad.automation.ltpsice.netlist.Netlist;
import com.accelad.automation.ltpsice.netlist.Node;
import com.accelad.automation.ltpsice.netlist.Title;
import com.accelad.automation.ltpsice.netlist.Value;
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

public class NetlistAnalyzerTest {

    @Test
    public void should_testname() throws Exception {
        NetlistAnalyzer analyzer = new NetlistAnalyzer();
        analyzer.analyze(createNetlist());

    }

    private Netlist createNetlist() {
        Netlist netlist = new Netlist();
        netlist.setTitle(new Title("title"));
        Node nodeA = new Node("a");
        Node nodeB = new Node("b");
        Node nodeC = new Node("c");
        Node nodeD = new Node("d");
        netlist.addElement(new BehaviouralCurrentSource(new InstanceName("0BCS"), nodeA, nodeB, new Value("1")));
        netlist.addElement(new BehaviouralVoltageSource(new InstanceName("0BVS"), nodeA, nodeB, new Value("2")));
        netlist.addElement(new BipolarJunctionTransistor(new InstanceName("0BJT"), nodeA, nodeB, nodeC, nodeD, new Model("3")));
        netlist.addElement(new Capacitor(new InstanceName("0C"), nodeA, nodeB, new Value("4")));
        netlist.addElement(new CurrentControlledCurrentSource(new InstanceName("0CCC"), nodeA, nodeB, nodeC, new Value("5")));
        netlist.addElement(new CurrentControlledVoltageSource(new InstanceName("0CCV"), nodeA, nodeB, nodeC, new Value("6")));
        netlist.addElement(new Diode(new InstanceName("0BVS"), nodeA, nodeB, new Model("7")));
        netlist.addElement(new IndependentCurrentSource(new InstanceName("0ICS"), nodeA, nodeB, new Value("8")));
        netlist.addElement(new IndependentVoltageSource(new InstanceName("0IVS"), nodeA, nodeB, new Value("9")));
        netlist.addElement(new Inductor(new InstanceName("0I"), nodeA, nodeB, new Value("10")));
        netlist.addElement(new Resistor(new InstanceName("0R"), nodeA, nodeB, new Value("11")));
        netlist.addElement(new Subcircuit(new InstanceName("0S"), Arrays.asList(nodeA, nodeB, nodeC, nodeD), new Model("12")));
        netlist.addElement(new VoltageControlledCurrentSource(new InstanceName("VCC"), nodeA, nodeB, nodeC, nodeD, new Value("13")));
        netlist.addElement(new VoltageControlledVoltageSource(new InstanceName("VCV"), nodeA, nodeB, nodeC, nodeD, new Value("14")));
        return netlist;
    }
}
