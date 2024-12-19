package org.apache.commons.codec.net;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.BitSet;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.binary.StringUtils;

public class QuotedPrintableCodec implements BinaryEncoder, BinaryDecoder, StringEncoder, StringDecoder {
    private static final byte CR = 13;
    private static final byte ESCAPE_CHAR = 61;
    private static final byte LF = 10;
    private static final BitSet PRINTABLE_CHARS = new BitSet(256);
    private static final int SAFE_LENGTH = 73;
    private static final byte SPACE = 32;
    private static final byte TAB = 9;
    private final Charset charset;
    private final boolean strict;

    private static boolean isWhitespace(int i) {
        return i == 32 || i == 9;
    }

    static {
        for (int i = 33; i <= 60; i++) {
            PRINTABLE_CHARS.set(i);
        }
        for (int i2 = 62; i2 <= 126; i2++) {
            PRINTABLE_CHARS.set(i2);
        }
        BitSet bitSet = PRINTABLE_CHARS;
        bitSet.set(9);
        bitSet.set(32);
    }

    public QuotedPrintableCodec() {
        this(Charsets.UTF_8, false);
    }

    public QuotedPrintableCodec(boolean z) {
        this(Charsets.UTF_8, z);
    }

    public QuotedPrintableCodec(Charset charset2) {
        this(charset2, false);
    }

    public QuotedPrintableCodec(Charset charset2, boolean z) {
        this.charset = charset2;
        this.strict = z;
    }

    public QuotedPrintableCodec(String str) throws IllegalCharsetNameException, IllegalArgumentException, UnsupportedCharsetException {
        this(Charset.forName(str), false);
    }

    private static final int encodeQuotedPrintable(int i, ByteArrayOutputStream byteArrayOutputStream) {
        byteArrayOutputStream.write(61);
        char hexDigit = Utils.hexDigit(i >> 4);
        char hexDigit2 = Utils.hexDigit(i);
        byteArrayOutputStream.write(hexDigit);
        byteArrayOutputStream.write(hexDigit2);
        return 3;
    }

    private static int getUnsignedOctet(int i, byte[] bArr) {
        byte b = bArr[i];
        return b < 0 ? b + 256 : b;
    }

    private static int encodeByte(int i, boolean z, ByteArrayOutputStream byteArrayOutputStream) {
        if (z) {
            return encodeQuotedPrintable(i, byteArrayOutputStream);
        }
        byteArrayOutputStream.write(i);
        return 1;
    }

