package org.apache.commons.codec.digest;

import java.security.SecureRandom;
import java.util.Random;

class B64 {
    static final String B64T = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    B64() {
    }

    static void b64from24bit(byte b, byte b2, byte b3, int i, StringBuilder sb) {
        int i2 = ((b << 16) & 16777215) | ((b2 << 8) & 65535) | (b3 & 255);
        while (true) {
            int i3 = i - 1;
            if (i > 0) {
                sb.append(B64T.charAt(i2 & 63));
                i2 >>= 6;
                i = i3;
            } else {
                return;
            }
        }
    }

    static String getRandomSalt(int i) {
        return getRandomSalt(i, new SecureRandom());
    }

    static String getRandomSalt(int i, Random random) {
        StringBuilder sb = new StringBuilder(i);
        for (int i2 = 1; i2 <= i; i2++) {
            sb.append(B64T.charAt(random.nextInt(64)));
        }
        return sb.toString();
    }
}
