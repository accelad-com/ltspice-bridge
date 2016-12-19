package com.accelad.automation.ltpsice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.accelad.automation.ltpsice.netlist.InstanceName;
import com.accelad.automation.ltpsice.netlist.Netlist;
import com.accelad.automation.ltpsice.netlist.Node;
import com.accelad.automation.ltpsice.netlist.Value;
import com.accelad.automation.ltpsice.netlist.directives.OperatingPoint;
import com.accelad.automation.ltpsice.netlist.element.IndependentVoltageSource;
import com.accelad.automation.ltpsice.netlist.element.Resistor;
import com.accelad.automation.ltpsice.output.log.Measure;

public class LTSpiceServiceImplTest {

    @Test
    public void shouldStartASimulation() throws Exception {
        LTSpiceService service = new LTSpiceServiceImpl();

        if (!service.isAvailable()) {
            return;
        }

        Netlist netlist = createNetlist();
        LTSpiceResult result = service.runSimulation(netlist);

        Double value = result.findMeasureByName("vout")
                .map(Measure::getValue)
                .orElse(Double.NaN);
        assertEquals(5.0, value, 1e-12);
    }

    private Netlist createNetlist() {
        Netlist netlist = new Netlist();
        netlist.addDirective(new OperatingPoint());
        Node nodeIn = new Node("in");
        Node nodeOut = new Node("out");
        Node nodeGnd = new Node("0");
        netlist.addElement(
                new Resistor(new InstanceName("R1"), nodeIn, nodeOut, new Value("1000")));
        netlist.addElement(
                new Resistor(new InstanceName("R2"), nodeOut, nodeGnd, new Value("1000")));
        netlist.addElement(new IndependentVoltageSource(new InstanceName("V1"), nodeIn, nodeGnd,
                new Value("10")));

        netlist.addDirective(
                new com.accelad.automation.ltpsice.netlist.directives.Measure("vout",
                        "PARAM V(out)"));
        return netlist;
    }

}