    public static final byte[] encodeQuotedPrintable(BitSet bitSet, byte[] bArr) {
        return encodeQuotedPrintable(bitSet, bArr, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final byte[] encodeQuotedPrintable(java.util.BitSet r9, byte[] r10, boolean r11) {
        /*
            if (r10 != 0) goto L_0x0004
            r9 = 0
            return r9
        L_0x0004:
            if (r9 != 0) goto L_0x0008
            java.util.BitSet r9 = PRINTABLE_CHARS
        L_0x0008:
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 0
            if (r11 == 0) goto L_0x00a3
            r11 = 1
            r3 = r11
            r2 = r1
        L_0x0013:
            int r4 = r10.length
            int r4 = r4 + -3
            r5 = 10
            r6 = 13
            r7 = 61
            if (r2 >= r4) goto L_0x0051
            int r4 = getUnsignedOctet(r2, r10)
            r8 = 73
            if (r3 >= r8) goto L_0x0031
            boolean r5 = r9.get(r4)
            r5 = r5 ^ r11
            int r4 = encodeByte(r4, r5, r0)
            int r3 = r3 + r4
            goto L_0x004e
        L_0x0031:
            boolean r3 = r9.get(r4)
            if (r3 == 0) goto L_0x0040
            boolean r3 = isWhitespace(r4)
            if (r3 == 0) goto L_0x003e
            goto L_0x0040
        L_0x003e:
            r3 = r1
            goto L_0x0041
        L_0x0040:
            r3 = r11
        L_0x0041:
            encodeByte(r4, r3, r0)
            r0.write(r7)
            r0.write(r6)
            r0.write(r5)
            r3 = r11
        L_0x004e:
            int r2 = r2 + 1
            goto L_0x0013
        L_0x0051:
            int r2 = r10.length
            int r2 = r2 + -3
            int r2 = getUnsignedOctet(r2, r10)
            boolean r4 = r9.get(r2)
            if (r4 == 0) goto L_0x006b
            boolean r4 = isWhitespace(r2)
            if (r4 == 0) goto L_0x0069
            r4 = 68
            if (r3 <= r4) goto L_0x0069
            goto L_0x006b
        L_0x0069:
            r4 = r1
            goto L_0x006c
        L_0x006b:
            r4 = r11
        L_0x006c:
            int r2 = encodeByte(r2, r4, r0)
            int r3 = r3 + r2
            r2 = 71
            if (r3 <= r2) goto L_0x007e
            r0.write(r7)
            r0.write(r6)
            r0.write(r5)
        L_0x007e:
            int r2 = r10.length
            int r2 = r2 + -2
        L_0x0081:
            int r3 = r10.length
            if (r2 >= r3) goto L_0x00bc
            int r3 = getUnsignedOctet(r2, r10)
            boolean r4 = r9.get(r3)
            if (r4 == 0) goto L_0x009c
            int r4 = r10.length
            int r4 = r4 + -2
            if (r2 <= r4) goto L_0x009a
            boolean r4 = isWhitespace(r3)
            if (r4 == 0) goto L_0x009a
            goto L_0x009c
        L_0x009a:
            r4 = r1
            goto L_0x009d
        L_0x009c:
            r4 = r11
        L_0x009d:
            encodeByte(r3, r4, r0)
            int r2 = r2 + 1
            goto L_0x0081
        L_0x00a3:
            int r11 = r10.length
        L_0x00a4:
            if (r1 >= r11) goto L_0x00bc
            byte r2 = r10[r1]
            if (r2 >= 0) goto L_0x00ac
            int r2 = r2 + 256
        L_0x00ac:
            boolean r3 = r9.get(r2)
            if (r3 == 0) goto L_0x00b6
            r0.write(r2)
            goto L_0x00b9
        L_0x00b6:
            encodeQuotedPrintable((int) r2, (java.io.ByteArrayOutputStream) r0)
        L_0x00b9:
            int r1 = r1 + 1
            goto L_0x00a4
        L_0x00bc:
            byte[] r9 = r0.toByteArray()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.net.QuotedPrintableCodec.encodeQuotedPrintable(java.util.BitSet, byte[], boolean):byte[]");
    }

    public static final byte[] decodeQuotedPrintable(byte[] bArr) throws DecoderException {
        if (bArr == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < bArr.length) {
            byte b = bArr[i];
            if (b == 61) {
                i++;
                try {
                    if (bArr[i] != 13) {
                        int digit16 = Utils.digit16(bArr[i]);
                        i++;
                        byteArrayOutputStream.write((char) ((digit16 << 4) + Utils.digit16(bArr[i])));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DecoderException("Invalid quoted-printable encoding", e);
                }
            } else if (!(b == 13 || b == 10)) {
                byteArrayOutputStream.write(b);
            }
            i++;
        }
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] encode(byte[] bArr) {
        return encodeQuotedPrintable(PRINTABLE_CHARS, bArr, this.strict);
    }

    public byte[] decode(byte[] bArr) throws DecoderException {
        return decodeQuotedPrintable(bArr);
    }

    public String encode(String str) throws EncoderException {
        return encode(str, getCharset());
    }

    public String decode(String str, Charset charset2) throws DecoderException {
        if (str == null) {
            return null;
        }
        return new String(decode(StringUtils.getBytesUsAscii(str)), charset2);
    }

    public String decode(String str, String str2) throws DecoderException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return new String(decode(StringUtils.getBytesUsAscii(str)), str2);
    }

    public String decode(String str) throws DecoderException {
        return decode(str, getCharset());
    }

    public Object encode(Object obj) throws EncoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return encode((byte[]) obj);
        }
        if (obj instanceof String) {
            return encode((String) obj);
        }
        throw new EncoderException("Objects of type " + obj.getClass().getName() + " cannot be quoted-printable encoded");
    }

    public Object decode(Object obj) throws DecoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        if (obj instanceof String) {
            return decode((String) obj);
        }
        throw new DecoderException("Objects of type " + obj.getClass().getName() + " cannot be quoted-printable decoded");
    }

    public Charset getCharset() {
        return this.charset;
    }

    public String getDefaultCharset() {
        return this.charset.name();
    }

    public String encode(String str, Charset charset2) {
        if (str == null) {
            return null;
        }
        return StringUtils.newStringUsAscii(encode(str.getBytes(charset2)));
    }

    public String encode(String str, String str2) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return StringUtils.newStringUsAscii(encode(str.getBytes(str2)));
    }
}
