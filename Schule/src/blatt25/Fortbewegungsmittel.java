package blatt25;

public class Fortbewegungsmittel {
    private int geschwindigkeit;
    private int beschleunigung;
    private int gewicht;

    public Fortbewegungsmittel(int geschwindigkeit, int beschleunigung, int gewicht) {
        this.geschwindigkeit = geschwindigkeit;
        this.beschleunigung = beschleunigung;
        this.gewicht = gewicht;
    }

    public int getGeschwindigkeit() {
        return geschwindigkeit;
    }

    public void setGeschwindigkeit(int geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
    }

    public int getBeschleunigung() {
        return beschleunigung;
    }

    public void setBeschleunigung(int beschleunigung) {
        this.beschleunigung = beschleunigung;
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public void sayHello() {
        System.out.println("Hello!");
    }
}
