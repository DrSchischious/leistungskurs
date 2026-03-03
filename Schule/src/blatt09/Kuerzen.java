package blatt09;

public class Kuerzen {

    /**
     * Prüft, ob ein Bruch kürzbar ist.
     * @param z Zähler des Bruchs
     * @param n Nenner des Bruchs
     * @return true, falls kürzbar, sonst false
     */
    public static boolean istKuerzbar(int z, int n) {
        int[] tz = Teiler.teiler(z);
        int[] tn = Teiler.teiler(n);

        for (int i = 0; i < tz.length; i++) {
            for (int j = 0; j < tn.length; j++) {
                if (tz[i] == tn[j] && tz[i] != 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gibt zu einem gegebenem Bruch die gekürzte Form aus.
     * @param z Zähler des Bruchs
     * @param n Nenner des Bruchs
     */
    public static void kuerzen(int z, int n) {

        System.out.print(z + "/" + n + " = ");
        while (istKuerzbar(z, n)) {
            int[] tz = Teiler.teiler(z);
            int[] tn = Teiler.teiler(n);

            for (int i = 0; i < tz.length; i++) {
                for (int j = 0; j < tn.length; j++) {
                    if (tz[i] == tn[j] && tz[i] != 1) {
                        z = z / tz[i];
                        n = n / tn[j];
                    }
                }
            }
        }

        System.out.println(z + "/" + n);
    }

    public static void main(String[] args) {
        System.out.println(istKuerzbar(4, 7));
        kuerzen(128,2060);
    }
}
