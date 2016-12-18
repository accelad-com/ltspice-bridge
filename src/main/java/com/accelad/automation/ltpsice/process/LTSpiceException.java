package com.accelad.automation.ltpsice.process;

public class LTSpiceException extends Exception {

    public LTSpiceException() {
        super();
    }

    public LTSpiceException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public LTSpiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public LTSpiceException(String message) {
        super(message);
    }

    public LTSpiceException(Throwable cause) {
        super(cause);
    }


}
