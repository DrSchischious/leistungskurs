package blatt08;

public class SuchenUndErsetzen {
    public static String ersetzen(String wort, char s, char z) {
        char[] charr = wort.toCharArray();
        for (int i = 0; i < charr.length; i++) {
            if (charr[i] == s) {
                charr[i] = z;
            }
        }
        return new String(charr);
    }
}
