package com.accelad.automation.ltpsice;

@SuppressWarnings("serial")
public class InvalidEngineeringNotationException extends RuntimeException {

    private final String invalidNotation;

    public InvalidEngineeringNotationException(String invalidNotation) {
        this.invalidNotation = invalidNotation;
    }

    public String getInvalidNotation() {
        return invalidNotation;
    }
}
