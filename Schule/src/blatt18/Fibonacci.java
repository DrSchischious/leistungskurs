package blatt18;

public class Fibonacci {

    static long[] fibos;

    public static long dynamicFibonacci(int n) {
        if (fibos.length < n) {
            fibos = new long[n];
            System.out.println("Reset");
        }
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n > 1) {
            long a = 0;
            long b = 0;

            if (fibos[n-1] != 0) {
                a = fibos[n-1];
            } else {
                a = dynamicFibonacci(n-1);
                fibos[n-1] = a;
                System.out.println("Set " + (n-1) + " = " + a);
            }
            if (fibos[n-2] != 0) {
                b = fibos[n-2];
            } else {
                b = dynamicFibonacci(n-2);
                fibos[n-2] = b;
                System.out.println("Set " + (n-2) + " = " + b);
            }
            return a+b;
        } else {
            return -1;
        }

    }


    public static int fibonacci(int n) {

        if (n == 0) {
            //System.out.println("fib(0)");
            return 0;
        } else if (n == 1) {
            //System.out.println("fib(1)");
            return 1;
        } else if (n > 1) {
            //System.out.println("fib("+(n-1)+")+fib("+(n-2)+")");
            return fibonacci(n-1) + fibonacci(n-2);
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        fibos = new long[0];
        long start = System.currentTimeMillis();
        System.out.println(dynamicFibonacci(90));
        long end = System.currentTimeMillis();
        long dur = end - start;
        System.out.println(dur + " Millisekunden");
        System.out.println(dur/1000 + " Sekunden");
    }
}
