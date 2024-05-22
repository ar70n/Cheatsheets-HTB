
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
    
    
    public static void main(String[] args){
        System.out.println(new c("asdfff").getHex());
    }
    
}


public class a {
    String a = "2d644cd693bb";

    public a(String str) {
        this.a += str;
    }

    public String a(String str) {
        String str2 = "";
        for (int length = str.toCharArray().length - 1; length >= 0; length--) {
            str2 = str2 + String.valueOf(str.toCharArray()[length]);
        }
        return str2;
    }

    public static String OOOO000OOO000OO00000(String str) {
        char[] OOOO000OOO0000O00000 = OOOO000OOO0000O00000(str.toCharArray(), str.toCharArray().length);
        String str2 = "";
        for (int i = 0; i < OOOO000OOO0000O00000.length; i++) {
            str2 = str2 + String.valueOf(OOOO000OOO0000O00000[i]);
        }
        return str2;
    }

    public static char[] OOOO000OOO0000O00000(char[] cArr, int i) {
        char[] cArr2 = new char[i];
        int i2 = i;
        for (int i3 = 0; i3 < i; i3++) {
            i2--;
            cArr2[i2] = cArr[i3];
        }
        return cArr2;
    }
}



public class b {
    String b = "aaaa";

    public void removeItem(String str, String str2) {
        if (str != null) {
            for (int i = 0; i < 2245; i++) {
            }
        }
    }

    public b(String str) {
        this.b += str;
    }
}

