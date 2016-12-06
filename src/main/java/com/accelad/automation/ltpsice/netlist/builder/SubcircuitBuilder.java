package com.accelad.automation.ltpsice.netlist.builder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.accelad.automation.ltpsice.netlist.element.Subcircuit;

public class SubcircuitBuilder implements ElementBuilderDelegate {
    @Override
    public Subcircuit build(String text) {

        String re1 = "((?:[a-z][a-z]*[0-9]+[a-z0-9]*))"; // Alphanum 1
        String re2 = "(\\s+)"; // White Space 1
        String re3 = "((?:[a-z][a-z]*[0-9]+[a-z0-9]*))"; // Alphanum 2
        String re4 = "(\\s+)"; // White Space 2
        String re5 = "((?:[a-z][a-z]*[0-9]+[a-z0-9]*))"; // Alphanum 3
        String re6 = "(\\s+)"; // White Space 3
        String re7 = "((?:[a-z][a-z0-9_]*))"; // Variable Name 1
        String re8 = "(\\s+)"; // White Space 4

        Pattern pattern = Pattern.compile(re1);
        Matcher m = pattern.matcher(text);
        while (m.find()) {
            System.out.println("Le texte \"" + m.group() +
                    "\" débute à " + m.start() + " et termine à " + m.end());
        }

        return null;
    }
}