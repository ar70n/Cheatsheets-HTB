package org.apache.commons.codec.binary;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import org.apache.commons.codec.binary.BaseNCodec;

public class BaseNCodecOutputStream extends FilterOutputStream {
    private final BaseNCodec baseNCodec;
    private final BaseNCodec.Context context = new BaseNCodec.Context();
    private final boolean doEncode;
    private final byte[] singleByte = new byte[1];

    public BaseNCodecOutputStream(OutputStream outputStream, BaseNCodec baseNCodec2, boolean z) {
        super(outputStream);
        this.baseNCodec = baseNCodec2;
        this.doEncode = z;
    }

    public void write(int i) throws IOException {
        byte[] bArr = this.singleByte;
        bArr[0] = (byte) i;
        write(bArr, 0, 1);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        Objects.requireNonNull(bArr);
        if (i < 0 || i2 < 0) {
            throw new IndexOutOfBoundsException();
        } else if (i > bArr.length || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        } else if (i2 > 0) {
            if (this.doEncode) {
                this.baseNCodec.encode(bArr, i, i2, this.context);
            } else {
                this.baseNCodec.decode(bArr, i, i2, this.context);
            }
            flush(false);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r1 = new byte[r0];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void flush(boolean r6) throws java.io.IOException {
        /*
            r5 = this;
            org.apache.commons.codec.binary.BaseNCodec r0 = r5.baseNCodec
            org.apache.commons.codec.binary.BaseNCodec$Context r1 = r5.context
            int r0 = r0.available(r1)
            if (r0 <= 0) goto L_0x001c
            byte[] r1 = new byte[r0]
            org.apache.commons.codec.binary.BaseNCodec r2 = r5.baseNCodec
            org.apache.commons.codec.binary.BaseNCodec$Context r3 = r5.context
            r4 = 0
            int r0 = r2.readResults(r1, r4, r0, r3)
            if (r0 <= 0) goto L_0x001c
            java.io.OutputStream r2 = r5.out
            r2.write(r1, r4, r0)
        L_0x001c:
            if (r6 == 0) goto L_0x0023
            java.io.OutputStream r6 = r5.out
            r6.flush()
        L_0x0023:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.binary.BaseNCodecOutputStream.flush(boolean):void");
    }

    public void flush() throws IOException {
        flush(true);
    }

    public void close() throws IOException {
        eof();
        flush();
        this.out.close();
    }

    public void eof() throws IOException {
        if (this.doEncode) {
            this.baseNCodec.encode(this.singleByte, 0, -1, this.context);
        } else {
            this.baseNCodec.decode(this.singleByte, 0, -1, this.context);
        }
    }
}
