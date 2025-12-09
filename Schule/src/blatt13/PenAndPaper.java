package blatt13;

public class PenAndPaper {
    public static int wuerfel(int n, int x) {
        if (n < 0) {
            System.out.println("Fehler.");
            System.exit(0);
        }
        if (x < 0) {
            System.out.println("Fehler.");
            System.exit(0);
        }

        int wert = 0;
        for (int i = 0; i < n; i++) {
            wert += Zufall.zufallGanz(1,x);
        }
        return wert;
    }

    public static boolean check(int wert, boolean vorteil, boolean nachteil, int boni) {
        if (vorteil == true && nachteil == true) {
            System.out.println("Fehler.");
            System.exit(0);
        }
        int w = wuerfel(1,20);
        if (vorteil == true) {
            int w2 = wuerfel(1,20);
            if (w2 > w) {
                w = w2;
            }
        } else if (nachteil == true) {
            int w2 = wuerfel(1,20);
            if (w2 < w) {
                w = w2;
            }
        }
        if (w == 20) {
            return true;
        }
        if (w == 1) {
            return false;
        }

        if (w + boni >= wert) {
            return true;
        } else {
            return false;
        }
    }

    public static int angriff(int n, int x, boolean vorteil, boolean nachteil, int bonus, int klasse, boolean treffer) {
        if (check(klasse,vorteil,nachteil, bonus) || treffer == true) {
            int schaden = wuerfel(n,x);
            return schaden;
        } else {
            return -1;
        }
    }

    public static int simulation() {
        //Spieler A
        int tp1 = 20;
        int nt1 = 3;

        //Spieler B
        int tp2 = 42;
        int vt1 = 3;

        while (true) {
            if (vt1 > 0) {
                int dmg = angriff(1,6, true, false, 3, 10, false);
                if (dmg > 0) {
                    tp1 -= dmg;
                }
                vt1--;
            } else {
                int dmg = angriff(1,6, false, false, 3, 10, false);
                if (dmg > 0) {
                    tp1 -= dmg;
                }
            }

            if (tp1 <= 0) {
                return 1; //Bard
            }

            if (nt1 > 0) {
                int dmg = angriff(1,8, false, true, 1, 10, false);
                if (dmg > 0) {
                    tp2 -= dmg;
                    tp2 -= angriff(1,8, false, false, 1, 10, true);
                }
                nt1--;
            } else {
                int dmg = angriff(1,8, false, false, 1, 10, false);
                if (dmg > 0) {
                    tp2 -= dmg;
                    tp2 -= angriff(1,8, false, false, 1, 10, true);
                }
            }

            if (tp2 <= 0) {
                return -1; //Barbar
            }



        }

    }

    public static void main(String[] args) {
        int bard = 0;
        int barbar = 0;
        for (int i = 0; i < 100; i++) {
            if (simulation() == -1) {
                barbar++;
            } else {
                bard++;
            }
        }
        System.out.println("Barbar: " + barbar);
        System.out.println("Bard: " + bard);

    }
}
