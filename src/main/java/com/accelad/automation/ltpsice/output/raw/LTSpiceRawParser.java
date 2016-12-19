package com.accelad.automation.ltpsice.output.raw;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accelad.automation.ltpsice.output.raw.LTSpiceRaw.Builder;

public class LTSpiceRawParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(LTSpiceRawParser.class);

    public LTSpiceRaw read(InputStream is) throws IOException {
        RawFileInputStreamReader streamReader = new RawFileInputStreamReader(is);
        Builder builder = LTSpiceRaw.builder();

        RawLineHandler firstHandler = new HandlerTitle(builder);
        RawLineHandler handler = firstHandler.setNext(new HandlerDate(builder));
        handler = handler.setNext(new HandlerPlotname(builder));
        handler = handler.setNext(new HandlerFlags(builder));
        handler = handler.setNext(new HandlerVariableCount(builder));
        handler = handler.setNext(new HandlerPointCount(builder));
        handler = handler.setNext(new HandlerOffset(builder));
        handler = handler.setNext(new HandlerCommand(builder));
        handler = handler.setNext(new HandlerCommand(builder));
        handler = handler.setNext(new HandlerVariables(builder, streamReader));
        handler.setNext(new HandlerBinary(builder, streamReader));

        String line;
        while ((line = streamReader.readLine()) != null) {
            boolean result = firstHandler.operation(line);

            if (!result) {
                LOGGER.warn("No raw line handler was configured to read the line : " + line);
            }
        }
        streamReader.close();
        
        return builder.build();
    }
}
