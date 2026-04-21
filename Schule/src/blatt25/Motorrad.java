package blatt25;

public class Motorrad extends Fahrzeug {

    public Motorrad(int gewicht, int anzahlPersonen, int beschleunigung, int geschwindigkeit, String modell, String marke) {
        super(beschleunigung, marke, modell, geschwindigkeit, anzahlPersonen, gewicht);
    }

    public boolean wheelie() {
        if (this.getBeschleunigung() > 100) {
            return true;
        } else {
            return false;
        }

    }
}
