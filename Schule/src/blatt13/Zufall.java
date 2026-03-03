package blatt13;

public class Zufall {

    /**
     * Bildet zufällige Gleitkommazahl zwischen 0 und b.
     * @param b Grenze für Zahlenbildung
     * @return zufällige Gleitkommazahl zwischen 0 und b
     */
    public static double zufall(int b) {
        return Math.random()*b;
    }

    /**
     * Bildet zufällige Ganzzahl zwischen 0 und b.
     * @param b Grenze für Zahlenbildung
     * @return zufällige Ganzzahl zwischen 0 und b
     */
    public static int zufallGanz(int b) {
        return (int)zufall(b+1);
    }

    /**
     * Bildet zufällige Gleitkommazahl zwischen a und b.
     * @param a Untere Grenze für Zahlenbildung
     * @param b Obere Grenze für Zahlenbildung
     * @return zufällige Gleitkommazahl zwischen a und b
     */
    public static double zufall(int a, int b) {
        return (Math.random()*(b-a))+a;
    }

    /**
     * Bildet zufällige Ganzzahl zwischen a und b.
     * @param a Untere Grenze für Zahlenbildung
     * @param b Obere Grenze für Zahlenbildung
     * @return zufällige Ganzzahl zwischen b und b
     */
    public static int zufallGanz(int a, int b) {
        return (int)((Math.random()*(b-a+1))+a);
    }

    /**
     * Bildet einen int-Array mit l Zufallszahlen zwischen a und b
     * @param l Länge der Liste an Zufallszahlen
     * @param a Untere Grenze für Zahlenbildung
     * @param b Obere Grenze für Zahlenbildung
     * @return int-Array mit l Zufallszahlen zwischen a und b
     */
    public static int[] zufallArray(int l, int a, int b) {
        int[] arr = new int[l];
        for (int i = 0; i < l; i++) {
            arr[i] = zufallGanz(a,b);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = zufallArray(20, 4, 9);
        //blatt07.ArbeitMitArrays.printArray(arr);
        System.out.println(zufallGanz(5,10));
        System.out.println(zufallGanz(5,10));
        System.out.println(zufallGanz(5,10));
        System.out.println(zufallGanz(5,10));
        System.out.println(zufallGanz(5,10));
        System.out.println(zufallGanz(5,10));
        System.out.println(zufallGanz(5,10));
        System.out.println(zufallGanz(5,10));
    }
}
