package blatt12;

public class SelectionSort {
    public static void selectionSort(int[] arr) {
        for (int j = 1; j <= arr.length - 1; j++) {
            int tmp = arr[j];
            int i = j - 1;
            while (i >= 0 && arr[i] > tmp) {
                arr[i + 1] = arr[i];
                i = i - 1;
            }
            arr[i+1] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,-1,2,5,91,5,4,1,0};
        selectionSort(arr);
        blatt07.ArbeitMitArrays.printArray(arr);
    }
}
