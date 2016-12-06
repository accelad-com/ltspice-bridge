package com.accelad.automation.ltpsice.output;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LogReader {

    private static final String TOTAL_ELAPSED_TIME = "Total elapsed time: ";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
            "EEE MMM dd HH:mm:ss yyyy", Locale.US);
    private static final String DATE_HEADER = "Date: ";
    private static final String CIRCUIT_HEADER = "Circuit: ";
    private static final Charset CHARSET = Charset.forName("UTF-16LE");

    public Log read(InputStream is) {
        Log log = new Log();
        try {
            InputStreamReader inputReader = new InputStreamReader(is, CHARSET);
            BufferedReader reader = new BufferedReader(inputReader);

            String line = reader.readLine();
            if (!line.startsWith(CIRCUIT_HEADER)) {
                throw new RuntimeException("invalid file format");
            }
            log.setTitle(line.substring(CIRCUIT_HEADER.length() + 1));

            checkEmptyLine(reader);
            log.setInfo(reader.readLine());
            checkEmptyLine(reader);

            log.setMeasures(readMeasures(reader));

            if (!line.startsWith(DATE_HEADER)) {
                throw new RuntimeException("invalid file format");
            }
            String dateString = line.substring(DATE_HEADER.length());
            log.setDate(DATE_FORMAT.parse(dateString));

            if (!line.startsWith(TOTAL_ELAPSED_TIME)) {
                throw new RuntimeException("invalid file format");
            }
            String timeString = line.substring(TOTAL_ELAPSED_TIME.length()).split(" ")[0];
            log.setElapsedTime(Double.parseDouble(timeString));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return log;
    }

    private List<Measure> readMeasures(BufferedReader reader) throws IOException {
        List<Measure> measures = new ArrayList<>();
        String line;
        while (!(line = reader.readLine()).isEmpty()) {
            String[] parameters = line.split(" ");
            String name = parameters[0].substring(0, parameters[0].length() - 1);
            String definition = parameters[0].split("=")[0];
            double value = Double.parseDouble(parameters[0].split("=")[1]);

            Measure measure = new Measure(name, definition, value);
            measures.add(measure);
        }

        return measures;
    }

    private void checkEmptyLine(BufferedReader reader) throws IOException {
        String emptyLine = reader.readLine();
        if (!emptyLine.isEmpty()) {
            throw new RuntimeException("invalid file format");
        }
    }

}
