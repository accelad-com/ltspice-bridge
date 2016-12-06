package com.accelad.automation.ltpsice;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

class PrefixedValueFormatter {
    private static final DecimalFormat FORMAT = new DecimalFormat("###.###");
    private static final Map<Integer, String> prefixes;

    private static final double MIN_LEFT_VALUE = 1.0;
    private static final double MAX_LEFT_VALUE = 1000.0;

    private static final int STEP_FACTOR = 3;
    private static final double TEN_POWER_FACTOR = Math.pow(10, STEP_FACTOR);

    static {
        prefixes = Arrays.stream(MetricPrefix.values())
                .collect(Collectors.toMap(MetricPrefix::getFactor,
                        MetricPrefix::getDefaultPrefix));
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(' ');

        FORMAT.setDecimalFormatSymbols(otherSymbols);
    }

    private PrefixedValueFormatter() {
    }

    public static String formatValue(double value) {
        if (Math.abs(value) < 1e-12) {
            return FORMAT.format(0);
        }

        double sign = Math.signum(value);
        double tval = Math.abs(value);
        int order = 0;
        while (tval >= MAX_LEFT_VALUE) {
            tval /= TEN_POWER_FACTOR;
            order += STEP_FACTOR;
        }
        while (tval < MIN_LEFT_VALUE) {
            tval *= TEN_POWER_FACTOR;
            order -= STEP_FACTOR;
        }

        if (sign < 0) {
            return "-" + FORMAT.format(tval) + prefixes.get(order);
        } else {
            return FORMAT.format(tval) + prefixes.get(order);
        }
    }
}