package blatt06;

import java.util.Scanner;

public class Wertetabelle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welchen Grad soll das Polynom haben?");
        int n = sc.nextInt();

        double[] koeff = new double[n+1];

        for (int i = 0; i < koeff.length; i++) {
            System.out.println("Nenne den Koeffizient " + i + ":");
            koeff[i] = sc.nextDouble();
        }

        System.out.println("Gib die Grenzen der Wertetabelle ein.");
        int a = sc.nextInt();
        int b = sc.nextInt();

        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        System.out.println("Wähle eine Schrittweite.");
        double step = sc.nextDouble();

        System.out.println("Berechnen.");
        System.out.println("Berechnen.");
        System.out.println("Berechnen.");

        System.out.println();
        System.out.println();

        double x = a;
        while (x <= b) {
            //Auswerten von x
            int y = 0;
            for (int i = 0; i < koeff.length; i++) {
                y += koeff[i]*Math.pow(x, i);
            }
            System.out.println("( " + x + " | " + y + " )");

            x += step;
        }

    }
}
