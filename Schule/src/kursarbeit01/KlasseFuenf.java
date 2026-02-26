package kursarbeit01;

import java.util.Scanner;

public class KlasseFuenf {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bitte gib die Zahl ein, deren Teiler du bestimmen möchtest.");
        int n = sc.nextInt();

        System.out.print("T("+n+")={");
        for (int i = 1; i <= n; i++) {
            if (n%i == 0) {
                if (i == n) {
                    System.out.print(i);
                } else {
                    System.out.print(i +";");
                }

            }
        }
        System.out.println("}");
    }
}
