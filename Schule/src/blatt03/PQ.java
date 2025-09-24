package blatt03;

import java.util.Scanner;

public class PQ {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Geben Sie ihre P Zahl ein");
        double p = input.nextDouble();

        System.out.println("Geben Sie ihre Q Zahl ein");
        double q = input.nextDouble();

        double x1 = -p / 2 + Math.sqrt( (p / 2 * p / 2) - q );
        double x2 = -p / 2 - Math.sqrt( (p / 2 * p / 2) - q );

        if (x1 == x2) {
            System.out.printf("Es gibt nur eine Lösung die ist %.2f" , x1);
        } else {
            System.out.printf("Es gibt zwei Lösungen nämlich ist X1 = %.2f und X2 ist = %.2f" , x1 , x2);
        }

    }

}
