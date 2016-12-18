package com.accelad.automation.ltpsice.netlist;

public class Value {

    private final String valueAsText;

    public Value(String value) {
        this.valueAsText = value;
    }

    public String getValue() {
        return valueAsText;
    }

    @Override
    public String toString() {
        return valueAsText;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((valueAsText == null) ? 0 : valueAsText.hashCode());
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
        Value other = (Value) obj;
        if (valueAsText == null) {
            if (other.valueAsText != null)
                return false;
        } else if (!valueAsText.equals(other.valueAsText))
            return false;
        return true;
    }

}
