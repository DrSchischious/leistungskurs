package blatt07;

public class Rechner {

    /**
     * Addiert zwei Zahlen und gibt die Summe aus
     * @param a Zahl 1
     * @param b Zahl 2
     * @return Summe a + b
     */
    public static int add(int a, int b) {
        return a + b;
    }

    /**
     * Subtrahiert zwei Zahlen und gibt die Differenz aus
     * @param a Zahl 1
     * @param b Zahl 2
     * @return Differenz a - b
     */
    public static int sub(int a, int b) {
        return a - b;
    }

    /**
     * Multipliziert zwei Zahlen und gibt das Produkt aus
     * @param a Zahl 1
     * @param b Zahl 2
     * @return Summe a * b
     */
    public static int mult(int a, int b) {
        return a * b;
    }

    /**
     * Dividiert zwei Zahlen und gibt den Quotienten aus
     * @param a Zahl 1
     * @param b Zahl 2
     * @return Quotient a / b
     */
    public static int div(int a, int b) {
        if (b == 0) {
            System.out.println("Division durch 0 nicht möglich.");
            System.exit(0);
        }
        return a / b;
    }

    /**
     * Berechnet die vier mathematischen Grundoperationen mit zwei Eingabewerten und gibt diese aus.
     * @param a Zahl 1
     * @param b Zahl 2
     */
    public static void operation(int a, int b) {
        System.out.println("Summe " + add(a, b));
        System.out.println("Differenz " + sub(a, b));
        System.out.println("Produkt " + mult(a, b));
        System.out.println("Quotient " + div(a, b));
    }





}
