package org.apache.commons.codec.digest;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.Charsets;

public class Sha2Crypt {
    private static final int ROUNDS_DEFAULT = 5000;
    private static final int ROUNDS_MAX = 999999999;
    private static final int ROUNDS_MIN = 1000;
    private static final String ROUNDS_PREFIX = "rounds=";
    private static final Pattern SALT_PATTERN = Pattern.compile("^\\$([56])\\$(rounds=(\\d+)\\$)?([\\.\\/a-zA-Z0-9]{1,16}).*");
    private static final int SHA256_BLOCKSIZE = 32;
    static final String SHA256_PREFIX = "$5$";
    private static final int SHA512_BLOCKSIZE = 64;
    static final String SHA512_PREFIX = "$6$";

    public static String sha256Crypt(byte[] bArr) {
        return sha256Crypt(bArr, (String) null);
    }

    public static String sha256Crypt(byte[] bArr, String str) {
        if (str == null) {
            str = SHA256_PREFIX + B64.getRandomSalt(8);
        }
        return sha2Crypt(bArr, str, SHA256_PREFIX, 32, MessageDigestAlgorithms.SHA_256);
    }

    public static String sha256Crypt(byte[] bArr, String str, Random random) {
        if (str == null) {
            str = SHA256_PREFIX + B64.getRandomSalt(8, random);
        }
        return sha2Crypt(bArr, str, SHA256_PREFIX, 32, MessageDigestAlgorithms.SHA_256);
    }

