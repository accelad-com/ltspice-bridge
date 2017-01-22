package com.accelad.automation.ltpsice;

import com.accelad.automation.ltpsice.netlist.Netlist;
import com.accelad.automation.ltpsice.process.LTSpiceException;

public interface LTSpiceService {
    public boolean isAvailable();

    public LTSpiceResult runSimulation(Netlist netlist) throws LTSpiceException;

    public LTSpiceResult runSimulation(Netlist netlist, boolean withTraces) throws LTSpiceException;

}
