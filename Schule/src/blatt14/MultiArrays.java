package blatt14;

public class MultiArrays {

    public static void print2DArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            blatt07.ArbeitMitArrays.printArray(arr[i]);
        }
    }

    public static void print2DArray(double[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            blatt07.ArbeitMitArrays.printArray(arr[i]);
        }
    }

    public static void print2DArray(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            blatt07.ArbeitMitArrays.printArray(arr[i]);
        }
    }

    public static int[][] createRandom2DIntArray(int a, int b, int p, int q) {
        int[][] arr = new int[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                arr[i][j] = blatt13.Zufall.zufallGanz(p,q);
            }
        }
        return arr;
    }

    public static double[][] createRandom2DDoubleArray(int a, int b) {
        double[][] arr = new double[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                arr[i][j] = blatt13.Zufall.zufall(1);
            }
        }
        return arr;
    }

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

    public static char[][] createEmpty2DCharArray(int a, int b) {
        char[][] arr = new char[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                arr[i][j] = ' ';
            }
        }
        return arr;
    }

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
