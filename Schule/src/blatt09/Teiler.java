package blatt09;

public class Teiler {

    /**
     * Prüft, ob eine Zahl t ein Teiler einer anderen Zahl n ist.
     * @param n Hauptzahl
     * @param t Teiler
     * @return true, falls t Teiler von n, sonst false
     */
    public static boolean istTeiler(int n, int t) {
        if (n % t == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Ausgabe aller Teiler einer gegebenen Zahl.
     * @param n Zahl, deren Teiler ausgegeben werden sollen
     */
    public static void alleTeiler(int n) {
        for (int i = 1; i <= n; i++) {
            if (istTeiler(n,i)) {
                System.out.println(i);
            }
        }
    }

    /**
     * Gibt die Anzahl der Teiler einer Zahl zurück.
     * @param n Zu prüfende Zahl
     * @return Anzahl der Teiler von n
     */
    public static int anzahlTeiler(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (istTeiler(n,i)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Gibt einen Array aller Teiler von n zurück.
     * @param n Zu prüfende Zahl
     * @return int-Array aller Teiler von n
     */
    public static int[] teiler(int n) {
        int[] teiler = new int[anzahlTeiler(n)];
        int i = 0;
        for (int j = 1; j <= n; j++) {
            if (istTeiler(n,j)) {
                teiler[i] = j;
                i++;
            }
        }
        return teiler;
    }

    public static void main(String[] args) {
        blatt07.ArbeitMitArrays.printArray(teiler(10));
    }

}
