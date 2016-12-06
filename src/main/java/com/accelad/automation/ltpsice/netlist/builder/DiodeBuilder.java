package com.accelad.automation.ltpsice.netlist.builder;

import java.util.StringTokenizer;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.Model;
import com.accelad.automation.ltpsice.netlist.Node;
import com.accelad.automation.ltpsice.netlist.element.Diode;

public class DiodeBuilder implements ElementBuilderDelegate {
    @Override
    public Diode build(String text) {
        StringTokenizer st = new StringTokenizer(text, " ");
        InstanceName instanceName = new InstanceName(st.nextToken().substring(1));
        Node anode = new Node(st.nextToken());
        Node cathode = new Node(st.nextToken());
        Model model = new Model(st.nextToken());
        return new Diode(instanceName, anode, cathode, model);
    }
}