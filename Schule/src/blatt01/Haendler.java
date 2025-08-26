package blatt01;

public class Haendler {
    public static void main(String[] args) {
        int anzahlSD = 9;
        int anzahlMini = 5;
        int anzahlMicro = 0;

        double preisSD = 5;
        double preisMini = 8;
        double preisMicro = 12;

        double gesamt = anzahlSD*preisSD+anzahlMini*preisMini + anzahlMicro*preisMicro;
        System.out.println("Der Preis für...");
        System.out.println(anzahlSD + " normale SD-Karten");
        System.out.println(anzahlMini + " MiniSD-Karten");
        System.out.println(anzahlMicro + " MicroSD-Karten");
        System.out.println("...lautet " + gesamt + " €");
    }
}
