package blatt26.aufgabe01;

public class Main {
    public static void main(String[] args) {
        Rechteck r = new Rechteck(new Punkt(2,9),5,4);
        Kreis k = new Kreis(new Punkt(4,4),5);
        Quadrat q = new Quadrat(new Punkt(8,10),7);
        
        r.distanz(k);

    }
}
