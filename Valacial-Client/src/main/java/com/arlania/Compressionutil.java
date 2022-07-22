package com.arlania;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class Compressionutil {

    public static byte[] gunzip(byte[] bytes) {
        try {
            /* create the streams */
            InputStream is = new GZIPInputStream(new ByteArrayInputStream(bytes));
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                try {
                    /* copy data between the streams */
                    byte[] buf = new byte[4096];
                    int len = 0;
                    while ((len = is.read(buf, 0, buf.length)) != -1) {
                        os.write(buf, 0, len);
                    }
                } finally {
                    os.close();
                }

                /* return the uncompressed bytes */
                return os.toByteArray();
            } finally {
                is.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isCompressed(byte[] bytes)
    {
        if ((bytes == null) || (bytes.length < 2))
        {
            return false;
        }
        else
        {
            return ((bytes[0] == (byte) (GZIPInputStream.GZIP_MAGIC)) && (bytes[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8)));
        }
    }
}
