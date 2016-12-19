package com.accelad.automation.ltpsice.output.raw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListOfFlags {

    private final List<Flag> flags;

    public ListOfFlags() {
        flags = new ArrayList<>();
    }

    public ListOfFlags(Flag... flags) {
        this();
        this.flags.addAll(Arrays.asList(flags));
    }

    public void add(Flag e) {
        flags.add(e);
    }

    public void remove(Flag o) {
        flags.remove(o);
    }

    public boolean contains(Flag o) {
        return flags.contains(o);
    }



}
