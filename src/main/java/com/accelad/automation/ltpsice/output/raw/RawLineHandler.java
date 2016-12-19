package com.accelad.automation.ltpsice.output.raw;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class RawLineHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RawLineHandler.class);
    private RawLineHandler nextHandler;
    
    public RawLineHandler setNext(RawLineHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }
    
    public boolean operation(String line) {
        try {
            if (operationSpec(line)) {
            	return true;
            }
        } catch (IOException e) {
            LOGGER.info("the handler was not able to read data from the stream.", e);
        }
        
        if(nextHandler != null) {
            return nextHandler.operation(line);
        }
        return false;
    }

    abstract boolean operationSpec(String line) throws IOException;
}