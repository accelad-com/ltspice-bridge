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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((flags == null) ? 0 : flags.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ListOfFlags other = (ListOfFlags) obj;
        if (flags == null) {
            if (other.flags != null)
                return false;
        } else if (!flags.equals(other.flags))
            return false;
        return true;
    }


}
