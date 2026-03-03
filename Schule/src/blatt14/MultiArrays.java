package blatt14;

public class MultiArrays {

    /**
     * Gibt den Inhalt eines zweidimensionalen int-Arrays aus
     * @param arr zweidimensionaler int-Array
     */
    public static void print2DArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            blatt07.ArbeitMitArrays.printArray(arr[i]);
        }
    }

    /**
     * Gibt den Inhalt eines zweidimensionalen double-Arrays aus
     * @param arr zweidimensionaler double-Array
     */
    public static void print2DArray(double[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            blatt07.ArbeitMitArrays.printArray(arr[i]);
        }
    }

    /**
     * Gibt den Inhalt eines zweidimensionalen char-Arrays aus
     * @param arr zweidimensionaler char-Array
     */
    public static void print2DArray(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            blatt07.ArbeitMitArrays.printArray(arr[i]);
        }
    }

    /**
     * Erschafft einen zweidimensionalen int-Array einer bestimmten Länge mit zufälligen Werten zwischen p und q
     * @param a Anzahl der Elemente erster Ordnung im Array
     * @param b Anzahl der Elemente zweiter Ordnung im Array
     * @param p Untere Grenze zur Zahlenbildung
     * @param q Obere Grenze zur Zahlenbildung
     * @return Zweidimensionaler int-Array mit Zufallswerten
     */
    public static int[][] createRandom2DIntArray(int a, int b, int p, int q) {
        int[][] arr = new int[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                arr[i][j] = blatt13.Zufall.zufallGanz(p,q);
            }
        }
        return arr;
    }


    /**
     * Erschafft einen zweidimensionalen double-Array einer bestimmten Länge mit zufälligen Werten zwischen 0 und 1
     * @param a Anzahl der Elemente erster Ordnung im Array
     * @param b Anzahl der Elemente zweiter Ordnung im Array
     * @return Zweidimensionaler double-Array mit Zufallswerten
     */
    public static double[][] createRandom2DDoubleArray(int a, int b) {
        double[][] arr = new double[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                arr[i][j] = blatt13.Zufall.zufall(1);
            }
        }
        return arr;
    }

    /**
     * Erschafft einen zweidimensionalen int-Array einer bestimmten Länge mit aufsteigenden Werten
     * @param a Anzahl der Elemente erster Ordnung im Array
     * @param b Anzahl der Elemente zweiter Ordnung im Array
     * @return Zweidimensionaler int-Array mit aufsteigenden Werten
     */
    public static int[][] createCountingArray(int a, int b) {
        int c = 0;
        int[][] arr = new int[a][b];

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                arr[i][j] = c;
                c++;
            }
        }
        return arr;
    }

    /**
     * Erschafft einen leeren (' ') zweidimensionalen char-Array einer bestimmten Länge
     * @param a Anzahl der Elemente erster Ordnung im Array
     * @param b Anzahl der Elemente zweiter Ordnung im Array
     * @return Zweidimensionaler leerer char-Array
     */
    public static char[][] createEmpty2DCharArray(int a, int b) {
        char[][] arr = new char[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                arr[i][j] = ' ';
            }
        }
        return arr;
    }

    /**
     * Prüft, ob zwei zweidimensionale char-Arrays identisch sind
     * @param arr1 erster Array
     * @param arr2 zweiter Array
     * @return true, falls identisch, sonst false
     */
    public static boolean istIdentisch(char[][] arr1, char[][] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i].length != arr2[i].length) {
                return false;
            }
            for (int j = 0; j < arr1[i].length; j++) {
                if (arr1[i][j] != arr2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Legt eine harte Kopie eines zweidimensionalen char-Arrays an und gibt diesen zurück
     * @param arr zu kopierender Array
     * @return Kopie des übergebenen Arrays
     */
    public static char[][] copy2DCharArray(char[][] arr) {
        char[][] arr2 = new char[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            arr2[i] = new char[arr[i].length];
            for (int j = 0; j < arr[i].length; j++) {
                arr2[i][j] = arr[i][j];
            }
        }
        return arr2;
    }

    /**
     * Rotiert eine Reihe des Arrays um eine bestimmte Anzahl an Schritten linksherum.
     * @param arr zweidimensionaler Array für die Rotation
     * @param row zu rotierende Reihe
     * @param v Anzahl der Rotationsschritte
     */
    public static void shiftRows(int[][] arr, int row, int v) {
        if (row >= 0 && row < arr.length) {
            for (int i = 0; i < v; i++) {
                blatt07.ArbeitMitArrays.shiftLeft(arr[row]);
            }

        }
    }



    public static void main(String[] args) {
        print2DArray(createRandom2DDoubleArray(10,10));
        System.out.println("Ich mag Züge\n\nTUUUUUUT TUUUUUUUUT");
    }
}