    private static String sha2Crypt(byte[] bArr, String str, String str2, int i, String str3) {
        boolean z;
        int i2;
        byte b;
        byte[] bArr2;
        int i3;
        byte[] bArr3 = bArr;
        String str4 = str;
        int i4 = i;
        int length = bArr3.length;
        if (str4 != null) {
            Matcher matcher = SALT_PATTERN.matcher(str4);
            if (matcher.find()) {
                if (matcher.group(3) != null) {
                    i2 = Math.max(1000, Math.min(ROUNDS_MAX, Integer.parseInt(matcher.group(3))));
                    z = true;
                } else {
                    i2 = ROUNDS_DEFAULT;
                    z = false;
                }
                String group = matcher.group(4);
                byte[] bytes = group.getBytes(Charsets.UTF_8);
                int length2 = bytes.length;
                MessageDigest digest = DigestUtils.getDigest(str3);
                digest.update(bArr3);
                digest.update(bytes);
                MessageDigest digest2 = DigestUtils.getDigest(str3);
                digest2.update(bArr3);
                digest2.update(bytes);
                digest2.update(bArr3);
                byte[] digest3 = digest2.digest();
                int length3 = bArr3.length;
                while (length3 > i4) {
                    digest.update(digest3, 0, i4);
                    length3 -= i4;
                }
                digest.update(digest3, 0, length3);
                for (int length4 = bArr3.length; length4 > 0; length4 >>= 1) {
                    if ((length4 & 1) != 0) {
                        digest.update(digest3, 0, i4);
                    } else {
                        digest.update(bArr3);
                    }
                }
                byte[] digest4 = digest.digest();
                MessageDigest digest5 = DigestUtils.getDigest(str3);
                for (int i5 = 1; i5 <= length; i5++) {
                    digest5.update(bArr3);
                }
                byte[] digest6 = digest5.digest();
                byte[] bArr4 = new byte[length];
                int i6 = 0;
                while (i6 < length - i4) {
                    System.arraycopy(digest6, 0, bArr4, i6, i4);
                    i6 += i4;
                }
                System.arraycopy(digest6, 0, bArr4, i6, length - i6);
                MessageDigest digest7 = DigestUtils.getDigest(str3);
                for (int i7 = 1; i7 <= (digest4[0] & 255) + 16; i7++) {
                    digest7.update(bytes);
                }
                byte[] digest8 = digest7.digest();
                byte[] bArr5 = new byte[length2];
                int i8 = 0;
                MessageDigest messageDigest = digest;
                while (i8 < length2 - i4) {
                    System.arraycopy(digest8, 0, bArr5, i8, i4);
                    i8 += i4;
                }
                System.arraycopy(digest8, 0, bArr5, i8, length2 - i8);
                int i9 = 0;
                MessageDigest messageDigest2 = messageDigest;
                while (i9 <= i2 - 1) {
                    messageDigest2 = DigestUtils.getDigest(str3);
                    int i10 = i9 & 1;
                    if (i10 != 0) {
                        bArr2 = bytes;
                        i3 = 0;
                        messageDigest2.update(bArr4, 0, length);
                    } else {
                        bArr2 = bytes;
                        i3 = 0;
                        messageDigest2.update(digest4, 0, i4);
                    }
                    if (i9 % 3 != 0) {
                        messageDigest2.update(bArr5, i3, length2);
                    }
                    if (i9 % 7 != 0) {
                        messageDigest2.update(bArr4, i3, length);
                    }
                    if (i10 != 0) {
                        messageDigest2.update(digest4, i3, i4);
                    } else {
                        messageDigest2.update(bArr4, i3, length);
                    }
                    digest4 = messageDigest2.digest();
                    i9++;
                    bytes = bArr2;
                }
                byte[] bArr6 = bytes;
                StringBuilder sb = new StringBuilder(str2);
                if (z) {
                    sb.append(ROUNDS_PREFIX);
                    sb.append(i2);
                    sb.append("$");
                }
                sb.append(group);
                sb.append("$");
                if (i4 == 32) {
                    B64.b64from24bit(digest4[0], digest4[10], digest4[20], 4, sb);
                    B64.b64from24bit(digest4[21], digest4[1], digest4[11], 4, sb);
                    B64.b64from24bit(digest4[12], digest4[22], digest4[2], 4, sb);
                    B64.b64from24bit(digest4[3], digest4[13], digest4[23], 4, sb);
                    B64.b64from24bit(digest4[24], digest4[4], digest4[14], 4, sb);
                    B64.b64from24bit(digest4[15], digest4[25], digest4[5], 4, sb);
                    B64.b64from24bit(digest4[6], digest4[16], digest4[26], 4, sb);
                    B64.b64from24bit(digest4[27], digest4[7], digest4[17], 4, sb);
                    B64.b64from24bit(digest4[18], digest4[28], digest4[8], 4, sb);
                    B64.b64from24bit(digest4[9], digest4[19], digest4[29], 4, sb);
                    B64.b64from24bit((byte) 0, digest4[31], digest4[30], 3, sb);
                    b = 0;
                } else {
                    B64.b64from24bit(digest4[0], digest4[21], digest4[42], 4, sb);
                    B64.b64from24bit(digest4[22], digest4[43], digest4[1], 4, sb);
                    B64.b64from24bit(digest4[44], digest4[2], digest4[23], 4, sb);
                    B64.b64from24bit(digest4[3], digest4[24], digest4[45], 4, sb);
                    B64.b64from24bit(digest4[25], digest4[46], digest4[4], 4, sb);
                    B64.b64from24bit(digest4[47], digest4[5], digest4[26], 4, sb);
                    B64.b64from24bit(digest4[6], digest4[27], digest4[48], 4, sb);
                    B64.b64from24bit(digest4[28], digest4[49], digest4[7], 4, sb);
                    B64.b64from24bit(digest4[50], digest4[8], digest4[29], 4, sb);
                    B64.b64from24bit(digest4[9], digest4[30], digest4[51], 4, sb);
                    B64.b64from24bit(digest4[31], digest4[52], digest4[10], 4, sb);
                    B64.b64from24bit(digest4[53], digest4[11], digest4[32], 4, sb);
                    B64.b64from24bit(digest4[12], digest4[33], digest4[54], 4, sb);
                    B64.b64from24bit(digest4[34], digest4[55], digest4[13], 4, sb);
                    B64.b64from24bit(digest4[56], digest4[14], digest4[35], 4, sb);
                    B64.b64from24bit(digest4[15], digest4[36], digest4[57], 4, sb);
                    B64.b64from24bit(digest4[37], digest4[58], digest4[16], 4, sb);
                    B64.b64from24bit(digest4[59], digest4[17], digest4[38], 4, sb);
                    B64.b64from24bit(digest4[18], digest4[39], digest4[60], 4, sb);
                    B64.b64from24bit(digest4[40], digest4[61], digest4[19], 4, sb);
                    B64.b64from24bit(digest4[62], digest4[20], digest4[41], 4, sb);
                    b = 0;
                    B64.b64from24bit((byte) 0, (byte) 0, digest4[63], 2, sb);
                }
                Arrays.fill(digest8, b);
                Arrays.fill(bArr4, b);
                Arrays.fill(bArr5, b);
                messageDigest2.reset();
                digest7.reset();
                Arrays.fill(bArr3, b);
                Arrays.fill(bArr6, b);
                return sb.toString();
            }
            throw new IllegalArgumentException("Invalid salt value: " + str4);
        }
        throw new IllegalArgumentException("Salt must not be null");
    }

    public static String sha512Crypt(byte[] bArr) {
        return sha512Crypt(bArr, (String) null);
    }

    public static String sha512Crypt(byte[] bArr, String str) {
        if (str == null) {
            str = SHA512_PREFIX + B64.getRandomSalt(8);
        }
        return sha2Crypt(bArr, str, SHA512_PREFIX, 64, MessageDigestAlgorithms.SHA_512);
    }

    public static String sha512Crypt(byte[] bArr, String str, Random random) {
        if (str == null) {
            str = SHA512_PREFIX + B64.getRandomSalt(8, random);
        }
        return sha2Crypt(bArr, str, SHA512_PREFIX, 64, MessageDigestAlgorithms.SHA_512);
    }
}
