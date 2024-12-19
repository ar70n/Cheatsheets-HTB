package org.apache.commons.codec.binary;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;

public class Hex implements BinaryEncoder, BinaryDecoder {
    public static final Charset DEFAULT_CHARSET = Charsets.UTF_8;
    public static final String DEFAULT_CHARSET_NAME = "UTF-8";
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final Charset charset;

    public static byte[] decodeHex(String str) throws DecoderException {
        return decodeHex(str.toCharArray());
    }

    public static byte[] decodeHex(char[] cArr) throws DecoderException {
        int length = cArr.length;
        if ((length & 1) == 0) {
            byte[] bArr = new byte[(length >> 1)];
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i + 1;
                i = i3 + 1;
                bArr[i2] = (byte) (((toDigit(cArr[i], i) << 4) | toDigit(cArr[i3], i3)) & 255);
                i2++;
            }
            return bArr;
        }
        throw new DecoderException("Odd number of characters.");
    }

    public static char[] encodeHex(byte[] bArr) {
        return encodeHex(bArr, true);
    }

    public static char[] encodeHex(ByteBuffer byteBuffer) {
        return encodeHex(byteBuffer, true);
    }

    public static char[] encodeHex(byte[] bArr, boolean z) {
        return encodeHex(bArr, z ? DIGITS_LOWER : DIGITS_UPPER);
    }

    public static char[] encodeHex(ByteBuffer byteBuffer, boolean z) {
        return encodeHex(byteBuffer, z ? DIGITS_LOWER : DIGITS_UPPER);
    }

    protected static char[] encodeHex(byte[] bArr, char[] cArr) {
        int length = bArr.length;
        char[] cArr2 = new char[(length << 1)];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr2[i] = cArr[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr2[i3] = cArr[bArr[i2] & 15];
        }
        return cArr2;
    }

    protected static char[] encodeHex(ByteBuffer byteBuffer, char[] cArr) {
        return encodeHex(byteBuffer.array(), cArr);
    }

    public static String encodeHexString(byte[] bArr) {
        return new String(encodeHex(bArr));
    }

    public static String encodeHexString(byte[] bArr, boolean z) {
        return new String(encodeHex(bArr, z));
    }

    public static String encodeHexString(ByteBuffer byteBuffer) {
        return new String(encodeHex(byteBuffer));
    }

    public static String encodeHexString(ByteBuffer byteBuffer, boolean z) {
        return new String(encodeHex(byteBuffer, z));
    }

    protected static int toDigit(char c, int i) throws DecoderException {
        int digit = Character.digit(c, 16);
        if (digit != -1) {
            return digit;
        }
        throw new DecoderException("Illegal hexadecimal character " + c + " at index " + i);
    }

    public Hex() {
        this.charset = DEFAULT_CHARSET;
    }

    public Hex(Charset charset2) {
        this.charset = charset2;
    }

    public Hex(String str) {
        this(Charset.forName(str));
    }

    public byte[] decode(byte[] bArr) throws DecoderException {
        return decodeHex(new String(bArr, getCharset()).toCharArray());
    }

    public byte[] decode(ByteBuffer byteBuffer) throws DecoderException {
        return decodeHex(new String(byteBuffer.array(), getCharset()).toCharArray());
    }

    public Object decode(Object obj) throws DecoderException {
        if (obj instanceof String) {
            return decode((Object) ((String) obj).toCharArray());
        }
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        if (obj instanceof ByteBuffer) {
            return decode((ByteBuffer) obj);
        }
        try {
            return decodeHex((char[]) obj);
        } catch (ClassCastException e) {
            throw new DecoderException(e.getMessage(), e);
        }
    }

    public byte[] encode(byte[] bArr) {
        return encodeHexString(bArr).getBytes(getCharset());
    }

    public byte[] encode(ByteBuffer byteBuffer) {
        return encodeHexString(byteBuffer).getBytes(getCharset());
    }

    public Object encode(Object obj) throws EncoderException {
        byte[] bArr;
        if (obj instanceof String) {
            bArr = ((String) obj).getBytes(getCharset());
        } else if (obj instanceof ByteBuffer) {
            bArr = ((ByteBuffer) obj).array();
        } else {
            try {
                bArr = (byte[]) obj;
            } catch (ClassCastException e) {
                throw new EncoderException(e.getMessage(), e);
            }
        }
        return encodeHex(bArr);
    }

    public Charset getCharset() {
        return this.charset;
    }

    public String getCharsetName() {
        return this.charset.name();
    }

    public String toString() {
        return super.toString() + "[charsetName=" + this.charset + "]";
    }
}
