package org.apache.commons.codec.binary;

import org.apache.commons.codec.binary.BaseNCodec;

public class Base32 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 5;
    private static final int BYTES_PER_ENCODED_BLOCK = 8;
    private static final int BYTES_PER_UNENCODED_BLOCK = 5;
    private static final byte[] CHUNK_SEPARATOR = {13, 10};
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    private static final byte[] ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 50, 51, 52, 53, 54, 55};
    private static final byte[] HEX_DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private static final byte[] HEX_ENCODE_TABLE = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86};
    private static final int MASK_5BITS = 31;
    private final int decodeSize;
    private final byte[] decodeTable;
    private final int encodeSize;
    private final byte[] encodeTable;
    private final byte[] lineSeparator;

    public Base32() {
        this(false);
    }

    public Base32(byte b) {
        this(false, b);
    }

    public Base32(boolean z) {
        this(0, (byte[]) null, z, (byte) 61);
    }

    public Base32(boolean z, byte b) {
        this(0, (byte[]) null, z, b);
    }

    public Base32(int i) {
        this(i, CHUNK_SEPARATOR);
    }

    public Base32(int i, byte[] bArr) {
        this(i, bArr, false, (byte) 61);
    }

    public Base32(int i, byte[] bArr, boolean z) {
        this(i, bArr, z, (byte) 61);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Base32(int i, byte[] bArr, boolean z, byte b) {
        super(5, 8, i, bArr == null ? 0 : bArr.length, b);
        if (z) {
            this.encodeTable = HEX_ENCODE_TABLE;
            this.decodeTable = HEX_DECODE_TABLE;
        } else {
            this.encodeTable = ENCODE_TABLE;
            this.decodeTable = DECODE_TABLE;
        }
        if (i <= 0) {
            this.encodeSize = 8;
            this.lineSeparator = null;
        } else if (bArr == null) {
            throw new IllegalArgumentException("lineLength " + i + " > 0, but lineSeparator is null");
        } else if (!containsAlphabetOrPad(bArr)) {
            this.encodeSize = bArr.length + 8;
            byte[] bArr2 = new byte[bArr.length];
            this.lineSeparator = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        } else {
            throw new IllegalArgumentException("lineSeparator must not contain Base32 characters: [" + StringUtils.newStringUtf8(bArr) + "]");
        }
        this.decodeSize = this.encodeSize - 1;
        if (isInAlphabet(b) || isWhiteSpace(b)) {
            throw new IllegalArgumentException("pad must not be in alphabet or whitespace");
        }
    }

    /* access modifiers changed from: package-private */
    public void decode(byte[] bArr, int i, int i2, BaseNCodec.Context context) {
        byte b;
        int i3 = i2;
        BaseNCodec.Context context2 = context;
        if (!context2.eof) {
            boolean z = true;
            if (i3 < 0) {
                context2.eof = true;
            }
            int i4 = 0;
            int i5 = i;
            while (true) {
                if (i4 >= i3) {
                    break;
                }
                int i6 = i5 + 1;
                byte b2 = bArr[i5];
                if (b2 == this.pad) {
                    context2.eof = z;
                    break;
                }
                byte[] ensureBufferSize = ensureBufferSize(this.decodeSize, context2);
                if (b2 >= 0) {
                    byte[] bArr2 = this.decodeTable;
                    if (b2 < bArr2.length && (b = bArr2[b2]) >= 0) {
                        context2.modulus = (context2.modulus + (z ? 1 : 0)) % 8;
                        context2.lbitWorkArea = (context2.lbitWorkArea << 5) + ((long) b);
                        if (context2.modulus == 0) {
                            int i7 = context2.pos;
                            context2.pos = i7 + 1;
                            ensureBufferSize[i7] = (byte) ((int) ((context2.lbitWorkArea >> 32) & 255));
                            int i8 = context2.pos;
                            context2.pos = i8 + 1;
                            ensureBufferSize[i8] = (byte) ((int) ((context2.lbitWorkArea >> 24) & 255));
                            int i9 = context2.pos;
                            context2.pos = i9 + 1;
                            ensureBufferSize[i9] = (byte) ((int) ((context2.lbitWorkArea >> 16) & 255));
                            int i10 = context2.pos;
                            context2.pos = i10 + 1;
                            ensureBufferSize[i10] = (byte) ((int) ((context2.lbitWorkArea >> 8) & 255));
                            int i11 = context2.pos;
                            context2.pos = i11 + 1;
                            ensureBufferSize[i11] = (byte) ((int) (context2.lbitWorkArea & 255));
                        }
                    }
                }
                i4++;
                i5 = i6;
                z = true;
            }
            if (context2.eof && context2.modulus >= 2) {
                byte[] ensureBufferSize2 = ensureBufferSize(this.decodeSize, context2);
                switch (context2.modulus) {
                    case 2:
                        int i12 = context2.pos;
                        context2.pos = i12 + 1;
                        ensureBufferSize2[i12] = (byte) ((int) ((context2.lbitWorkArea >> 2) & 255));
                        return;
                    case 3:
                        int i13 = context2.pos;
                        context2.pos = i13 + 1;
                        ensureBufferSize2[i13] = (byte) ((int) ((context2.lbitWorkArea >> 7) & 255));
                        return;
                    case 4:
                        context2.lbitWorkArea >>= 4;
                        int i14 = context2.pos;
                        context2.pos = i14 + 1;
                        ensureBufferSize2[i14] = (byte) ((int) ((context2.lbitWorkArea >> 8) & 255));
                        int i15 = context2.pos;
                        context2.pos = i15 + 1;
                        ensureBufferSize2[i15] = (byte) ((int) (context2.lbitWorkArea & 255));
                        return;
                    case 5:
                        context2.lbitWorkArea >>= 1;
                        int i16 = context2.pos;
                        context2.pos = i16 + 1;
                        ensureBufferSize2[i16] = (byte) ((int) ((context2.lbitWorkArea >> 16) & 255));
                        int i17 = context2.pos;
                        context2.pos = i17 + 1;
                        ensureBufferSize2[i17] = (byte) ((int) ((context2.lbitWorkArea >> 8) & 255));
                        int i18 = context2.pos;
                        context2.pos = i18 + 1;
                        ensureBufferSize2[i18] = (byte) ((int) (context2.lbitWorkArea & 255));
                        return;
                    case 6:
                        context2.lbitWorkArea >>= 6;
                        int i19 = context2.pos;
                        context2.pos = i19 + 1;
                        ensureBufferSize2[i19] = (byte) ((int) ((context2.lbitWorkArea >> 16) & 255));
                        int i20 = context2.pos;
                        context2.pos = i20 + 1;
                        ensureBufferSize2[i20] = (byte) ((int) ((context2.lbitWorkArea >> 8) & 255));
                        int i21 = context2.pos;
                        context2.pos = i21 + 1;
                        ensureBufferSize2[i21] = (byte) ((int) (context2.lbitWorkArea & 255));
                        return;
                    case 7:
                        context2.lbitWorkArea >>= 3;
                        int i22 = context2.pos;
                        context2.pos = i22 + 1;
                        ensureBufferSize2[i22] = (byte) ((int) ((context2.lbitWorkArea >> 24) & 255));
                        int i23 = context2.pos;
                        context2.pos = i23 + 1;
                        ensureBufferSize2[i23] = (byte) ((int) ((context2.lbitWorkArea >> 16) & 255));
                        int i24 = context2.pos;
                        context2.pos = i24 + 1;
                        ensureBufferSize2[i24] = (byte) ((int) ((context2.lbitWorkArea >> 8) & 255));
                        int i25 = context2.pos;
                        context2.pos = i25 + 1;
                        ensureBufferSize2[i25] = (byte) ((int) (context2.lbitWorkArea & 255));
                        return;
                    default:
                        throw new IllegalStateException("Impossible modulus " + context2.modulus);
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v33, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void encode(byte[] r12, int r13, int r14, org.apache.commons.codec.binary.BaseNCodec.Context r15) {
        /*
            r11 = this;
            boolean r0 = r15.eof
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            r0 = 0
            r1 = 1
            if (r14 >= 0) goto L_0x0255
            r15.eof = r1
            int r12 = r15.modulus
            if (r12 != 0) goto L_0x0014
            int r12 = r11.lineLength
            if (r12 != 0) goto L_0x0014
            return
        L_0x0014:
            int r12 = r11.encodeSize
            byte[] r12 = r11.ensureBufferSize(r12, r15)
            int r13 = r15.pos
            int r14 = r15.modulus
            if (r14 == 0) goto L_0x0231
            r2 = 3
            r3 = 2
            if (r14 == r1) goto L_0x01d0
            r4 = 4
            if (r14 == r3) goto L_0x015c
            if (r14 == r2) goto L_0x00db
            if (r14 != r4) goto L_0x00c0
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte[] r1 = r11.encodeTable
            long r4 = r15.lbitWorkArea
            r6 = 27
            long r4 = r4 >> r6
            int r4 = (int) r4
            r4 = r4 & 31
            byte r1 = r1[r4]
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte[] r1 = r11.encodeTable
            long r4 = r15.lbitWorkArea
            r6 = 22
            long r4 = r4 >> r6
            int r4 = (int) r4
            r4 = r4 & 31
            byte r1 = r1[r4]
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte[] r1 = r11.encodeTable
            long r4 = r15.lbitWorkArea
            r6 = 17
            long r4 = r4 >> r6
            int r4 = (int) r4
            r4 = r4 & 31
            byte r1 = r1[r4]
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte[] r1 = r11.encodeTable
            long r4 = r15.lbitWorkArea
            r6 = 12
            long r4 = r4 >> r6
            int r4 = (int) r4
            r4 = r4 & 31
            byte r1 = r1[r4]
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte[] r1 = r11.encodeTable
            long r4 = r15.lbitWorkArea
            r6 = 7
            long r4 = r4 >> r6
            int r4 = (int) r4
            r4 = r4 & 31
            byte r1 = r1[r4]
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte[] r1 = r11.encodeTable
            long r4 = r15.lbitWorkArea
            long r3 = r4 >> r3
            int r3 = (int) r3
            r3 = r3 & 31
            byte r1 = r1[r3]
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte[] r1 = r11.encodeTable
            long r3 = r15.lbitWorkArea
            long r2 = r3 << r2
            int r2 = (int) r2
            r2 = r2 & 31
            byte r1 = r1[r2]
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte r1 = r11.pad
            r12[r14] = r1
            goto L_0x0231
        L_0x00c0:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "Impossible modulus "
            java.lang.StringBuilder r13 = r13.append(r14)
            int r14 = r15.modulus
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        L_0x00db:
            int r14 = r15.pos
            int r2 = r14 + 1
            r15.pos = r2
            byte[] r2 = r11.encodeTable
            long r5 = r15.lbitWorkArea
            r3 = 19
            long r5 = r5 >> r3
            int r3 = (int) r5
            r3 = r3 & 31
            byte r2 = r2[r3]
            r12[r14] = r2
            int r14 = r15.pos
            int r2 = r14 + 1
            r15.pos = r2
            byte[] r2 = r11.encodeTable
            long r5 = r15.lbitWorkArea
            r3 = 14
            long r5 = r5 >> r3
            int r3 = (int) r5
            r3 = r3 & 31
            byte r2 = r2[r3]
            r12[r14] = r2
            int r14 = r15.pos
            int r2 = r14 + 1
            r15.pos = r2
            byte[] r2 = r11.encodeTable
            long r5 = r15.lbitWorkArea
            r3 = 9
            long r5 = r5 >> r3
            int r3 = (int) r5
            r3 = r3 & 31
            byte r2 = r2[r3]
            r12[r14] = r2
            int r14 = r15.pos
            int r2 = r14 + 1
            r15.pos = r2
            byte[] r2 = r11.encodeTable
            long r5 = r15.lbitWorkArea
            long r3 = r5 >> r4
            int r3 = (int) r3
            r3 = r3 & 31
            byte r2 = r2[r3]
            r12[r14] = r2
            int r14 = r15.pos
            int r2 = r14 + 1
            r15.pos = r2
            byte[] r2 = r11.encodeTable
            long r3 = r15.lbitWorkArea
            long r3 = r3 << r1
            int r1 = (int) r3
            r1 = r1 & 31
            byte r1 = r2[r1]
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte r1 = r11.pad
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte r1 = r11.pad
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte r1 = r11.pad
            r12[r14] = r1
            goto L_0x0231
        L_0x015c:
            int r14 = r15.pos
            int r2 = r14 + 1
            r15.pos = r2
            byte[] r2 = r11.encodeTable
            long r5 = r15.lbitWorkArea
            r3 = 11
            long r5 = r5 >> r3
            int r3 = (int) r5
            r3 = r3 & 31
            byte r2 = r2[r3]
            r12[r14] = r2
            int r14 = r15.pos
            int r2 = r14 + 1
            r15.pos = r2
            byte[] r2 = r11.encodeTable
            long r5 = r15.lbitWorkArea
            r3 = 6
            long r5 = r5 >> r3
            int r3 = (int) r5
            r3 = r3 & 31
            byte r2 = r2[r3]
            r12[r14] = r2
            int r14 = r15.pos
            int r2 = r14 + 1
            r15.pos = r2
            byte[] r2 = r11.encodeTable
            long r5 = r15.lbitWorkArea
            long r5 = r5 >> r1
            int r1 = (int) r5
            r1 = r1 & 31
            byte r1 = r2[r1]
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte[] r1 = r11.encodeTable
            long r2 = r15.lbitWorkArea
            long r2 = r2 << r4
            int r2 = (int) r2
            r2 = r2 & 31
            byte r1 = r1[r2]
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte r1 = r11.pad
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte r1 = r11.pad
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte r1 = r11.pad
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte r1 = r11.pad
            r12[r14] = r1
            goto L_0x0231
        L_0x01d0:
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte[] r1 = r11.encodeTable
            long r4 = r15.lbitWorkArea
            long r4 = r4 >> r2
            int r2 = (int) r4
            r2 = r2 & 31
            byte r1 = r1[r2]
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte[] r1 = r11.encodeTable
            long r4 = r15.lbitWorkArea
            long r2 = r4 << r3
            int r2 = (int) r2
            r2 = r2 & 31
            byte r1 = r1[r2]
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte r1 = r11.pad
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte r1 = r11.pad
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte r1 = r11.pad
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte r1 = r11.pad
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte r1 = r11.pad
            r12[r14] = r1
            int r14 = r15.pos
            int r1 = r14 + 1
            r15.pos = r1
            byte r1 = r11.pad
            r12[r14] = r1
        L_0x0231:
            int r14 = r15.currentLinePos
            int r1 = r15.pos
            int r1 = r1 - r13
            int r14 = r14 + r1
            r15.currentLinePos = r14
            int r13 = r11.lineLength
            if (r13 <= 0) goto L_0x033d
            int r13 = r15.currentLinePos
            if (r13 <= 0) goto L_0x033d
            byte[] r13 = r11.lineSeparator
            int r14 = r15.pos
            byte[] r1 = r11.lineSeparator
            int r1 = r1.length
            java.lang.System.arraycopy(r13, r0, r12, r14, r1)
            int r12 = r15.pos
            byte[] r13 = r11.lineSeparator
            int r13 = r13.length
            int r12 = r12 + r13
            r15.pos = r12
            goto L_0x033d
        L_0x0255:
            r2 = r0
        L_0x0256:
            if (r2 >= r14) goto L_0x033d
            int r3 = r11.encodeSize
            byte[] r3 = r11.ensureBufferSize(r3, r15)
            int r4 = r15.modulus
            int r4 = r4 + r1
            r5 = 5
            int r4 = r4 % r5
            r15.modulus = r4
            int r4 = r13 + 1
            byte r13 = r12[r13]
            if (r13 >= 0) goto L_0x026d
            int r13 = r13 + 256
        L_0x026d:
            long r6 = r15.lbitWorkArea
            r8 = 8
            long r6 = r6 << r8
            long r9 = (long) r13
            long r6 = r6 + r9
            r15.lbitWorkArea = r6
            int r13 = r15.modulus
            if (r13 != 0) goto L_0x0338
            int r13 = r15.pos
            int r6 = r13 + 1
            r15.pos = r6
            byte[] r6 = r11.encodeTable
            long r9 = r15.lbitWorkArea
            r7 = 35
            long r9 = r9 >> r7
            int r7 = (int) r9
            r7 = r7 & 31
            byte r6 = r6[r7]
            r3[r13] = r6
            int r13 = r15.pos
            int r6 = r13 + 1
            r15.pos = r6
            byte[] r6 = r11.encodeTable
            long r9 = r15.lbitWorkArea
            r7 = 30
            long r9 = r9 >> r7
            int r7 = (int) r9
            r7 = r7 & 31
            byte r6 = r6[r7]
            r3[r13] = r6
            int r13 = r15.pos
            int r6 = r13 + 1
            r15.pos = r6
            byte[] r6 = r11.encodeTable
            long r9 = r15.lbitWorkArea
            r7 = 25
            long r9 = r9 >> r7
            int r7 = (int) r9
            r7 = r7 & 31
            byte r6 = r6[r7]
            r3[r13] = r6
            int r13 = r15.pos
            int r6 = r13 + 1
            r15.pos = r6
            byte[] r6 = r11.encodeTable
            long r9 = r15.lbitWorkArea
            r7 = 20
            long r9 = r9 >> r7
            int r7 = (int) r9
            r7 = r7 & 31
            byte r6 = r6[r7]
            r3[r13] = r6
            int r13 = r15.pos
            int r6 = r13 + 1
            r15.pos = r6
            byte[] r6 = r11.encodeTable
            long r9 = r15.lbitWorkArea
            r7 = 15
            long r9 = r9 >> r7
            int r7 = (int) r9
            r7 = r7 & 31
            byte r6 = r6[r7]
            r3[r13] = r6
            int r13 = r15.pos
            int r6 = r13 + 1
            r15.pos = r6
            byte[] r6 = r11.encodeTable
            long r9 = r15.lbitWorkArea
            r7 = 10
            long r9 = r9 >> r7
            int r7 = (int) r9
            r7 = r7 & 31
            byte r6 = r6[r7]
            r3[r13] = r6
            int r13 = r15.pos
            int r6 = r13 + 1
            r15.pos = r6
            byte[] r6 = r11.encodeTable
            long r9 = r15.lbitWorkArea
            long r9 = r9 >> r5
            int r5 = (int) r9
            r5 = r5 & 31
            byte r5 = r6[r5]
            r3[r13] = r5
            int r13 = r15.pos
            int r5 = r13 + 1
            r15.pos = r5
            byte[] r5 = r11.encodeTable
            long r6 = r15.lbitWorkArea
            int r6 = (int) r6
            r6 = r6 & 31
            byte r5 = r5[r6]
            r3[r13] = r5
            int r13 = r15.currentLinePos
            int r13 = r13 + r8
            r15.currentLinePos = r13
            int r13 = r11.lineLength
            if (r13 <= 0) goto L_0x0338
            int r13 = r11.lineLength
            int r5 = r15.currentLinePos
            if (r13 > r5) goto L_0x0338
            byte[] r13 = r11.lineSeparator
            int r5 = r15.pos
            byte[] r6 = r11.lineSeparator
            int r6 = r6.length
            java.lang.System.arraycopy(r13, r0, r3, r5, r6)
            int r13 = r15.pos
            byte[] r3 = r11.lineSeparator
            int r3 = r3.length
            int r13 = r13 + r3
            r15.pos = r13
            r15.currentLinePos = r0
        L_0x0338:
            int r2 = r2 + 1
            r13 = r4
            goto L_0x0256
        L_0x033d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.binary.Base32.encode(byte[], int, int, org.apache.commons.codec.binary.BaseNCodec$Context):void");
    }

    public boolean isInAlphabet(byte b) {
        if (b >= 0) {
            byte[] bArr = this.decodeTable;
            return b < bArr.length && bArr[b] != -1;
        }
    }
}
