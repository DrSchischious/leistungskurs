package blatt18;

public class Summe {
    public static int summe(int n) {
        if (n == 1) {
            return 1;
        } else if (n > 1) {
            return n +  summe(n - 1);
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {

    }
}
