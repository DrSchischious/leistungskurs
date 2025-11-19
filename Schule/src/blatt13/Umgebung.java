package blatt13;

public class Umgebung {
    public static int findeExponent(double basis, double e) {
        if (basis < 0 || basis >= 1) {
            return -1;
        }
        int i = 0;
        while (true) {
            double p = Math.pow(basis,i);
            if (p < e) {
                return i;
            }
            i++;
        }
    }

    public static void main(String[] args) {
        System.out.println(findeExponent(0.5,0.001));
    }
}
