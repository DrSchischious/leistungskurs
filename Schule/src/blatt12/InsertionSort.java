package blatt12;

import java.util.Arrays;

public class InsertionSort {

    public static int[] insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;

            }
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] arr = blatt07.ArbeitMitArrays.randomArray(40);
        blatt07.ArbeitMitArrays.printArray(arr);
        arr = insertionSort(arr);
        blatt07.ArbeitMitArrays.printArray(arr);
    }
}
