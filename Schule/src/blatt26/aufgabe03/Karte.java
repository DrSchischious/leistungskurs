package blatt26.aufgabe03;

public class Karte implements Comparable<Karte>{

    private Kartentyp typ;

    public Karte(Kartentyp typ) {
        this.typ = typ;
    }

    public Kartentyp getTyp() {
        return typ;
    }

    @Override
    public int compareTo(Karte o) {
        if (o == null) {
            return -2;
        } else if (this.typ == o.typ) {
            return 0;
        } else if ((this.typ == Kartentyp.PAPIER && o.getTyp() == Kartentyp.SCHERE) || (this.typ == Kartentyp.SCHERE && o.getTyp() == Kartentyp.STEIN) || (this.typ == Kartentyp.STEIN && o.getTyp() == Kartentyp.PAPIER)) {
            return -1;
        } else {
            return 1;
        }
    }
}
