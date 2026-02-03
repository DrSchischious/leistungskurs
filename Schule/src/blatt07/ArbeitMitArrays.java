package blatt07;

public class ArbeitMitArrays {


    /**
     * Gibt den Inhalt eines Arrays aus.
     * @param arr int-Array
     */
    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
            if (i == arr.length-1) {
                System.out.print("]");
            } else {
                System.out.print("| ");
            }
        }
        System.out.println();
    }

    /**
     * Gibt den Inhalt eines Arrays aus.
     * @param arr byte-Array
     */
    public static void printArray(byte[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
            if (i == arr.length-1) {
                System.out.print("]");
            } else {
                System.out.print("| ");
            }
        }
        System.out.println();
    }

    /**
     * Gibt den Inhalt eines Arrays aus.
     * @param arr boolean-Array
     */
    public static void printArray(boolean[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
            if (i == arr.length-1) {
                System.out.print("]");
            } else {
                System.out.print("| ");
            }
        }
        System.out.println();
    }

    /**
     * Gibt den Inhalt eines Arrays aus.
     * @param arr double-Array
     */
    public static void printArray(double[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
            if (i == arr.length-1) {
                System.out.print("]");
            } else {
                System.out.print("| ");
            }
        }
        System.out.println();
    }

    /**
     * Gibt den Inhalt eines Arrays aus.
     * @param arr String-Array
     */
    public static void printArray(String[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
            if (i == arr.length-1) {
                System.out.print("]");
            } else {
                System.out.print("| ");
            }
        }
        System.out.println();
    }

    /**
     * Gibt den Inhalt eines Arrays aus.
     * @param arr char-Array
     */
    public static void printArray(char[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
            if (i == arr.length-1) {
                System.out.print("]");
            } else {
                System.out.print("| ");
            }
        }
        System.out.println();
    }

    /**
     * Prüft, ob ein Array sortiert ist
     * @param arr double-Array
     * @param asc true, falls aufsteigend, sonst absteigend
     * @return true, falls Array sortiert
     */
    public static boolean istSortiert(int[] arr, boolean asc) {
        for (int i = 0; i < arr.length-1; i++) {
            if (asc == true && arr[i] > arr[i+1]) {
                return false;
            }
            if (asc == false && arr[i] < arr[i+1]) {
                return false;
            }
        }
        return true;
    }


    /**
     * Addiert zwei Ganzzahl-Arrays miteinander
     * @param arr1 Array 1
     * @param arr2 Array 2
     * @return Stellenweise summierter Array
     */
    public static int[] addieren(int[] arr1, int[] arr2) {
        int[] sum;
        if (arr1.length > arr2.length) {

            sum = new int[arr1.length];
            for (int i = 0; i < arr2.length; i++) {
                sum[i] = arr1[i] + arr2[i];
            }
            for (int i = arr2.length; i < arr1.length; i++) {
                sum[i] = arr1[i];
            }
        } else {
            sum = new int[arr2.length];
            for (int i = 0; i < arr1.length; i++) {
                sum[i] = arr1[i] + arr2[i];
            }
            for (int i = arr1.length; i < arr2.length; i++) {
                sum[i] = arr2[i];
            }
        }
        return sum;
    }

    /**
     * Bildet einen Array zufälliger Werte zwischen -50 und 50 der Länge l
     * @param l Länge des Zufalls-Array
     * @return Array der Länge l mit Zufallswerten
     */
    public static int[] randomArray(int l) {
        int[] arr = new int[l];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*100 - 50);
        }
        return arr;
    }

    /**
     * Verschiebt alle Werte des Arrays nach Links. Das erste Element wird an das Ende gesetzt.
     * @param arr zu verschiebender Array
     */
    public static void shiftLeft(int[] arr) {
        int tmp = arr[0];
        for (int i = 0; i < arr.length-1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length-1] = tmp;
    }

    /**
     * Verschiebt alle Werte des Arrays nach Rechts. Das letzte Element wird an den Anfang gesetzt.
     * @param arr zu verschiebender Array
     */
    public static void shiftRight(int[] arr) {
        int tmp = arr[arr.length-1];
        for (int i = arr.length-1; i > 0; i--) {
            arr[i] = arr[i-1];
        }
        arr[0] = tmp;
    }

    /**
     * Verschiebt alle Werte des Arrays nach Links. Das letzte Element wird 0 gesetzt.
     * @param arr zu verschiebender Array
     */
    public static void shiftLeftAbsolute(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length-1] = 0;
    }

    /**
     * Verschiebt alle Werte des Arrays nach Rechts. Das erste Element wird 0 gesetzt.
     * @param arr zu verschiebender Array
     */
    public static void shiftRightAbsolute(int[] arr) {
        for (int i = arr.length-1; i > 0; i--) {
            arr[i] = arr[i-1];
        }
        arr[0] = 0;
    }

    public static void main(String[] args) {
        int[] arr1 = randomArray(5);
        int[] arr2 = randomArray(10);
        printArray(arr1);
        printArray(arr2);
        int[] arr3 = addieren(arr1,arr2);
        printArray(arr3);

        shiftLeftAbsolute(arr1);
        printArray(arr1);
        shiftRightAbsolute(arr1);
        printArray(arr1);
        shiftRightAbsolute(arr1);
        printArray(arr1);
        shiftLeftAbsolute(arr1);
        printArray(arr1);

    }
}
