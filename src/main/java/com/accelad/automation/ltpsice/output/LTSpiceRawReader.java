package com.accelad.automation.ltpsice.output;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.accelad.automation.ltpsice.output.LTSpiceRaw.Builder;

public class LTSpiceRawReader {

    private static final String VARIABLES_HEADER = "Variables:";
    private static final String COMMAND_HEADER = "Command: ";
    private static final String OFFSET_HEADER = "Offset: ";
    private static final String NO_POINTS_HEADER = "No. Points: ";
    private static final String NO_VARIABLES_HEADER = "No. Variables: ";
    private static final String FLAGS_HEADER = "Flags: ";
    private static final String PLOTNAME_HEADER = "Plotname: ";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.US);
    private static final String DATE_HEADER = "Date: ";
    private static final String TITLE_HEADER = "Title: ";
    private static final Charset RAW_FILE_CHARSET = Charset.forName("UTF-16LE");

    public LTSpiceRaw read(InputStream is) throws IOException {

        MyReader br = new MyReader(is);
        
        Builder builder = LTSpiceRaw.builder();

        int variableCount = 0;
        int pointCount = 0;

        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(TITLE_HEADER)) {
                String title = line.substring(TITLE_HEADER.length());
                builder.withTitle(title);
            }
            if (line.startsWith(DATE_HEADER)) {
                String dateString = line.substring(DATE_HEADER.length());
                try {
                    builder.withDate(DATE_FORMAT.parse(dateString));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (line.startsWith(PLOTNAME_HEADER)) {
                String name = line.substring(PLOTNAME_HEADER.length());
                builder.withPlotName(name);
            }

            if (line.startsWith(FLAGS_HEADER)) {
                String flags = line.substring(FLAGS_HEADER.length());
                builder.withPlotName(flags);
            }

            if (line.startsWith(NO_VARIABLES_HEADER)) {
                String count = line.substring(NO_VARIABLES_HEADER.length());
                variableCount = Integer.parseInt(count);
                builder.withVariableCount(variableCount);
            }

            if (line.startsWith(NO_POINTS_HEADER)) {
                String count = line.substring(NO_POINTS_HEADER.length()).trim();
                pointCount = Integer.parseInt(count);
                builder.withPointCount(pointCount);
            }

            if (line.startsWith(OFFSET_HEADER)) {
                String text = line.substring(OFFSET_HEADER.length());
                builder.withOffset(Double.parseDouble(text));
            }

            if (line.startsWith(COMMAND_HEADER)) {
                String text = line.substring(COMMAND_HEADER.length());
                builder.withCommand(text);
            }

            if (line.startsWith(VARIABLES_HEADER)) {
                for (int i = 0; i < variableCount; i++) {
                    String variableLine = br.readLine();
                    String[] params = variableLine.split("\t");
                    int index = Integer.parseInt(params[1]);
                    String fullName = params[2];
                    String name = params[2];
                    String type = params[3];
                    builder.withVariable(new Variable(index, fullName, name, type));
                }
            }

            if (line.startsWith("Binary:")) {
                for (int i = 0; i < pointCount; i++) {
                    List<Number> values = new ArrayList<>();
                    for (int j = 0; j < variableCount; j++) {
                        if (j == 0) {
                            values.add(br.readDouble());
                        } else {
                            values.add(br.readFloat());
                        }
                    }
                    Point point = new Point(values);
                    builder.withPoint(point);
                }
                break;
            }
        }
        br.close();
        
        return builder.build();
    }

    private static class Complex {
        private double real;
        private double imaginary;

        public Complex(double real, double imaginary) {
            this.real = real;
            this.imaginary = imaginary;
        }

    }

    private class MyReader {
        public static final int READ_BUFFER_SIZE = 65536;
        DataInputStream dataInputStream;

        public MyReader(InputStream in) {
            BufferedInputStream bis = new BufferedInputStream(in, READ_BUFFER_SIZE);
            dataInputStream = new DataInputStream(bis);
        }

        public void close() throws IOException {
            dataInputStream.close();
        }

        protected String readLine()
                throws IOException {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (true) {
                int data = dataInputStream.read();
                baos.write(data);
                if (data == -1)
                    return null;
                if (data == '\n' || data == '\r') {
                    baos.write(dataInputStream.read());
                    break;
                }
            }

            String string = new String(baos.toByteArray(), RAW_FILE_CHARSET);
            return string.substring(0, string.length() - 1);
        }

        private Complex readDoubleComplex() throws IOException {
            double real = readDouble();
            double imaginary = readDouble();
            return new Complex(real, imaginary);
        }

        private double readDouble() throws IOException {
            long longValue = dataInputStream.readLong();
            if (ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN)
                longValue = Long.reverseBytes(longValue);
            return Double.longBitsToDouble(longValue);
        }

        private float readFloat() throws IOException {
            int lt = dataInputStream.readInt();
            if (ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN)
                lt = Integer.reverseBytes(lt);
            return Float.intBitsToFloat(lt);
        }

    }

}
