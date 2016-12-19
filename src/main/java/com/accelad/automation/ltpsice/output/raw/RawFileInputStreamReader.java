package com.accelad.automation.ltpsice.output.raw;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class RawFileInputStreamReader {
    private static final Charset RAW_FILE_CHARSET = Charset.forName("UTF-16LE");

    public static final int READ_BUFFER_SIZE = 65536;
    private final DataInputStream dataInputStream;

    public RawFileInputStreamReader(InputStream in) {
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

    double readDouble() throws IOException {
        long longValue = dataInputStream.readLong();
        if (ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN)
            longValue = Long.reverseBytes(longValue);
        return Double.longBitsToDouble(longValue);
    }

    float readFloat() throws IOException {
        int lt = dataInputStream.readInt();
        if (ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN)
            lt = Integer.reverseBytes(lt);
        return Float.intBitsToFloat(lt);
    }
}
