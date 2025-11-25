package blatt11;

public class BubbleSort {

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length-1; j++) {
                if(arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }




    public static void main(String[] args) {
        int[] arr = blatt07.ArbeitMitArrays.randomArray(15);
        blatt07.ArbeitMitArrays.printArray(arr);
        bubbleSort(arr);
        System.out.println();
        blatt07.ArbeitMitArrays.printArray(arr);

    }
}
