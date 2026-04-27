package blatt26.aufgabe03;

public class Spieler {
    private Karte[] karten;
    private int sterne;

    public Spieler() {
        this.karten = new Karte[]{new Karte(Kartentyp.STEIN),new Karte(Kartentyp.STEIN),new Karte(Kartentyp.STEIN),new Karte(Kartentyp.STEIN),new Karte(Kartentyp.PAPIER),new Karte(Kartentyp.PAPIER),new Karte(Kartentyp.PAPIER),new Karte(Kartentyp.PAPIER),new Karte(Kartentyp.SCHERE),new Karte(Kartentyp.SCHERE),new Karte(Kartentyp.SCHERE),new Karte(Kartentyp.SCHERE)};
        this.sterne = 3;
    }

    public int anzahlKarten() {
        int n = 0;
        for (int i = 0; i < 12; i++) {
            if (karten[i] != null) {
                n++;
            }
        }
        return n;
    }

    public void sternErhalten() {
        this.sterne++;
    }

    public void sternVerlieren() {
        this.sterne--;
    }

    public boolean istVersklavt() {
        if (this.sterne == 0 || (this.anzahlKarten() == 0 && this.sterne < 3)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hatGewonnen() {
        if (this.anzahlKarten() == 0) {
            if (this.sterne >= 3) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Karte spieleKarte() {
        if (this.anzahlKarten() > 0) {
            while (true) {
                int zufall = blatt13.Zufall.zufallGanz(0,11);
                if (karten[zufall] != null) {
                    Karte k = karten[zufall];
                    karten[zufall] = null;
                    return k;
                }
            }
        }
        return null;
    }
}
