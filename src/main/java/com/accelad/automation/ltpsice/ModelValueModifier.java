package com.accelad.automation.ltpsice;

public class ModelValueModifier {

    public String modify(String line, double value) {
        String[] arguments = line.split(" ");

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arguments.length - 1; i++) {
            sb.append(arguments[i]);
            sb.append(" ");
        }

        sb.append(Double.toString(value));

        return sb.toString();

    }

}
