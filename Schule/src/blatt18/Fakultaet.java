package blatt18;

public class Fakultaet {

    public static int fakultaet(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            return 1;
        } else if (n == 1) { //Abbruch
            return 1;
        } else {
            return n*fakultaet(n-1); //Veränderter Rekursionsaufruf
        }
    }

    public static void main(String[] args) {
        System.out.println(fakultaet(-2));

    }
}
