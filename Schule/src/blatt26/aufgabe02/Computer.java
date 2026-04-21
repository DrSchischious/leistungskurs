package blatt26.aufgabe02;

public class Computer implements Comparable<Computer> {
    private Prozessor p;
    private int ram;
    private int speicher;

    public Computer(Prozessor p, int ram, int speicher) {
        this.p = p;
        this.ram = ram;
        this.speicher = speicher;
    }

    @Override
    public int compareTo(Computer o) {
        if (this.p.getAnzahlKerne() > o.p.getAnzahlKerne()) {
            return 1;
        } else if (this.p.getAnzahlKerne() < o.p.getAnzahlKerne()) {
            return -1;
        }
        if (this.p.getGeschwindigkeit() > o.p.getGeschwindigkeit()) {
            return 1;
        } else if (this.p.getGeschwindigkeit() < o.p.getGeschwindigkeit()) {
            return -1;
        }
        if (this.p.getCache() > o.p.getCache()) {
            return 1;
        } else if (this.p.getCache() < o.p.getCache()) {
            return -1;
        }
        if (this.getRam() > o.getRam()) {
            return 1;
        } else if (this.getRam() < o.getRam()) {
            return -1;
        }
        if (this.speicher > o.speicher) {
            return 1;
        } else if  (this.speicher < o.speicher) {
            return -1;
        }

        return 0;
    }

    public Prozessor getP() {
        return p;
    }

    public void setP(Prozessor p) {
        this.p = p;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        if (ram > 0) {
            this.ram = ram;
        }

    }

    public int getSpeicher() {
        return speicher;
    }

    public void setSpeicher(int speicher) {
        if (speicher > 0) {
            this.speicher = speicher;
        }

    }
}
