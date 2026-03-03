package blatt08;

public class SuchenUndErsetzen {
    /**
     * Sucht in einem String, ob ein bestimmtes Zeichen vorkommt und ersetzt dieses durchgehend durch ein anderes Zeichen.
     * @param wort Zu durchsuchender String
     * @param s Suchzeichen
     * @param z Ersatzzeichen
     * @return Verändertes Wort
     */
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
