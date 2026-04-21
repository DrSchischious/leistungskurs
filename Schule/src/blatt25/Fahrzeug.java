package blatt25;

public abstract class Fahrzeug extends Fortbewegungsmittel {

    private String marke;
    private String modell;
    private int anzahlPersonen;


    public Fahrzeug(int beschleunigung, String marke, String modell, int geschwindigkeit, int anzahlPersonen, int gewicht) {
        super(geschwindigkeit, beschleunigung, gewicht);
        this.marke = marke;
        this.modell = modell;
        this.anzahlPersonen = anzahlPersonen;

    }



    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }



    public int getAnzahlPersonen() {
        return anzahlPersonen;
    }

    public void setAnzahlPersonen(int anzahlPersonen) {
        this.anzahlPersonen = anzahlPersonen;
    }



    public void fahren() {
        this.setGeschwindigkeit(this.getGeschwindigkeit() + this.getBeschleunigung());
    }

    public void lenken() {

    }

    public void bremsen() {

    }
}
