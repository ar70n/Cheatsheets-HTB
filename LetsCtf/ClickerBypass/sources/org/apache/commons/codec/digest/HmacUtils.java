package org.apache.commons.codec.digest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;

public final class HmacUtils {
    private static final int STREAM_BUFFER_LENGTH = 1024;
    private final Mac mac;

    public static boolean isAvailable(String str) {
        try {
            Mac.getInstance(str);
            return true;
        } catch (NoSuchAlgorithmException unused) {
            return false;
        }
    }

    public static boolean isAvailable(HmacAlgorithms hmacAlgorithms) {
        try {
            Mac.getInstance(hmacAlgorithms.getName());
            return true;
        } catch (NoSuchAlgorithmException unused) {
            return false;
        }
    }

    @Deprecated
    public static Mac getHmacMd5(byte[] bArr) {
        return getInitializedMac(HmacAlgorithms.HMAC_MD5, bArr);
    }

    @Deprecated
    public static Mac getHmacSha1(byte[] bArr) {
        return getInitializedMac(HmacAlgorithms.HMAC_SHA_1, bArr);
    }

    @Deprecated
    public static Mac getHmacSha256(byte[] bArr) {
        return getInitializedMac(HmacAlgorithms.HMAC_SHA_256, bArr);
    }

    @Deprecated
    public static Mac getHmacSha384(byte[] bArr) {
        return getInitializedMac(HmacAlgorithms.HMAC_SHA_384, bArr);
    }

    @Deprecated
    public static Mac getHmacSha512(byte[] bArr) {
        return getInitializedMac(HmacAlgorithms.HMAC_SHA_512, bArr);
    }

    public static Mac getInitializedMac(HmacAlgorithms hmacAlgorithms, byte[] bArr) {
        return getInitializedMac(hmacAlgorithms.getName(), bArr);
    }

