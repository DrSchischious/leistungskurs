package blatt18;

public class TuermeVonHanoi {
    static String stab1 = "";
    static String stab2 = "";
    static String stab3 = "";

    public static void initStaebe(int n) {
        if (n > 0) {
            for (int i = 1; i <= n; i++) {
                stab1 = i+stab1;
            }
            stab2 = "";
            stab3 = "";
        }
    }

    public static void printStaebe() {
        System.out.println("Stab A = " + stab1);
        System.out.println("Stab B = " + stab2);
        System.out.println("Stab C = " + stab3);
        System.out.println();
    }

    public static void removeScheibe(int i) {
        if (i == 1) {
            stab1 = stab1.substring(0,stab1.length()-1);
        } else if (i == 2) {
            stab2 = stab2.substring(0,stab2.length()-1);
        } else if (i == 3) {
            stab3 = stab3.substring(0,stab3.length()-1);
        }
    }

    public static void addScheibe(int i, String s) {
        if (i == 1) {
            stab1 = stab1+s;
        } else if (i == 2) {
            stab2 = stab2+s;
        } else if (i == 3) {
            stab3 = stab3+s;
        }
    }

    public static String getStab(int i) {
        switch(i) {
            case 1:
                return stab1;
            case 2:
                return stab2;
            case 3:
                return stab3;
        }
        return null;
    }

    public static boolean platziereScheibe(int von, int zu) {
        if (von > 3 || von < 1 || zu > 3 || zu < 1) {
            return false;
        } else {
            String s_von = getStab(von);
            String s_zu = getStab(zu);

            //Nehme Scheibe
            if (s_von.length() > 0) {
                String s1 = s_von.substring(s_von.length()-1);
                //Prüfe, ob Scheibe größer ist als auf dem anderen Turm
                int a = Integer.parseInt(s1);

                if (s_zu.length() > 0) {
                    String s2 = s_zu.substring(s_zu.length()-1);
                    int b = Integer.parseInt(s2);

                    if (a > b) {
                        //Fehler.
                        return false;
                    }
                }

                removeScheibe(von);
                addScheibe(zu,s1);

                System.out.println("Verschiebe Scheibe von " + von + " nach " + zu);
                printStaebe();

                return true;


            }
            return false;
        }
    }

    public static int getRest(int von, int zu) {
        int rest = 0;
        if (von == 1 && zu == 2) {
            rest = 3;
        } else if (von == 1 && zu == 3) {
            rest = 2;
        } else if (von == 2 && zu == 3) {
            rest = 1;
        } else if (von == 2 && zu == 1) {
            rest = 3;
        } else if (von == 3 && zu == 1) {
            rest = 2;
        } else if (von == 3 && zu == 2) {
            rest = 1;
        }
        return rest;
    }

    public static void platziereTurm(int von, int zu, int groesse) {
        if (groesse == 0) {
            //Hier passiert nichts.
        } else {
            int rest = getRest(von, zu);
            //1. platziereTurm g-1 von VON nach REST mit geringerer Größe
            platziereTurm(von,rest,groesse-1);
            //2. platziereScheibe von VON nach ZU
            platziereScheibe(von,zu);
            //3. platziere Turm g-1 von REST nach ZU mit geringerer Größe
            platziereTurm(rest,zu,groesse-1);
        }

    }

    public static void main(String[] args) {
        initStaebe(4);
        printStaebe();
        platziereTurm(1,3,4);

    }
}
