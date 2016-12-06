package com.accelad.automation.ltpsice;

import java.util.Arrays;
import java.util.List;

public enum MetricPrefix {

    YOCTO(-24, "y"),
    ZEPTO(-21, "z"),
    ATTO(-18, "a"),
    FEMTO(-15, "f"),
    PICO(-12, "p"),
    NANO(-9, "n"),
    MICRO(-6, "u", "µ"),
    MILLI(-3, "m"),
    NATURAL(0, ""),
    KILO(3, "k"),
    MEGA(6, "M"),
    GIGA(9, "G"),
    TERA(12, "T"),
    PETA(15, "P"),
    EXA(18, "E"),
    ZETTA(21, "Z"),
    YOTTA(24, "Y");

    private List<String> prefixes;
    private int factor;

    MetricPrefix(int factor, String... prefixes) {
        this.prefixes = Arrays.asList(prefixes);
        this.factor = factor;
    }

    public String getDefaultPrefix() {
        return prefixes.get(0);
    }

    public List<String> getPrefixes() {
        return prefixes;
    }

    public int getFactor() {
        return factor;
    }

    public static MetricPrefix findByPrefix(String prefix) {
        for (MetricPrefix en : MetricPrefix.values()) {
            for (String s : en.prefixes) {
                if (s.equals(prefix)) {
                    return en;
                }
            }
        }
        throw new InvalidEngineeringNotationException(prefix);
    }
}