    public static Mac getInitializedMac(String str, byte[] bArr) {
        if (bArr != null) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, str);
                Mac instance = Mac.getInstance(str);
                instance.init(secretKeySpec);
                return instance;
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalArgumentException(e);
            } catch (InvalidKeyException e2) {
                throw new IllegalArgumentException(e2);
            }
        } else {
            throw new IllegalArgumentException("Null key");
        }
    }

    @Deprecated
    public static byte[] hmacMd5(byte[] bArr, byte[] bArr2) {
        return new HmacUtils(HmacAlgorithms.HMAC_MD5, bArr).hmac(bArr2);
    }

    @Deprecated
    public static byte[] hmacMd5(byte[] bArr, InputStream inputStream) throws IOException {
        return new HmacUtils(HmacAlgorithms.HMAC_MD5, bArr).hmac(inputStream);
    }

    @Deprecated
    public static byte[] hmacMd5(String str, String str2) {
        return new HmacUtils(HmacAlgorithms.HMAC_MD5, str).hmac(str2);
    }

    @Deprecated
    public static String hmacMd5Hex(byte[] bArr, byte[] bArr2) {
        return new HmacUtils(HmacAlgorithms.HMAC_MD5, bArr).hmacHex(bArr2);
    }

    @Deprecated
    public static String hmacMd5Hex(byte[] bArr, InputStream inputStream) throws IOException {
        return new HmacUtils(HmacAlgorithms.HMAC_MD5, bArr).hmacHex(inputStream);
    }

    @Deprecated
    public static String hmacMd5Hex(String str, String str2) {
        return new HmacUtils(HmacAlgorithms.HMAC_MD5, str).hmacHex(str2);
    }

    @Deprecated
    public static byte[] hmacSha1(byte[] bArr, byte[] bArr2) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_1, bArr).hmac(bArr2);
    }

    @Deprecated
    public static byte[] hmacSha1(byte[] bArr, InputStream inputStream) throws IOException {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_1, bArr).hmac(inputStream);
    }

    @Deprecated
    public static byte[] hmacSha1(String str, String str2) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_1, str).hmac(str2);
    }

    @Deprecated
    public static String hmacSha1Hex(byte[] bArr, byte[] bArr2) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_1, bArr).hmacHex(bArr2);
    }

    @Deprecated
    public static String hmacSha1Hex(byte[] bArr, InputStream inputStream) throws IOException {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_1, bArr).hmacHex(inputStream);
    }

    @Deprecated
    public static String hmacSha1Hex(String str, String str2) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_1, str).hmacHex(str2);
    }

    @Deprecated
    public static byte[] hmacSha256(byte[] bArr, byte[] bArr2) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_256, bArr).hmac(bArr2);
    }

    @Deprecated
    public static byte[] hmacSha256(byte[] bArr, InputStream inputStream) throws IOException {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_256, bArr).hmac(inputStream);
    }

    @Deprecated
    public static byte[] hmacSha256(String str, String str2) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_256, str).hmac(str2);
    }

    @Deprecated
    public static String hmacSha256Hex(byte[] bArr, byte[] bArr2) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_256, bArr).hmacHex(bArr2);
    }

    @Deprecated
    public static String hmacSha256Hex(byte[] bArr, InputStream inputStream) throws IOException {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_256, bArr).hmacHex(inputStream);
    }

    @Deprecated
    public static String hmacSha256Hex(String str, String str2) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_256, str).hmacHex(str2);
    }

    @Deprecated
    public static byte[] hmacSha384(byte[] bArr, byte[] bArr2) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_384, bArr).hmac(bArr2);
    }

    @Deprecated
    public static byte[] hmacSha384(byte[] bArr, InputStream inputStream) throws IOException {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_384, bArr).hmac(inputStream);
    }

    @Deprecated
    public static byte[] hmacSha384(String str, String str2) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_384, str).hmac(str2);
    }

    @Deprecated
    public static String hmacSha384Hex(byte[] bArr, byte[] bArr2) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_384, bArr).hmacHex(bArr2);
    }

    @Deprecated
    public static String hmacSha384Hex(byte[] bArr, InputStream inputStream) throws IOException {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_384, bArr).hmacHex(inputStream);
    }

    @Deprecated
    public static String hmacSha384Hex(String str, String str2) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_384, str).hmacHex(str2);
    }

    @Deprecated
    public static byte[] hmacSha512(byte[] bArr, byte[] bArr2) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_512, bArr).hmac(bArr2);
    }

    @Deprecated
    public static byte[] hmacSha512(byte[] bArr, InputStream inputStream) throws IOException {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_512, bArr).hmac(inputStream);
    }

    @Deprecated
    public static byte[] hmacSha512(String str, String str2) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_512, str).hmac(str2);
    }

    @Deprecated
    public static String hmacSha512Hex(byte[] bArr, byte[] bArr2) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_512, bArr).hmacHex(bArr2);
    }

    @Deprecated
    public static String hmacSha512Hex(byte[] bArr, InputStream inputStream) throws IOException {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_512, bArr).hmacHex(inputStream);
    }

    @Deprecated
    public static String hmacSha512Hex(String str, String str2) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_512, str).hmacHex(str2);
    }

    public static Mac updateHmac(Mac mac2, byte[] bArr) {
        mac2.reset();
        mac2.update(bArr);
        return mac2;
    }

    public static Mac updateHmac(Mac mac2, InputStream inputStream) throws IOException {
        mac2.reset();
        byte[] bArr = new byte[1024];
        int read = inputStream.read(bArr, 0, 1024);
        while (read > -1) {
            mac2.update(bArr, 0, read);
            read = inputStream.read(bArr, 0, 1024);
        }
        return mac2;
    }

    public static Mac updateHmac(Mac mac2, String str) {
        mac2.reset();
        mac2.update(StringUtils.getBytesUtf8(str));
        return mac2;
    }

    @Deprecated
    public HmacUtils() {
        this((Mac) null);
    }

    private HmacUtils(Mac mac2) {
        this.mac = mac2;
    }

    public HmacUtils(String str, byte[] bArr) {
        this(getInitializedMac(str, bArr));
    }

    public HmacUtils(String str, String str2) {
        this(str, StringUtils.getBytesUtf8(str2));
    }

    public HmacUtils(HmacAlgorithms hmacAlgorithms, String str) {
        this(hmacAlgorithms.getName(), StringUtils.getBytesUtf8(str));
    }

    public HmacUtils(HmacAlgorithms hmacAlgorithms, byte[] bArr) {
        this(hmacAlgorithms.getName(), bArr);
    }

    public byte[] hmac(byte[] bArr) {
        return this.mac.doFinal(bArr);
    }

    public String hmacHex(byte[] bArr) {
        return Hex.encodeHexString(hmac(bArr));
    }

    public byte[] hmac(String str) {
        return this.mac.doFinal(StringUtils.getBytesUtf8(str));
    }

    public String hmacHex(String str) {
        return Hex.encodeHexString(hmac(str));
    }

    public byte[] hmac(ByteBuffer byteBuffer) {
        this.mac.update(byteBuffer);
        return this.mac.doFinal();
    }

    public String hmacHex(ByteBuffer byteBuffer) {
        return Hex.encodeHexString(hmac(byteBuffer));
    }

    public byte[] hmac(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr, 0, 1024);
            if (read <= -1) {
                return this.mac.doFinal();
            }
            this.mac.update(bArr, 0, read);
        }
    }

    public String hmacHex(InputStream inputStream) throws IOException {
        return Hex.encodeHexString(hmac(inputStream));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0014, code lost:
        r1 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] hmac(java.io.File r3) throws java.io.IOException {
        /*
            r2 = this;
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream
            java.io.FileInputStream r1 = new java.io.FileInputStream
            r1.<init>(r3)
            r0.<init>(r1)
            byte[] r3 = r2.hmac((java.io.InputStream) r0)     // Catch:{ all -> 0x0012 }
            r0.close()
            return r3
        L_0x0012:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0014 }
        L_0x0014:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x0019 }
            goto L_0x001d
        L_0x0019:
            r0 = move-exception
            r3.addSuppressed(r0)
        L_0x001d:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.digest.HmacUtils.hmac(java.io.File):byte[]");
    }

    public String hmacHex(File file) throws IOException {
        return Hex.encodeHexString(hmac(file));
    }
}
