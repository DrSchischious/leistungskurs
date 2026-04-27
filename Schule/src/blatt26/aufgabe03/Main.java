package blatt26.aufgabe03;

public class Main {

    public static void main(String[] args) {
        int n = 1000;
        Simulation s = new Simulation(n);
        int i = 1;
        while (s.runde() == true) {
            System.out.println("Spiel " + i + ":");
            System.out.println("Anzahl Spieler: " + s.anzahlSpieler());
            System.out.println("Anzahl Versklavt: " + s.getVersklavt());
            System.out.println("Anzahl Gewonnen: " + s.getGewonnen());
            System.out.println();
            i++;
        }

        System.out.println("Spiel " + i + ":");
        System.out.println("Anzahl Spieler: " + s.anzahlSpieler());
        System.out.println("Anzahl Versklavt: " + s.getVersklavt());
        System.out.println("Anzahl Gewonnen: " + s.getGewonnen());
        i++;
    }
}
