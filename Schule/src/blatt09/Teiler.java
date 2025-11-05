package blatt09;

public class Teiler {

    public static boolean istTeiler(int n, int t) {
        if (n % t == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void alleTeiler(int n) {
        for (int i = 1; i <= n; i++) {
            if (istTeiler(n,i)) {
                System.out.println(i);
            }
        }
    }

    public static int anzahlTeiler(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (istTeiler(n,i)) {
                count++;
            }
        }
        return count;
    }

    public static int[] teiler(int n) {
        int[] teiler = new int[anzahlTeiler(n)];
        int i = 0;
        for (int j = 1; j <= n; j++) {
            if (istTeiler(n,j)) {
                teiler[i] = j;
                i++;
            }
        }
        return teiler;
    }

    public static void main(String[] args) {
        blatt11.BubbleSort.printArray(teiler(10));
    }

}
