package blatt13;

public class Zufall {

    public static double zufall(int b) {
        return Math.random()*(b+1);
    }

    public static int zufallGanz(int b) {
        return (int)zufall(b);
    }

    public static double zufall(int a, int b) {
        return (Math.random()*(b-a+1))+a;
    }

    public static int zufallGanz(int a, int b) {
        return (int)zufall(a,b);
    }

    public static int[] zufallArray(int l, int a, int b) {
        int[] arr = new int[l];
        for (int i = 0; i < l; i++) {
            arr[i] = zufallGanz(a,b);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = zufallArray(20, 4, 9);
        blatt07.ArbeitMitArrays.printArray(arr);
    }
}
