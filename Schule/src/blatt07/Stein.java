package blatt07;

public class Stein {
    /**
     * Bildet den ggT zweier Zahlen nach dem Steinschen Algorithmus.
     * @param a Zahl 1
     * @param b Zahl 2
     * @return ggT der beiden Zahlen
     */
    public static int steinGGT(int a, int b) {
        int k = 0;
        while (a != 0 && b != 0) {
            if (a%2 == 0 && b%2 == 0) {
                a = a / 2;
                b = b / 2;
                k++;
            } else if (a%2 == 0) {
                a = a / 2;
            } else if (b%2 == 0) {
                b = b / 2;
            } else {
                if (a > b) {
                    a = (a-b)/2;
                } else {
                    b = (b-a)/2;
                }
            }
        }
        int erg;
        if (a == 0) {
            erg = (int) (b * Math.pow(2,k));
        } else {
            erg = (int) (a * Math.pow(2,k));
        }
        return erg;

    }
    public static void main(String[] args) {
        System.out.println(steinGGT(12,18));
    }
}
