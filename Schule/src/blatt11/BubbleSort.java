package blatt11;

public class BubbleSort {

    /**
     * Tauscht die Positionen zweier Elemente im Array.
     * @param arr Betroffener Array
     * @param i Element 1
     * @param j Element 2
     */
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Führt den BubbleSort zum Sortieren einer Liste durch.
     * @param arr zu sortierende Liste
     */
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
        int[] arr = blatt07.ArbeitMitArrays.randomArray(120);
        blatt07.ArbeitMitArrays.printArray(arr);
        bubbleSort(arr);
        System.out.println();
        blatt07.ArbeitMitArrays.printArray(arr);

    }
}
