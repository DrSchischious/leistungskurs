package blatt29.aufgabe04;

import java.util.ArrayList;

public abstract class Wesen {

    private int level;
    private int exp;
    private int maxHP;
    private int hp;

    private int str;
    private int vit;
    private int dex;
    private int mag;
    private int lck;

    private Waffe waffe;
    private Kopf kopf;
    private Ruestung ruestung;

    private ArrayList<Status> status;

    public Wesen(int level, int maxHP, int str, int vit, int dex, int mag, int lck) {
        this.level = level;
        this.exp = 0;
        this.maxHP = maxHP;
        this.hp = this.maxHP;
        this.str = str;
        this.vit = vit;
        this.dex = dex;
        this.mag = mag;
        this.lck = lck;

        this.waffe = null;
        this.kopf = null;
        this.ruestung = null;

        this.status = new ArrayList<Status>();
    }

    public boolean istTreffer(Wesen w) {
        //Ausweichen(?)

        if (w.status.contains(Status.SCHLAF)) {
            return true;
        }

        double aus = (double)(this.dex/w.dex);
        double real = 0;
        if (aus < 1) {
            //Gegner ist schneller
            real = 0.1;
            if (aus < 0.9) {
                real = 0.2;
            }
            if (aus < 0.8) {
                real = 0.3;
            }
            if (aus < 0.7) {
                real = 0.4;
            }
        } else if (aus > 1) {
            //Wir sind schneller
            real = 0.1;
            if (aus > 1.1) {
                real = 0.07;
            }
            if (aus > 1.2) {
                real = 0.05;
            }
            if (aus < 1.3) {
                real = 0.03;
            }
        }

        if (Math.random() < real) {
            if (Math.random() < ((double) w.lck / 100 / 10)) {
                System.out.println("Lucky Dodge!");
                return false;
            } else {
                System.out.println("Hit!");
                return true;
            }
        } else {
            System.out.println("Dodge!");
            return false;
        }
    }

    public void heilen(int wert) {
        if (this.hp + wert <= this.maxHP) {
            this.hp += wert;
        } else {
            this.hp = this.maxHP;
        }
    }

    public void schaden(int schaden) {
        if (schaden <= 0) {
            System.out.println("Zu schwach.");
        } else {
            if (schaden >= this.hp) {
                this.hp = 0;
                System.out.println("Besiegt.");
            } else {
                this.hp = this.hp - schaden;
                System.out.println("Schaden.");
            }
        }
    }

    public void angreifen(Wesen w) {
        if (istTreffer(w) == true) {
            //Calc-Damage

            if (Math.random() < ((double) this.lck/5)) {
                //CRIT
                w.schaden(4*this.str);
                System.out.println("Critical!");
            } else {
                w.schaden(4*this.str - 2*w.vit);
                System.out.println("Hit!");
            }
        }
    }

    public boolean setWaffe(Waffe w) {
        if (this.waffe == null) {
            this.waffe = w;
            updateStats(w, true);
            return true;
        } else {
            return false;
        }
    }

    public boolean setKopf(Kopf k) {
        if (this.kopf == null) {
            this.kopf = k;
            updateStats(k, true);
            return true;
        } else {
            return false;
        }
    }

    public boolean setRuestung(Ruestung r) {
        if (this.ruestung == null) {
            this.ruestung = r;
            updateStats(r, true);
            return true;
        } else {
            return false;
        }
    }

    private void updateStats(Ausruestung a, boolean add) {
        if (add) {
            this.str += a.getStr();
            this.vit += a.getVit();
            this.dex += a.getDex();
            this.mag += a.getMag();
            this.lck += a.getLck();
        } else {
            this.str -= a.getStr();
            this.vit -= a.getVit();
            this.dex -= a.getDex();
            this.mag -= a.getMag();
            this.lck -= a.getLck();
        }

    }

    public boolean ausruesten(Ausruestung a) {
        if (this.getClass() == a.getKlasse()) {
            if (a.getClass() == Waffe.class) {
                return setWaffe((Waffe)a);
            } else if (a.getClass() == Kopf.class) {
                return setKopf((Kopf)a);
            } else if (a.getClass() == Ruestung.class) {
                return setRuestung((Ruestung)a);
            }
        }
        return false;
    }

    public void ablegen(int i) {
        if (i == 0) {
            this.updateStats(this.waffe, false);
            this.waffe = null;

        } else if (i == 1) {
            this.updateStats(this.kopf, false);
            this.kopf = null;

        } else if (i == 2) {
            this.updateStats(this.ruestung, false);
            this.ruestung = null;

        }

    }
}