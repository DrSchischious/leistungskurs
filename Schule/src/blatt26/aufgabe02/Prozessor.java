package blatt26.aufgabe02;

public class Prozessor {
    private int anzahlKerne;
    private double geschwindigkeit;
    private double cache;

    public Prozessor(int anzahlKerne, double geschwindigkeit, double cache) {
        this.anzahlKerne = anzahlKerne;
        this.geschwindigkeit = geschwindigkeit;
        this.cache = cache;
    }

    public int getAnzahlKerne() {
        return anzahlKerne;
    }

    public void setAnzahlKerne(int anzahlKerne) {
        if (anzahlKerne >= 1) {
            this.anzahlKerne = anzahlKerne;
        }

    }

    public double getGeschwindigkeit() {
        return geschwindigkeit;
    }

    public void setGeschwindigkeit(double geschwindigkeit) {
        if (geschwindigkeit <= 0) {

        } else {
            this.geschwindigkeit = geschwindigkeit;
        }

    }

    public double getCache() {
        return cache;
    }

    public void setCache(double cache) {
        if (cache > 0) {
            this.cache = cache;
        }
    }
}
