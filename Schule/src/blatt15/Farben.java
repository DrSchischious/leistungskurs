package blatt15;

import blatt13.Zufall;
import blatt14.MultiArrays;
import schisch_visualizer.SchischVisualizer;

public class Farben {

    static int[] spielerPosX;
    static int[] spielerPosY;
    static int[] reihenfolge;
    static char[][] spielfeld;
    final static char FARBE1 = '7';
    final static char FARBE2 = '9';
    final static char WAND = '8';

    public static void initialisiereSpielfeld(int n, int m) {
        if (n % 2 != 0) {
            n++;
        }
        if (m % 2 != 0) {
            m++;
        }
        spielfeld = MultiArrays.createEmpty2DCharArray(n,m);
        for (int x = 0; x < n; x++) {
            spielfeld[x][0] = WAND;
            spielfeld[x][m-1] = WAND;
        }
        for (int y = 0; y < m; y++) {
            spielfeld[0][y] = WAND;
            spielfeld[n-1][y] = WAND;
        }
    }

    public static void startPositionen() {
        spielerPosX = new int[8];
        spielerPosY = new int[8];

        //Spieler 1 bis 4
        for (int i = 0; i < 4; i++) {
            while (true) {
                int x = Zufall.zufallGanz(1, spielfeld.length / 2);
                int y = Zufall.zufallGanz(1, spielfeld[0].length - 1);
                if (spielfeld[x][y] == ' ') {
                    spielfeld[x][y] = 'P';
                    spielerPosX[i] = x;
                    spielerPosY[i] = y;
                    break;
                }
            }
        }
        //Spieler 5 bis 8
        for (int i = 4; i < 8; i++) {
            while (true) {
                int x = Zufall.zufallGanz(spielfeld.length / 2 + 1, spielfeld.length-1);
                int y = Zufall.zufallGanz(1, spielfeld[0].length-1);
                if (spielfeld[x][y] == ' ') {
                    spielfeld[x][y] = 'P';
                    spielerPosX[i] = x;
                    spielerPosY[i] = y;
                    break;
                }
            }
        }
    }

    public static int zaehlen(int team) {
        int count = 0;
        char col = ' ';
        if (team == 1) {
            col = FARBE1;
        } else if (team == 2) {
            col = FARBE2;
        } else if (team == 0) {
            col = ' ';
        } else {
            System.out.println("Fehler, falsche Teamnummer.");
            return -1;
        }

        for (int x = 0; x < spielfeld.length; x++) {
            for (int y = 0; y < spielfeld[0].length; y++) {
                if (spielfeld[x][y] == col) {
                    count++;
                }
                if (spielfeld[x][y] == 'P') {
                    if (team == 1) {
                        for (int k = 0; k < 4; k++) {
                            if (x == spielerPosX[k] && y == spielerPosY[k]) {
                                count++;
                            }
                        }
                    } else if (team == 2) {
                        for (int k = 4; k < 8; k++) {
                            if (x == spielerPosX[k] && y == spielerPosY[k]) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    public static void respawn(int spieler) {
        if (spieler < 4 && spieler >= 0) {
            //Team 1
            if (zaehlen(1) > 3) {
                //Es gibt noch Felder meines Teams, auf denen keine Spieler stehen.
                while (true) {
                    int x = Zufall.zufallGanz(1, spielfeld.length-1);
                    int y = Zufall.zufallGanz(1, spielfeld[0].length-1);
                    if (spielfeld[x][y] != 'P' && spielfeld[x][y] == FARBE1) {
                        spielfeld[x][y] = 'P';
                        spielerPosX[spieler] = x;
                        spielerPosY[spieler] = y;
                        break;
                    }
                }

            } else {
                //Es gibt keine Felder meines Teams.
                while (true) {
                    int x = Zufall.zufallGanz(1, spielfeld.length-1);
                    int y = Zufall.zufallGanz(1, spielfeld[0].length-1);
                    if (spielfeld[x][y] != 'P') {
                        spielfeld[x][y] = 'P';
                        spielerPosX[spieler] = x;
                        spielerPosY[spieler] = y;
                        break;
                    }
                }

            }
        } else if (spieler < 8 && spieler >= 0) {
            //Team 2
            if (zaehlen(2) > 3) {
                //Es gibt noch Felder meines Teams, auf denen keine Spieler stehen.
                while (true) {
                    int x = Zufall.zufallGanz(1, spielfeld.length-1);
                    int y = Zufall.zufallGanz(1, spielfeld[0].length-1);
                    if (spielfeld[x][y] != 'P' && spielfeld[x][y] == FARBE2) {
                        spielfeld[x][y] = 'P';
                        spielerPosX[spieler] = x;
                        spielerPosY[spieler] = y;
                        break;
                    }
                }

            } else {
                //Es gibt keine Felder meines Teams.
                while (true) {
                    int x = Zufall.zufallGanz(1, spielfeld.length-1);
                    int y = Zufall.zufallGanz(1, spielfeld[0].length-1);
                    if (spielfeld[x][y] != 'P') {
                        spielfeld[x][y] = 'P';
                        spielerPosX[spieler] = x;
                        spielerPosY[spieler] = y;
                        break;
                    }
                }

            }

        } else {
            //Fehler
            System.out.println("Fehler, Spieler existiert nicht.");
        }
    }

    public static void reihenfolge() {
        int count = 0;
        reihenfolge = new int[8];
        for (int i = 0; i < 8; i++) {
            reihenfolge[i] = -1;
        }
        while (count != 8) {
            while (true) {
                int i = Zufall.zufallGanz(0,7);
                if (reihenfolge[i] == -1) {
                    reihenfolge[i] = count;
                    count++;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        SchischVisualizer sv = new SchischVisualizer();
        initialisiereSpielfeld(60,60);
        sv.step(spielfeld);

        startPositionen();
        sv.step(spielfeld);

        reihenfolge();
        blatt07.ArbeitMitArrays.printArray(reihenfolge);


        sv.start();
    }
}
