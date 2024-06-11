package ctf.clickergame;

import java.math.BigInteger;
import java.security.MessageDigest;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

public class c {
    a a = new a("2cf24dba5fb0a30e26e83b2ac5b");
    b b = new b(this.a.a);
    String d = "";
    String input;
    a o = new a("OOO000OO00OO000");
    String var1 = "e29e1b161e5c1fa7425e7304";

    public c(String str) {
        this.input = str;
    }

    public String OOO00000000OO0O0O0O000O() {
        char[] charArray = this.b.b.toCharArray();
        String str = "";
        for (int i = 0; i < charArray.length; i++) {
            str = str + (Character.getNumericValue(charArray[i]) ^ Integer.valueOf("44").intValue());
        }
        return str;
    }

    public String getHex() {
        try {
            String format = String.format("%032X", new Object[]{new BigInteger(1, MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256).digest((a.OOOO000OOO000OO00000(this.b.b) + this.input).getBytes("UTF-8")))});
            this.var1 = format;
            String substring = format.substring(0, Math.min(format.length(), 32));
            this.var1 = substring;
            return substring;
        } catch (Exception unused) {
            return null;
        }
    }
}
