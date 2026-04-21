package blatt25;

public class Auto extends Fahrzeug {

    private int anzahlFenster;

    public Auto(String marke, String modell, int geschwindigkeit, int beschleunigung, int anzahlPersonen, int anzahlFenster, int gewicht) {
        super(beschleunigung, marke, modell, geschwindigkeit, anzahlPersonen, gewicht);
        this.anzahlFenster = anzahlFenster;
    }


    public int getAnzahlFenster() {
        return anzahlFenster;
    }

    public void setAnzahlFenster(int anzahlFenster) {
        this.anzahlFenster = anzahlFenster;
    }

    @Override
    public void sayHello() {
        super.sayHello();
        System.out.println("NICHT HALLO!");
    }

    public void airbagAusloesen() {
        sayHello();
    }
}
