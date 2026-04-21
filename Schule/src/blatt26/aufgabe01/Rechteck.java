package blatt26.aufgabe01;

public class Rechteck extends GeometrischesObjekt implements AbstandEbene {
    private Punkt eckpunkt;
    private double laenge;
    private double breite;

    public Rechteck(Punkt eckpunkt, double laenge, double breite) {
        this.eckpunkt = eckpunkt;
        if (this.laenge > 0) {
            this.laenge = laenge;
        } else {
            this.laenge = 1;
        }

        if (this.breite > 0) {
            this.breite = breite;
        } else {
            this.breite = 1;
        }
    }

    public double getLaenge() {
        return laenge;
    }

    public void setLaenge(double laenge) {
        if (this.laenge > 0) {
            this.laenge = laenge;
        } else {
            this.laenge = 1;
        }

    }

    public double getBreite() {
        return breite;
    }

    public void setBreite(double breite) {
        if (this.breite > 0) {
            this.breite = laenge;
        } else {
            this.breite = 1;
        }
    }

    public Punkt getEckpunkt() {
        return eckpunkt;
    }

    public void setEckpunkt(Punkt eckpunkt) {
        this.eckpunkt = eckpunkt;
    }

    @Override
    public double berechneUmfang() {
        return 2*this.laenge + 2* this.breite;
    }

    @Override
    public double berechneFlaecheninhalt() {
        return this.laenge*this.breite;
    }

    @Override
    public double distanz(AbstandEbene geo) {
        if (geo instanceof Rechteck) {
            //Berechnungen.
        } else if (geo instanceof Quadrat) {

        } else {

        }
        return 0;
    }
}
