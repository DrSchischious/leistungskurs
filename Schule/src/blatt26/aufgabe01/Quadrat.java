package blatt26.aufgabe01;

public class Quadrat extends GeometrischesObjekt implements AbstandEbene{
    private Punkt eckpunkt;
    private double laenge;

    public Quadrat(Punkt eckpunkt, double laenge) {
        this.eckpunkt = eckpunkt;
        if (this.laenge > 0) {
            this.laenge = laenge;
        } else {
            this.laenge = 1;
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

    public Punkt getEckpunkt() {
        return eckpunkt;
    }

    public void setEckpunkt(Punkt eckpunkt) {
        this.eckpunkt = eckpunkt;
    }

    @Override
    public double berechneUmfang() {
        return 4*this.laenge;
    }

    @Override
    public double berechneFlaecheninhalt() {
        return this.laenge*this.laenge;
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
