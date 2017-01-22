package com.accelad.automation.ltpsice.netlist.directives;

import java.util.Iterator;

public class DirectiveWriter implements DirectiveVisitor {

    private static final String SEPARATOR = " ";
    private String text = "";

    public String getElementText() {
        return text;
    }

    @Override
    public void visit(UserDefinedParameter param) {
        text = ".PARAM" + SEPARATOR + param.getName() + SEPARATOR + param.getValue().getValue();
    }

    @Override
    public void visit(UserDefinedFunction function) {
        StringBuilder builder = new StringBuilder();
        builder.append(".FUNC" + SEPARATOR + function.getName() + "(");

        Iterator<Parameter> iterator = function.getParameters().iterator();
        while (iterator.hasNext()) {
            Parameter parameter = iterator.next();
            builder.append(parameter);
            if (iterator.hasNext()) {
                builder.append(",");
            }
        }

        builder.append(")" + SEPARATOR + function.getValue().getValue());
        text = builder.toString();
    }

    @Override
    public void visit(Measure meas) {
        text = ".MEASURE" + SEPARATOR + meas.getName() + SEPARATOR
                + meas.getExpression();

    }

    @Override
    public void visit(OperatingPoint operatingPoint) {
        text = ".OP";
    }

    @Override
    public void visit(Transient trans) {
        StringBuilder builder = new StringBuilder();
        builder.append(".TRAN" + SEPARATOR + trans.getStopTime());

        for (String modifier : trans.getModifiers()) {
            builder.append(SEPARATOR + modifier);
        }
        text = builder.toString();
    }

    @Override
    public void visit(Library library) {
        text = ".LIB" + " " + library.getPath();
    }

    @Override
    public void visit(ModelDirective model) {
        String modelName = model.getModelName().toString();
        String type = convert(model.getModelType());
        String value = model.getValue().getValue();
        text = ".MODEL" + SEPARATOR + modelName + SEPARATOR + type + value;
    }

    private String convert(ModelType type) {
        switch (type) {

        case VoltageControlledSwitch:
            return "SW";
        case CurrentControlledSwitch:
            return "CSW";
        case UniformDistributedRCLine:
            return "URC";
        case LossyTransmissionLine:
            return "LTRA";
        case Diode:
            return "D";
        case NPNBipolarTransistor:
            return "NPN";
        case PNPBipolarTransistor:
            return "PNP";
        case NchannelJFET:
            return "NJF";
        case PchannelJFET:
            return "PJF";
        case NchannelMOSFET:
            return "NMOS";
        case PchannelMOSFET:
            return "PMOS";
        case NchannelMESFET:
            return "NMF";
        case PchannelMESFET:
            return "PMF";
        case NchannelIGBT:
            return "NIGBT";
        case PchannelIGBT:
            return "PIGBT";
        case VerticalDoubleDiffusedPowerMOSFET:
            return "VDMOS";
        }
        return "";

    }

}
