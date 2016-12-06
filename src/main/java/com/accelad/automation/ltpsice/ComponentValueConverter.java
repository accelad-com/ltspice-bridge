package com.accelad.automation.ltpsice;

import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComponentValueConverter implements AttributeConverter {

    private static final AttributeConverter INSTANCE = new ComponentValueConverter();

    private static final String ALL_EXCEPT_NOTATION_REGEX = getAllExceptNotationRegex();
    private static final String NOTATION_REGEX = getNotationRegex();
    private static final int ALL_EXCEPT_NOTATION_GROUP = 1;
    private static final int NOTATION_GROUP = 2;
    private static final String COMPLETE_REGEX = ALL_EXCEPT_NOTATION_REGEX + NOTATION_REGEX + "?";
    private static final Pattern PATTERN = Pattern.compile(COMPLETE_REGEX);

    private ComponentValueConverter() {
    }

    private static String getAllExceptNotationRegex() {
        StringBuilder builder = new StringBuilder();
        builder.append("(.*[^");
        forEachNotation(builder::append);
        builder.append("])");
        return builder.toString();
    }

    private static String getNotationRegex() {
        StringJoiner builder = new StringJoiner("|", "(", ")");
        forEachNotation(builder::add);
        return builder.toString();
    }

    private static void forEachNotation(Consumer<String> consumer) {
        for (MetricPrefix en : MetricPrefix.values()) {
            en.getPrefixes().forEach(consumer);
        }
    }

    @Override
    public double convert(String componentValue) throws NumberFormatException {
        Matcher match = PATTERN.matcher(componentValue);
        if (match.matches()) {
            String numberPart = match.group(ALL_EXCEPT_NOTATION_GROUP);
            double numberValue = Double.parseDouble(numberPart);
            String notationPart = match.group(NOTATION_GROUP);
            if (notationPart == null) {
                return numberValue;
            }
            return applyNotation(numberValue, notationPart);
        }
        throw new NumberFormatException();
    }

    private double applyNotation(double numberValue, String notationPart) {
        MetricPrefix notation = MetricPrefix.findByPrefix(notationPart);
        return numberValue * Math.pow(10, notation.getFactor());
    }

    @Override
    public String convert(double d) {
        return PrefixedValueFormatter.formatValue(d);
    }

    public static AttributeConverter getInstance() {
        return INSTANCE;
    }
}
