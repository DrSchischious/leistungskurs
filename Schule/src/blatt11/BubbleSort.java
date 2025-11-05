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

    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
            if (i == arr.length-1) {
                System.out.print("]");
            } else {
                System.out.print("| ");
            }
        }
    }

    public static void printArray(boolean[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
            if (i == arr.length-1) {
                System.out.print("]");
            } else {
                System.out.print("| ");
            }
        }
    }

    public static void printArray(byte[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
            if (i == arr.length-1) {
                System.out.print("]");
            } else {
                System.out.print("| ");
            }
        }
    }

    public static int[] randomArray(int l) {
        int[] arr = new int[l];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*100 - 50);
        }
        return arr;
    }



    public static void main(String[] args) {
        int[] arr = randomArray(15);
        printArray(arr);
        bubbleSort(arr);
        System.out.println();
        printArray(arr);

    }
}
