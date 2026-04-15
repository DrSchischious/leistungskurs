package kursarbeit_gk_01;

public class Stabilizer {
    public static void swap(int[] liste, int i, int j) {
        int temp = liste[i];
        liste[i] = liste[j];
        liste[j] = temp;
    }

    public static void stabilize(int[] liste) {
        int i = 0;
        int j = 0;
        while (i < liste.length) {
            //Breakpoint
            j = 0;
            while (j < liste.length-1) {
                if (liste[j] > liste[j+1]) {
                    swap(liste, j, j+1);
                }
                j++;
            }
            i++;
        }
        //Breakpoint
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,-2,21,5,0,-6};
    }
}
