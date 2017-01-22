package com.accelad.automation.ltpsice.output.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogParser.class);



    private static final Charset CHARSET = Charset.forName("UTF-16LE");

    public Log read(InputStream is) throws IOException {
        Log log = new Log();

        InputStreamReader inputReader = new InputStreamReader(is, CHARSET);
        BufferedReader reader = new BufferedReader(inputReader);

        LogLineHandler firsthandler = createHandlerChain(log, reader);

        String line;
        while ((line = reader.readLine()) != null) {
            boolean result = firsthandler.operation(line);
            if (!result) {
                LOGGER.warn("No raw line handler was configured to read the line : " + line);
            }
        }

        return log;
    }

    private LogLineHandler createHandlerChain(Log log, BufferedReader reader) {
        LogLineHandler firsthandler = new HandlerEmpty();
        LogLineHandler handler = firsthandler.setNext(new HandlerCircuit(log));
        handler = handler.setNext(new HandlerDate(log));
        handler = handler.setNext(new HandlerElapsedTime(log));
        handler = handler.setNext(new HandlerTnom(log));
        handler = handler.setNext(new HandlerTemp(log));
        handler = handler.setNext(new HandlerMethod(log));
        handler = handler.setNext(new HandlerTotalIteration(log));
        handler = handler.setNext(new HandlerTransientIteration(log));
        handler = handler.setNext(new HandlerTransientPoints(log));
        handler = handler.setNext(new HandlerAcceptedPoints(log));
        handler = handler.setNext(new HandlerRejectedPoints(log));
        handler = handler.setNext(new HandlerMatrixSize(log));
        handler = handler.setNext(new HandlerFillins(log));
        handler = handler.setNext(new HandlerSolver(log));
        handler = handler.setNext(new HandlerMatrixCompilerOne(log));
        handler = handler.setNext(new HandlerMatrixCompilerTwo(log));
        handler = handler.setNext(new HandlerDirectNewtonIterationSuccess(log));
        handler = handler.setNext(new HandlerOPPointFoundByInspection(log));
        handler = handler.setNext(new HandlerWarning(log));
        handler = handler.setNext(new HandlerHeightenedDefCon(log));
        handler = handler.setNext(new HandlerSkipOperatingPoint(log));
        handler = handler.setNext(new HandlerMeasure(log));
        handler = handler.setNext(new HandlerThreadVector(log));
        handler = handler.setNext(new HandlerChangeTseed());
        handler.setNext(new HandlerGminStepping(log, reader));
        return firsthandler;
    }
}
