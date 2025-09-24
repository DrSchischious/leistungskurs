package blatt04;

import java.util.Scanner;

public class Topfschlagen {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int anzahl = 0;

        int x = (int)(Math.random()*50);
        int y = (int)(Math.random()*50);

        int topfX = (int)(Math.random()*50);
        int topfY = (int)(Math.random()*50);

        while (topfX == x && topfY == y) { //Unterschiedliche Startpositionen
            topfX = (int)(Math.random()*50);
            topfY = (int)(Math.random()*50);
        }

        int diff = Math.abs(topfX - x) + Math.abs(topfY - y);

        System.out.println("Folgende Optionen stehen dir zur Verfügung:");
        System.out.println("\t(w) - nach Oben bewegen.");
        System.out.println("\t(a) - nach Links bewegen.");
        System.out.println("\t(s) - nach Unten bewegen.");
        System.out.println("\t(d) - nach Rechts bewegen.");
        System.out.println("");

        while (true) {


            String eingabe = sc.nextLine();

            if (eingabe.equals("w") || eingabe.equals("W")) {
                y = y - 1;
                anzahl++;
            } else if (eingabe.equals("a") || eingabe.equals("A")) {
                x = x - 1;
                anzahl++;
            } else if (eingabe.equals("s") || eingabe.equals("S")) {
                y = y + 1;
                anzahl++;
            } else if (eingabe.equals("d") || eingabe.equals("D")) {
                x = x + 1;
                anzahl++;
            } else {
                System.out.println("Eingabe nicht erlaubt.");
                System.out.println("Folgende Optionen stehen dir zur Verfügung:");
                System.out.println("\t(w) - nach Oben bewegen.");
                System.out.println("\t(a) - nach Links bewegen.");
                System.out.println("\t(s) - nach Unten bewegen.");
                System.out.println("\t(d) - nach Rechts bewegen.");
                System.out.println("");
            }

            if (anzahl%4 == 0) {
                System.out.println("Der Topf hat sich bewegt!");
                int r = (int)(Math.random()*4);
                if (r == 0) {
                    topfX++;
                } else if (r == 1) {
                    topfX--;
                } else if (r ==2) {
                    topfY++;
                } else if (r ==3) {
                    topfY--;
                }
            }

            int newDiff = Math.abs(topfX - x) + Math.abs(topfY - y);

            if (diff == 0) {
                System.out.println("Gewonnen!");
                break;
            }

            if (newDiff < diff) {
                if (newDiff < 3) {
                    System.out.println("Vulkan!");
                } else {
                    System.out.println("Wärmer.");
                }

            } else if (newDiff > diff) {
                if (newDiff > 40) {
                    System.out.println("Antarktis!");
                } else {
                    System.out.println("Kälter.");
                }
            } else {
                System.out.println("Gleich wie zuvor!");
            }



            diff = newDiff;
            System.out.println();

        }
    }
}
