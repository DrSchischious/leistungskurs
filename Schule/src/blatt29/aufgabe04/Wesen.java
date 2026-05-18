package blatt29.aufgabe04;

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
    }



    public void angreifen(Wesen w) {
        //Ausweichen(?)
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
            //HIT
            if (Math.random() >= ((double) w.lck /10)) {
                //HIT
                //Calc-Damage
                int dmg;
                if (Math.random() < ((double) this.lck/5)) {
                    //CRIT
                    dmg = 4*this.str;
                    System.out.println("Critical!");
                } else {
                    dmg = 4*this.str - 2*w.vit;
                    System.out.println("Hit!");
                }

                if (dmg > 0) {
                    //DAMAGE
                    if (w.hp - dmg > 0) {
                        w.hp -= dmg;
                    } else {
                        //Dead
                        w.hp = 0;
                        System.out.println("Dead.");
                    }
                } else {
                    //No Damage
                    System.out.println("Too Weak!");
                }

            } else {
                //Lucky Dodge
                System.out.println("Lucky Dodge!");
            }
        } else {
            //NO HIT
            System.out.println("Dodge!");
        }

    }
}