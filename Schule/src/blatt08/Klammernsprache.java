package blatt08;

public class Klammernsprache {

    /**
     * Prüft, ob ein Wort Teil der legitimen Klammernsprache ist.
     * @param wort zu prüfendes Wort
     * @return true, falls Klammernwort, sonst false
     */
    public static boolean istKlammerwort(String wort) {
        int w = 0;
        char[] charr =  wort.toCharArray();
        for (int i = 0; i < charr.length; i++) {
            if (charr[i] == '(') {
                w++;
            } else if (charr[i] == ')') {
                w--;
            } else {
                return false;
            }
            if (w < 0) {
                return false;
            }
        }
        if (w == 0) {
            return true;
        }
        return false;
    }
}
