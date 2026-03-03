package blatt09;

public class Primzahlen {

    /**
     * Gibt die Primfaktorzerlegung zu einer gegebenen natürlichen Zahl zurück.
     * @param n zu zerlegende Zahl
     */
    public static void primfaktorzerlegung(int n) {
        String ausgabe = n + " = ";
        boolean first = true;
        while (!istPrim(n)) {
            int[] t = Teiler.teiler(n);
            for (int i = t.length-1; i >= 0; i--) {
                if (istPrim(t[i])) {
                    if (first) {
                        ausgabe = ausgabe + t[i] + " ";
                        first = false;
                    } else {
                        ausgabe = ausgabe + "* " + t[i] + " ";
                    }

                    n = n / t[i];
                    break;
                }
            }
        }
        ausgabe = ausgabe + "* " + n;
        System.out.println(ausgabe);
    }

    /**
     * Gibt zurück, ob eine natürliche Zahl prim ist.
     * @param n zu überprüfende Zahl
     * @return true, falls prim, sonst false
     */
    public static boolean istPrim(int n) {
        if (Teiler.anzahlTeiler(n) == 2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gibt alle Primzahlen zwischen 2 und n aus.
     * @param n Grenze der Primzahlen
     */
    public static void printPrim(int n) {
        for (int i = 2; i <= n; i++) {
            if (istPrim(i)) {
                System.out.println(i);
            }
        }
    }

    /**
     * Generiert die ersten n Primzahlen und gibt diese als Array zurück.
     * @param n Anzahl der zu generierenden Primzahlen.
     * @return int-Array der ersten n Primzahlen
     */
    public static int[] generierePrimzahlen(int n) {
        int[] prims = new int[n];
        int i = 0;
        int j = 1;
        while(true) {
            if (istPrim(j)) {
                prims[i] = j;

                i++;
            }
            if (i >= n) {
                break;
            }
            j++;
        }

        return prims;

    }

    public static void main(String[] args) {
        printPrim(40);
        blatt07.ArbeitMitArrays.printArray(generierePrimzahlen(10));
        System.out.println();
        primfaktorzerlegung(124);
    }
}
