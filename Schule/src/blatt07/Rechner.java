package blatt07;

public class Rechner {

    /**
     * Addiert drei Zahlen und gibt die Summe aus
     * @param a Zahl 1
     * @param b Zahl 2
     * @param c Zahl 3
     */
    public static void addieren(int a, int b, int c) {
        System.out.println(a + b + c);
    }

    /**
     * Subtrahiert drei Zahlen und gibt das Ergebnis zurück
     * @param a Zahl 1
     * @param b Zahl 2
     * @param c Zahl 3
     * @return Differenz
     */
    public static int subtrahieren(int a, int b, int c) {
        return a - b - c;
    }


    public static void main(String[] args) {
        addieren(3,5,12);
        System.out.println(subtrahieren(-4,19,8));
    }
}
