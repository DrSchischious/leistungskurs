package blatt09;

public class Primzahlen {

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

    public static boolean istPrim(int n) {
        if (Teiler.anzahlTeiler(n) == 2) {
            return true;
        } else {
            return false;
        }
    }

    public static void printPrim(int n) {
        for (int i = 2; i <= n; i++) {
            if (istPrim(i)) {
                System.out.println(i);
            }
        }
    }

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
