package blatt26.aufgabe01;

public class Kreis extends GeometrischesObjekt implements AbstandEbene {
    private Punkt mittelpunkt;
    private double radius;

    public Kreis(Punkt mittelpunkt, double radius) {
        this.mittelpunkt = mittelpunkt;
        if (this.radius > 0) {
            this.radius = radius;
        } else {
            this.radius = 1;
        }

    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (this.radius > 0) {
            this.radius = radius;
        } else {
            this.radius = 1;
        }

    }

    public Punkt getMittelpunkt() {
        return mittelpunkt;
    }

    public void setMittelpunkt(Punkt mittelpunkt) {
        this.mittelpunkt = mittelpunkt;
    }

    @Override
    public double berechneUmfang() {
        return 2*Math.PI*this.radius;
    }

    @Override
    public double berechneFlaecheninhalt() {
        return Math.PI*this.radius*this.radius;
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
