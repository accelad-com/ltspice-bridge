package com.accelad.automation.ltpsice.output.log;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LogLineHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogLineHandler.class);
    private LogLineHandler nextHandler;
    
    public LogLineHandler setNext(LogLineHandler nextHandler) {
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