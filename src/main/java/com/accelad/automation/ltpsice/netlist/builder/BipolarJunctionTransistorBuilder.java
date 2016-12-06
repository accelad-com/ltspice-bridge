package com.accelad.automation.ltpsice.netlist.builder;

import java.util.StringTokenizer;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.Model;
import com.accelad.automation.ltpsice.netlist.Node;
import com.accelad.automation.ltpsice.netlist.element.BipolarJunctionTransistor;

public class BipolarJunctionTransistorBuilder implements ElementBuilderDelegate {
    @Override
    public BipolarJunctionTransistor build(String text) {
        StringTokenizer st = new StringTokenizer(text, " ");
        InstanceName instanceName = new InstanceName(st.nextToken().substring(1));
        Node collector = new Node(st.nextToken());
        Node base = new Node(st.nextToken());
        Node emitter = new Node(st.nextToken());
        Node substrate = new Node(st.nextToken());
        Model model = new Model(st.nextToken());
        return new BipolarJunctionTransistor(instanceName, collector, base, emitter, substrate,
                model);
    }
}