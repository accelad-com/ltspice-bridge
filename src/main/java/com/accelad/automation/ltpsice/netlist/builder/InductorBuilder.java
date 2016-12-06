package com.accelad.automation.ltpsice.netlist.builder;

import java.util.StringTokenizer;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.Node;
import com.accelad.automation.ltpsice.netlist.Value;
import com.accelad.automation.ltpsice.netlist.element.Inductor;

public class InductorBuilder implements ElementBuilderDelegate {
    @Override
    public Inductor build(String text) {
        StringTokenizer st = new StringTokenizer(text, " ");
        InstanceName instanceName = new InstanceName(st.nextToken().substring(1));
        Node positive = new Node(st.nextToken());
        Node negative = new Node(st.nextToken());
        Value value = new Value(st.nextToken());
        return new Inductor(instanceName, positive, negative, value);
    }
}