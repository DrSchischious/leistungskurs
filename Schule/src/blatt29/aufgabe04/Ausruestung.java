package blatt29.aufgabe04;

public abstract class Ausruestung {
    private Class klasse;

    private int str;
    private int vit;

    private int dex;
    private int mag;
    private int lck;

    public Ausruestung(Class klasse, int str, int vit, int dex, int mag, int lck) {
        this.klasse = klasse;
        this.str = str;
        this.vit = vit;
        this.dex = dex;
        this.mag = mag;
        this.lck = lck;
    }

    public Class getKlasse() {
        return klasse;
    }

    public int getStr() {
        return str;
    }

    public int getVit() {
        return vit;
    }

    public int getDex() {
        return dex;
    }

    public int getMag() {
        return mag;
    }

    public int getLck() {
        return lck;
    }
}
