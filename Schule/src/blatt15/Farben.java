package blatt15;

import blatt13.Zufall;
import blatt13.Zufall.*;
import blatt14.MultiArrays;
import blatt14.Simulationen;
import schisch_visualizer.SchischVisualizer;
import blatt14.Simulationen.*;

import static blatt13.Zufall.zufallGanz;
import static blatt14.Simulationen.*;

public class Farben {

    static int[] spielerPosX;
    static int[] spielerPosY;
    static int[] reihenfolge;
    static char[][] spielfeld;
    final static char FARBE1 = '7';
    final static char FARBE2 = '9';
    final static char WAND = '8';

    static boolean flaechePushen = true;
    static boolean gegnerJagen = false;
    static boolean search = false;
    static int searchCount = 0;
    static int gegegnerJagenCount = 0;
    static int flaechePushenCount = 0;

    static int laenge;
    static int breite;
    static int[][] teamHeatmap = new int[laenge][breite];

    static int[][] spieler = new int[9][2]; //[spielerid][x -> 0; y -> 1]
    static boolean[] darfZiehen = {true, true, true, true, true, true, true, true, true};


    static boolean spielerBewegt;
    static int gescannteFelder = 0;
    static int schrittAnzahlOne = 10;
    static int schrittAnzahlTwo = 10;
    static int schrittAnzahlOneV = 10;
    static int schrittAnzahlTwoV = 10;
    static int[] richtungLast = new int[8];




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
                int x = zufallGanz(1, spielfeld.length / 2);
                int y = zufallGanz(1, spielfeld[0].length - 1);
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

    public static void zugSchisch(int spieler) {

        //Check around
        int x = spielerPosX[spieler];
        int y = spielerPosY[spieler];

        int[] directions = new int[4];
        int k = 1;
        while (true) {

            int pos = Zufall.zufallGanz(0,3);
            if (directions[pos] == 0) {
                directions[pos] = k;
                k++;
            }

            if (k == 5) {
                break;
            }
        }

        int team = 0;
        if (spieler < 4) {
            team = 1;
        } else {
            team = 2;
        }

        boolean done = false;


        if (spieler%4 == 0 || spieler%4 == 1) {
            //Taktik für zwei Spieler


            //if enemy one field around, attack
            for (int j = 0; j < directions.length; j++) {
                if (directions[j] == 1) {
                    //North
                    if (Simulationen.getNorden(spielfeld,x,y,false) == 'P' && done == false) {
                        //Check if Enemy
                        int cx = x;
                        int cy = y-1;
                        for (int i = 0; i < 8; i++) {
                            if (spielerPosX[i] == cx && spielerPosY[i] == cy) {
                                if ((team == 1 && i < 4) || (team == 2 && i >= 4)) {
                                    //Mein Team
                                } else {
                                    //GEGNER
                                    if (team == 1) {
                                        spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                                    } else {
                                        spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                                    }

                                    spielerPosX[spieler] = cx;
                                    spielerPosY[spieler] = cy;
                                    spielerPosX[i] = -1;
                                    spielerPosY[i] = -1;

                                    done = true;
                                }

                            }
                        }
                    }

                } else if (directions[j] == 2) {
                    //East
                    if (getOsten(spielfeld,x,y,false) == 'P' && done == false) {
                        //Check if Enemy
                        int cx = x+1;
                        int cy = y;
                        for (int i = 0; i < 8; i++) {
                            if (spielerPosX[i] == cx && spielerPosY[i] == cy) {
                                if ((team == 1 && i < 4) || (team == 2 && i >= 4)) {
                                    //Mein Team
                                } else {
                                    //GEGNER
                                    if (team == 1) {
                                        spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                                    } else {
                                        spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                                    }

                                    spielerPosX[spieler] = cx;
                                    spielerPosY[spieler] = cy;
                                    spielerPosX[i] = -1;
                                    spielerPosY[i] = -1;

                                    done = true;
                                }

                            }
                        }
                    }

                } else if (directions[j] == 3) {
                    //South
                    if (getSueden(spielfeld,x,y,false) == 'P' && done == false) {
                        //Check if Enemy
                        int cx = x;
                        int cy = y+1;
                        for (int i = 0; i < 8; i++) {
                            if (spielerPosX[i] == cx && spielerPosY[i] == cy) {
                                if ((team == 1 && i < 4) || (team == 2 && i >= 4)) {
                                    //Mein Team
                                } else {
                                    //GEGNER
                                    if (team == 1) {
                                        spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                                    } else {
                                        spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                                    }

                                    spielerPosX[spieler] = cx;
                                    spielerPosY[spieler] = cy;
                                    spielerPosX[i] = -1;
                                    spielerPosY[i] = -1;

                                    done = true;
                                }

                            }
                        }
                    }

                } else if (directions[j] == 4) {
                    //West
                    if (getWesten(spielfeld,x,y,false) == 'P' && done == false) {
                        //Check if Enemy
                        int cx = x-1;
                        int cy = y;
                        for (int i = 0; i < 8; i++) {
                            if (spielerPosX[i] == cx && spielerPosY[i] == cy) {
                                if ((team == 1 && i < 4) || (team == 2 && i >= 4)) {
                                    //Mein Team
                                } else {
                                    //GEGNER
                                    if (team == 1) {
                                        spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                                    } else {
                                        spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                                    }

                                    spielerPosX[spieler] = cx;
                                    spielerPosY[spieler] = cy;
                                    spielerPosX[i] = -1;
                                    spielerPosY[i] = -1;

                                    done = true;
                                }

                            }
                        }
                    }

                }
            }







            //if no enemy is around go ahead and check for enemy field
            for (int j = 0; j < directions.length; j++) {
                if (directions[j] == 1) {
                    //North
                    if (done == false && ((Simulationen.getNorden(spielfeld,x,y,false) == FARBE1 && team == 2) || (Simulationen.getNorden(spielfeld,x,y,false) == FARBE2 && team == 1))) {
                        //Attack

                        if (team == 1) {
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                        } else {
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                        }

                        spielerPosX[spieler] = x;
                        spielerPosY[spieler] = y-1;
                        spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = 'P';


                        done = true;
                    }
                } else if (directions[j] == 2) {
                    //East
                    if (done == false && ((getOsten(spielfeld,x,y,false) == FARBE1 && team == 2) || (getOsten(spielfeld,x,y,false) == FARBE2 && team == 1))) {
                        //Attack

                        if (team == 1) {
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                        } else {
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                        }

                        spielerPosX[spieler] = x+1;
                        spielerPosY[spieler] = y;
                        spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = 'P';


                        done = true;
                    }

                } else if (directions[j] == 3) {
                    //South
                    if (done == false && ((getSueden(spielfeld,x,y,false) == FARBE1 && team == 2) || (getSueden(spielfeld,x,y,false) == FARBE2 && team == 1))) {
                        //Attack

                        if (team == 1) {
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                        } else {
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                        }

                        spielerPosX[spieler] = x;
                        spielerPosY[spieler] = y+1;
                        spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = 'P';


                        done = true;
                    }

                } else if (directions[j] == 4) {
                    //West
                    if (done == false && ((getWesten(spielfeld,x,y,false) == FARBE1 && team == 2) || (getWesten(spielfeld,x,y,false) == FARBE2 && team == 1))) {
                        //Attack

                        if (team == 1) {
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                        } else {
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                        }

                        spielerPosX[spieler] = x-1;
                        spielerPosY[spieler] = y;
                        spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = 'P';

                        done = true;
                    }

                }
            }


            //if no enemy field is around go ahead and check for free field
            for (int j = 0; j < directions.length; j++) {
                if (directions[j] == 1) {
                    //North
                    if (done == false && Simulationen.getNorden(spielfeld,x,y,false) == ' ') {
                        //Attack

                        if (team == 1) {
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                        } else {
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                        }

                        spielerPosX[spieler] = x;
                        spielerPosY[spieler] = y-1;
                        spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = 'P';


                        done = true;
                    }
                } else if (directions[j] == 2) {
                    //East
                    if (done == false && getOsten(spielfeld,x,y,false) == ' ') {
                        //Attack

                        if (team == 1) {
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                        } else {
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                        }

                        spielerPosX[spieler] = x+1;
                        spielerPosY[spieler] = y;
                        spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = 'P';


                        done = true;
                    }
                } else if (directions[j] == 3) {
                    //South
                    if (done == false && getSueden(spielfeld,x,y,false) == ' ') {
                        //Attack

                        if (team == 1) {
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                        } else {
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                        }

                        spielerPosX[spieler] = x;
                        spielerPosY[spieler] = y+1;
                        spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = 'P';


                        done = true;
                    }
                } else if (directions[j] == 4) {
                    //West
                    if (done == false && getWesten(spielfeld,x,y,false) == ' ') {
                        //Attack

                        if (team == 1) {
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                        } else {
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                        }

                        spielerPosX[spieler] = x-1;
                        spielerPosY[spieler] = y;
                        spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = 'P';

                        done = true;
                    }
                }
            }




            //Random!
            if (done == false) {
                while (true) {
                    int dir = Zufall.zufallGanz(1,4);
                    if (dir == 1) {
                        if (Simulationen.getNorden(spielfeld,x,y,false) != WAND) {
                            if (team == 1) {
                                spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                            } else {
                                spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                            }

                            spielerPosX[spieler] = x;
                            spielerPosY[spieler] = y-1;
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = 'P';

                            done = true;
                            break;
                        }
                    } else if (dir == 2) {
                        if (getOsten(spielfeld,x,y,false) != WAND) {
                            if (team == 1) {
                                spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                            } else {
                                spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                            }

                            spielerPosX[spieler] = x+1;
                            spielerPosY[spieler] = y;
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = 'P';

                            done = true;
                            break;
                        }
                    } else if (dir == 3) {
                        if (getSueden(spielfeld,x,y,false) != WAND) {
                            if (team == 1) {
                                spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                            } else {
                                spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                            }

                            spielerPosX[spieler] = x;
                            spielerPosY[spieler] = y+1;
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = 'P';

                            done = true;
                            break;
                        }
                    } else {
                        if (getWesten(spielfeld,x,y,false) != WAND) {
                            if (team == 1) {
                                spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                            } else {
                                spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                            }

                            spielerPosX[spieler] = x-1;
                            spielerPosY[spieler] = y;
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = 'P';

                            done = true;
                            break;
                        }
                    }
                }

            }





        } else if (spieler%4 == 2 || spieler%4 == 3) {

            //Random!
            if (done == false) {
                while (true) {
                    int dir = Zufall.zufallGanz(1,4);
                    if (dir == 1) {
                        if (Simulationen.getNorden(spielfeld,x,y,false) != WAND) {
                            if (team == 1) {
                                spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                            } else {
                                spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                            }

                            spielerPosX[spieler] = x;
                            spielerPosY[spieler] = y-1;
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = 'P';

                            done = true;
                            break;
                        }
                    } else if (dir == 2) {
                        if (getOsten(spielfeld,x,y,false) != WAND) {
                            if (team == 1) {
                                spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                            } else {
                                spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                            }

                            spielerPosX[spieler] = x+1;
                            spielerPosY[spieler] = y;
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = 'P';

                            done = true;
                            break;
                        }
                    } else if (dir == 3) {
                        if (getSueden(spielfeld,x,y,false) != WAND) {
                            if (team == 1) {
                                spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                            } else {
                                spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                            }

                            spielerPosX[spieler] = x;
                            spielerPosY[spieler] = y+1;
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = 'P';

                            done = true;
                            break;
                        }
                    } else {
                        if (getWesten(spielfeld,x,y,false) != WAND) {
                            if (team == 1) {
                                spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE1;
                            } else {
                                spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = FARBE2;
                            }

                            spielerPosX[spieler] = x-1;
                            spielerPosY[spieler] = y;
                            spielfeld[spielerPosX[spieler]][spielerPosY[spieler]] = 'P';

                            done = true;
                            break;
                        }
                    }
                }

            }


        }
        /*
        else if (spieler%4 == 3) {
            //Suche nach Rand, Defensiv
        }

         */
    }

    public static void zugBene(int spieler) {
        if (spielerPosX[spieler] > -1) {
            char farbe = '7';

            char farbeG = '9';

            if (spieler > 3) {
                farbe = '9';
                farbeG = '7';
            }

            int x = spielerPosX[spieler];
            int y = spielerPosY[spieler];

            char[] sichtfeld = new char[12];

            sichtfeld[8] = Simulationen.getNorden(spielfeld, x, y - 1, false);
            sichtfeld[11] = Simulationen.getWesten(spielfeld, x - 1, y, false);
            sichtfeld[9] = Simulationen.getOsten(spielfeld, x + 1, y, false);
            sichtfeld[10] = Simulationen.getSueden(spielfeld, x, y + 1, false);


            sichtfeld[5] = Simulationen.getSuedOst(spielfeld, x, y, false);
            sichtfeld[0] = Simulationen.getNorden(spielfeld, x, y, false);
            sichtfeld[4] = Simulationen.getNordOst(spielfeld, x, y, false);
            sichtfeld[1] = Simulationen.getOsten(spielfeld, x, y, false);
            sichtfeld[3] = Simulationen.getWesten(spielfeld, x, y, false);
            sichtfeld[7] = Simulationen.getNordWest(spielfeld, x, y, false);
            sichtfeld[2] = Simulationen.getSueden(spielfeld, x, y, false);
            sichtfeld[6] = Simulationen.getSuedWest(spielfeld, x, y, false);

/*
        8
     7  0  4
  11 3  -  1  9
     6  2  5
        10
 */

            int richtung = zufallGanz(3);

/*
    0 -> oben
    1 -> unten
    2 -> links
    3 -> rechts
 */

            int kante = 0;

            if (sichtfeld[0] == farbe && sichtfeld[1] == farbe && sichtfeld[2] == farbe && sichtfeld[3] == farbe) {
                if (sichtfeld[4] == farbeG || sichtfeld[4] == ' ') {
                    richtung = 1;
                    kante = 1;
                }
                if (sichtfeld[5] == farbeG || sichtfeld[5] == ' ') {
                    richtung = 1;
                    kante = 2;
                }
                if (sichtfeld[6] == farbeG || sichtfeld[6] == ' ') {
                    richtung = 3;
                }
                if (sichtfeld[7] == farbeG || sichtfeld[7] == ' ') {
                    richtung = 3;
                    kante = 3;
                }
                if (sichtfeld[4] == farbe && sichtfeld[5] == farbe && sichtfeld[6] == farbe && sichtfeld[7] == farbe) {
                    if (sichtfeld[8] == ' ' || sichtfeld[8] == farbeG) {
                        richtung = 0;
                        kante = 1;
                    }
                    if (sichtfeld[9] == ' ' || sichtfeld[9] == farbeG) {
                        richtung = 1;
                        kante = 2;
                    }
                    if (sichtfeld[10] == ' ' || sichtfeld[10] == farbeG) {
                        richtung = 2;
                        kante = 3;
                    }
                    if (sichtfeld[11] == ' ' || sichtfeld[11] == farbeG) {
                        richtung = 3;
                        kante = 4;
                    }
                }
            }
            if (kante == 0) {
                if (sichtfeld[1] == farbe && sichtfeld[0] == farbe && sichtfeld[3] == farbe) {
                    richtung = 2;
                    kante = 1;
                } else if (sichtfeld[0] == farbe && sichtfeld[3] == farbe && sichtfeld[2] == farbe) {
                    richtung = 1;
                    kante = 2;
                } else if (sichtfeld[2] == farbe && sichtfeld[3] == farbe && sichtfeld[1] == farbe) {
                    richtung = 0;
                    kante = 3;
                } else if (sichtfeld[2] == farbe && sichtfeld[1] == farbe && sichtfeld[0] == farbe) {
                    richtung = 3;
                    kante = 4;
                }
            }
            if (kante == 0) {
                if (sichtfeld[1] == farbe && sichtfeld[0] == farbe) {
                    richtung = 2;
                    kante = 1;
                } else if (sichtfeld[0] == farbe && sichtfeld[3] == farbe) {
                    richtung = 1;
                    kante = 2;
                } else if (sichtfeld[2] == farbe && sichtfeld[3] == farbe) {
                    richtung = 0;
                    kante = 3;
                } else if (sichtfeld[2] == farbe && sichtfeld[1] == farbe) {
                    richtung = 3;
                    kante = 4;
                }
            }
            if (kante == 0) {
                if (sichtfeld[1] == farbe) {
                    richtung = 2;
                }
                if (sichtfeld[2] == farbe) {
                    richtung = 3;
                }
                if (sichtfeld[3] == farbe) {
                    richtung = 0;
                }
                if (sichtfeld[0] == farbe) {
                    richtung = 1;
                }
            }

/*
if (sichtfeld[0] == '8') {
    richtung = 2;
}
if (sichtfeld[1] == '8') {
    richtung = 3;
}
if (sichtfeld[2] == '8') {
    richtung = 0;
}
if (sichtfeld[3] == '8') {
    richtung = 1;
}*/

            if ((sichtfeld[0] == '8' && sichtfeld[2] != farbe) || (sichtfeld[1] == '8' && sichtfeld[3] == farbe)) {
                richtung = 2;
            }
            if ((sichtfeld[3] == '8' && sichtfeld[1] != farbe) || (sichtfeld[0] == '8' && sichtfeld[2] == farbe)) {
                richtung = 1;
            }
            if ((sichtfeld[1] == '8' && sichtfeld[3] != farbe) || (sichtfeld[2] == '8' && sichtfeld[0] == farbe)) {
                richtung = 3;
            }
            if ((sichtfeld[2] == '8' && sichtfeld[0] != farbe) || (sichtfeld[3] == '8' && sichtfeld[1] == farbe)) {
                richtung = 0;
            }

            if (sichtfeld[0] == farbe && sichtfeld[3] == farbe && sichtfeld[2] == farbe && sichtfeld[1] == '8') {
                richtung = 1;
            }
            if (sichtfeld[0] == farbe && sichtfeld[1] == farbe && sichtfeld[2] == farbe && sichtfeld[3] == '8') {
                richtung = 3;
            }
            if (sichtfeld[1] == farbe && sichtfeld[3] == farbe && sichtfeld[2] == farbe && sichtfeld[0] == '8') {
                richtung = 0;
            }
            if (sichtfeld[0] == farbe && sichtfeld[3] == farbe && sichtfeld[1] == farbe && sichtfeld[2] == '8') {
                richtung = 2;
            }

            boolean v = true;
            for (int i = 0; i < sichtfeld.length; i++) {
                if (sichtfeld[i] == ' ' || sichtfeld[i] == farbeG) {
                    v = false;
                    break;
                }
            }

            int zx = 3 * spielfeld.length / 4;

            if (spieler > 3) {
                zx /= 3;
            }

            if (v) {
                if (x < zx) {
                    richtung = 1;
                } else if (x > zx) {
                    richtung = 3;
                } else if (y < spielfeld[0].length / 4) {
                    richtung = 2;
                } else if (y > spielfeld[0].length / 4) {
                    richtung = 0;
                } else {
                    richtung = -1;
                }
            }

            for (int i = 0; i < 4; i++) {
                if (sichtfeld[i] == 'P') {
                    richtung = i;
                    int xK = x;
                    int yK = y;
                    switch (richtung) {
                        case 0:
                            yK--;
                            break;
                        case 1:
                            xK++;
                            break;
                        case 2:
                            yK++;
                            break;
                        case 3:
                            xK--;
                            break;
                    }
                    for (int j = 0; j < spielerPosX.length; j++) {
                        if (xK == spielerPosX[j] && yK == spielerPosY[j]) {
                            spielerPosX[j] = -1;
                            spielerPosY[j] = -1;
                        }
                    }
                    break;
                }
            }

            // spieler platzieren

            spielfeld[x][y] = farbe;
            switch (richtung) {
                case 0:
                    if (Simulationen.getNorden(spielfeld, x, y, false) != '8') {
                        y--;
                    } else {
                        y++;
                    }
                    break;
                case 2:
                    if (Simulationen.getSueden(spielfeld, x, y, false) != '8') {
                        y++;
                    } else {
                        y--;
                    }
                    break;
                case 3:
                    if (Simulationen.getWesten(spielfeld, x, y, false) != '8') {
                        x--;
                    } else {
                        x++;
                    }
                    break;
                case 1:
                    if (Simulationen.getOsten(spielfeld, x, y, false) != '8') {
                        x++;
                    } else {
                        x--;
                    }
                    break;
            }
            spielerPosX[spieler] = x;
            spielerPosY[spieler] = y;
            spielfeld[x][y] = 'P';
        }
    }

    public static void zugSimon(int sp) {
        if (spielerPosX[sp] > -1) {
            char[] sicht = sichtfeld(sp);
            int counter = 0;
            for (int i = 0; i < 4; i++) {
                if (spielerPosX[i] < spielerPosX[sp] && spielerPosX[i] > -1) {
                    counter++;
                }
            }
            if (counter <= 1) { //Defensive
                if (spielerPosX[sp] > 1) {
                    char[] charr = new char[]{'P', '9', ' ', '7'};
                    for (int i = 0; i < charr.length; i++) {
                        if (sicht[3] == charr[i]) {
                            if (!istTeammate(sp, spielerPosX[sp] - 1, spielerPosY[sp])) {
                                move(sp, 4);
                                i = charr.length;
                            }
                        } else if (sicht[2] == charr[i]) {
                            if (!istTeammate(sp, spielerPosX[sp], spielerPosY[sp] + 1)) {
                                move(sp, 3);
                                i = charr.length;
                            }
                        } else if (sicht[0] == charr[i]) {
                            if (!istTeammate(sp, spielerPosX[sp], spielerPosY[sp] - 1)) {
                                move(sp, 1);
                                i = charr.length;
                            }
                        } else if (sicht[1] == charr[i]) {
                            if (!istTeammate(sp, spielerPosX[sp] + 1, spielerPosY[sp])) {
                                move(sp, 2);
                                i = charr.length;
                            }
                        } else {
                            if (i == charr.length - 1) {
                                move(sp, zufallGanz(1,4));
                            }
                        }
                    }
                } else {
                    int min;
                    if (sp == 0) {
                        min = 1;
                    } else {
                        min = 0;
                    }
                    int dir;
                    for (int i = 0; i < 4; i++) {
                        if (spielerPosX[i] < spielerPosX[min] && i != sp) {
                            min = i;
                        }
                    }
                    int y;
                    if (spielerPosY[sp] < spielerPosY[min]) {
                        dir = 1;
                        y = -1;
                    } else {
                        dir = 3;
                        y = 1;
                    }
                    boolean c = true;
                    if (!istTeammate(sp,spielerPosX[sp], spielerPosY[sp] + y)) {
                        if (sicht[dir - 1] != '7' && sicht[dir - 1] != '8') {
                            move(sp, dir);
                            c = false;
                        }
                    }
                    char[] charr = new char[]{'P', '9', ' ', '7'};
                    if ((sicht[0] == '9' || sicht[0] == '8') && (sicht[1] == '9' || sicht[1] == '8') && sicht[2] == '9' && (sicht[3] == '9' || sicht[3] == '8')) {
                        move(sp, 2);
                        c = false;
                    }
                    if (c) {
                        for (int i = 0; i < charr.length; i++) {
                            if (sicht[dir - 1] == charr[i]) {
                                if (!istTeammate(sp, spielerPosX[sp], spielerPosY[sp] + y)) {
                                    move(sp, dir);
                                    i = charr.length;
                                }
                            } else if (sicht[1] == charr[i]) {
                                if (!istTeammate(sp, spielerPosX[sp] + 1, spielerPosY[sp])) {
                                    move(sp, 2);
                                    i = charr.length;
                                }
                            } else if (sicht[(dir - (2 * y)) - 1] == charr[i]) {
                                if (!istTeammate(sp, spielerPosX[sp], spielerPosY[sp] +y)) {
                                    move(sp, dir - (2 * y));
                                    i = charr.length;
                                }
                            } else if (sicht[3] == charr[i]) {
                                if (!istTeammate(sp, spielerPosX[sp] - 1, spielerPosY[sp])) {
                                    move(sp, 4);
                                    i = charr.length;
                                }
                            } else {
                                if (i == charr.length - 1) {
                                    move(sp, zufallGanz(1,4));
                                }
                            }
                        }
                    }
                }
            } else { //Offensive
                char[] charr = new char[]{'P', '9', ' ', '7'};
                for (int i = 0; i < charr.length; i++) {
                    if (sicht[1] == charr[i]) {
                        if (!istTeammate(sp, spielerPosX[sp] + 1, spielerPosY[sp])) {
                            move(sp, 2);
                            i = charr.length;
                        }
                    } else if (sicht[0] == charr[i]) {
                        if (!istTeammate(sp, spielerPosX[sp], spielerPosY[sp] - 1)) {
                            move(sp, 1);
                            i = 3;
                        }
                    } else if (sicht[2] == charr[i]) {
                        if (!istTeammate(sp, spielerPosX[sp], spielerPosY[sp] + 1)) {
                            move(sp, 3);
                            i = charr.length;
                        }
                    } else if (sicht[3] == charr[i]) {
                        if (!istTeammate(sp, spielerPosX[sp] - 1, spielerPosY[sp])) {
                            move(sp, 4);
                            i = charr.length;
                        }
                    } else {
                        if (i == charr.length - 1) {
                            move(sp, zufallGanz(1, 4));
                        }
                    }
                }
            }

        }
    }

    public static void zugTim(int spielerNum) {
        int zufall = 0;
        int x = 0;
        int y = 0;
        if (blatt14.Simulationen.getWesten(spielfeld, spielerPosX[spielerNum], spielerPosY[spielerNum], false) == '7' || blatt14.Simulationen.getWesten(spielfeld, spielerPosX[spielerNum], spielerPosY[spielerNum], false) == 'P') {
            x = spielerPosX[spielerNum];
            y = spielerPosY[spielerNum];
            spielerPosX[spielerNum]--;
        } else if (blatt14.Simulationen.getOsten(spielfeld, spielerPosX[spielerNum], spielerPosY[spielerNum], false) == '7' || blatt14.Simulationen.getOsten(spielfeld, spielerPosX[spielerNum], spielerPosY[spielerNum], false) == 'P') {
            x = spielerPosX[spielerNum];
            y = spielerPosY[spielerNum];
            spielerPosX[spielerNum]++;
        } else if (blatt14.Simulationen.getNorden(spielfeld, spielerPosX[spielerNum], spielerPosY[spielerNum], false) == '7' || blatt14.Simulationen.getNorden(spielfeld, spielerPosX[spielerNum], spielerPosY[spielerNum], false) == 'P') {
            x = spielerPosX[spielerNum];
            y = spielerPosY[spielerNum];
            spielerPosY[spielerNum]--;
        } else if (blatt14.Simulationen.getSueden(spielfeld, spielerPosX[spielerNum], spielerPosY[spielerNum], false) == '7' || blatt14.Simulationen.getSueden(spielfeld, spielerPosX[spielerNum], spielerPosY[spielerNum], false) == 'P') {
            x = spielerPosX[spielerNum];
            y = spielerPosY[spielerNum];
            spielerPosY[spielerNum]++;
        } else {
            if (spielerPosY[spielerNum] == 78 && spielerPosX[spielerNum] != 78) {
                x = spielerPosX[spielerNum];
                y = spielerPosY[spielerNum];
                spielerPosX[spielerNum]++;
            }
            zufall = Zufall.zufallGanz(1, 4);
            if (zufall == 3) {
                if (spielerPosX[spielerNum] == 78) {
                } else {
                    spielfeld[spielerPosX[spielerNum]][spielerPosY[spielerNum]] = '9';
                    spielerPosX[spielerNum] = spielerPosX[spielerNum] + 1;
                    spielfeld[spielerPosX[spielerNum]][spielerPosY[spielerNum]] = 'P';
                }
            }
            if (zufall == 2) {
                if (spielerPosY[spielerNum] == 78) {
                } else {
                    spielfeld[spielerPosX[spielerNum]][spielerPosY[spielerNum]] = '9';
                    spielerPosY[spielerNum] = spielerPosY[spielerNum] + 1;
                    spielfeld[spielerPosX[spielerNum]][spielerPosY[spielerNum]] = 'P';
                }
            }
            if (zufall == 1) {
                if (spielerPosX[spielerNum] == 1) {
                } else {
                    spielfeld[spielerPosX[spielerNum]][spielerPosY[spielerNum]] = '9';
                    spielerPosX[spielerNum] = spielerPosX[spielerNum] - 1;
                    spielfeld[spielerPosX[spielerNum]][spielerPosY[spielerNum]] = 'P';
                }
            }
            if (zufall == 4) {
                if (spielerPosY[spielerNum] == 1) {
                } else {
                    spielfeld[spielerPosX[spielerNum]][spielerPosY[spielerNum]] = '9';
                    spielerPosY[spielerNum] = spielerPosY[spielerNum] - 1;
                    spielfeld[spielerPosX[spielerNum]][spielerPosY[spielerNum]] = 'P';
                }
            }
        }

        spielfeld[x][y] = '9';
        spielfeld[spielerPosX[spielerNum]][spielerPosY[spielerNum]] = 'P';

    }

    public static void zugJan(int spielernum) {
        if ((spielerPosX[spielernum] != -1) && (spielerPosY[spielernum] != -1)) {
            //Angriff oder Verteidigung - Entscheidung
            int[] team = new int[4];
            if (spielernum < 4) {
                for (int i = 0; i < 4; i++) {
                    team[i] = spielerPosX[i];
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    team[i] = spielerPosX[i + 4];
                }
            }

            blatt11.BubbleSort.bubbleSort(team);

            int teamPos = 0;
            for (int i = 0; i < 4; i++) {
                if (team[i] == spielerPosX[spielernum]) {
                    teamPos = i;
                }
            }

            if (spielernum > 3) {
                spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]] = '9';
            } else {
                spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]] = '7';
            }

            char farbeGegner = '7';

            if (spielernum < 4) {
                farbeGegner = '9';
            }

            spielerBewegt = false;
            //Gegner angreifen, falls 1 Block entfernt:
            attack(spielernum);

            if(spielerBewegt==false) {


                // Counterstrike check
                int anzahlSchritt = schrittAnzahlOne;
                if(spielernum > 3){
                    anzahlSchritt = schrittAnzahlTwo;
                }
                if ((spielernum==0 || spielernum==4) && (anzahlSchritt < -10 && anzahlSchritt > -89)) {
                    if (spielerPosY[spielernum] > 2) {
                        spielerPosY[spielernum] -= 1;
                        spielerBewegt = true;
                    } else if (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]-1] == farbeGegner){
                        if(spielernum<4) {
//                            check[0][0] = true;
                            anzahlSchritt = -89;
                        } else{
//                            check[1][0] = true;
                            anzahlSchritt = -89;
                        }

                    } else {
                        anzahlSchritt = -89;
                    }
                    if(spielernum < 4){
                        schrittAnzahlOne = anzahlSchritt;
                    } else{
                        schrittAnzahlTwo = anzahlSchritt;
                    }
                }else if ((spielernum==0 || spielernum==4) && (anzahlSchritt < -88 && anzahlSchritt > -169)) {
                    if(spielerPosY[spielernum] < spielfeld[0].length-2) {
                        spielerPosY[spielernum] += 1;
                        spielerBewegt = true;
                    } else if( spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]+1] == farbeGegner){
                        if(spielernum<4) {
//                            check[0][1] = true;
                            anzahlSchritt = -169;
                        } else{
//                            check[1][1] = true;
                            anzahlSchritt = -169;
                        }
                    }
                    if(spielernum < 4){
                        schrittAnzahlOne = anzahlSchritt;
                    } else{
                        schrittAnzahlTwo = anzahlSchritt;
                    }
                    //Strike!
                    // |
                    // V
//                } else if((spielernum<2 && check[0][0] == true && check[0][1] == true) || (spielernum > 3 && spielernum < 6 && check[1][0] == true && check[1][1] == true)) {
//                    counterMove(spielernum,teamPos);
                }
                anzahlSchritt = schrittAnzahlOneV;
                if(spielernum > 3){
                    anzahlSchritt = schrittAnzahlTwoV;
                }
                if ((spielernum==1 || spielernum==5) && (anzahlSchritt < -10 && anzahlSchritt > -89)) {
                    if (spielerPosX[spielernum] > 2) {
                        spielerPosX[spielernum] -= 1;
                        spielerBewegt = true;
                    } else if (spielfeld[spielerPosX[spielernum]-1][spielerPosY[spielernum]] == farbeGegner){
                        if(spielernum<4) {
                            anzahlSchritt = -89;
                        } else{
                            anzahlSchritt = -89;
                        }

                    } else {
                        anzahlSchritt = -89;
                    }
                    if(spielernum < 4){
                        schrittAnzahlOneV = anzahlSchritt;
                    } else{
                        schrittAnzahlTwoV = anzahlSchritt;
                    }
                }else if ((spielernum==1 || spielernum==5) && (anzahlSchritt < -88 && anzahlSchritt > -169)) {
                    if (spielerPosX[spielernum] < spielfeld.length - 2) {
                        spielerPosX[spielernum] += 1;
                        spielerBewegt = true;
                    } else if (spielfeld[spielerPosX[spielernum]+1][spielerPosY[spielernum]] == farbeGegner) {
                        if (spielernum < 4) {
                            anzahlSchritt = -169;
                        } else {
                            anzahlSchritt = -169;
                        }
                    }
                    if (spielernum < 4) {
                        schrittAnzahlOneV = anzahlSchritt;
                    } else {
                        schrittAnzahlTwoV = anzahlSchritt;
                    }
                }else { //Normal!
                    follow(spielernum);
                }
                int felder = 0 ;
                if (spielerBewegt == false) {
                    gescannteFelder = 0;
                    bewegeSpielerO(spielernum, teamPos);
//
//                    if (spielerBewegt == false) {
//                        bewegeRaus(spielernum, teamPos);

                    if (spielerBewegt == false) {
                        bewegeSpielerR(spielernum, teamPos, felder+1);
                    }
//                    }
                }
            }
            spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]] = 'P';
        }

    }

    public static void zugTimo(int spielerID) {
        //Timos Spiellogic



        double bestScore = -100;
        int besteRichtung = -1;

        char gegnerfarbe = '9';
        char eigenefarbe = '7';
        char gegner = 'O';
        char kamerade = 'P';
        char wand = '8';

        int[][] bew = new int[4][2];

        //Oben
        bew[0][0] = spielerPosX[spielerID] - 1;
        bew[0][1] = spielerPosY[spielerID];
        //Rechts
        bew[1][0] = spielerPosX[spielerID];
        bew[1][1] = spielerPosY[spielerID] + 1;
        //Unten
        bew[2][0] = spielerPosX[spielerID] + 1;
        bew[2][1] = spielerPosY[spielerID];
        //Links
        bew[3][0] = spielerPosX[spielerID];
        bew[3][1] = spielerPosY[spielerID] - 1;


        // Für jede Richtung
        for (int i = 0; i < 4; i++) {
            int x = bew[i][0];
            int y = bew[i][1];
            if (!imFeld(x, y)) continue;
            if (spielfeld[bew[i][0]][bew[i][1]] != wand && spielfeld[bew[i][0]][bew[i][1]] != kamerade) { // Wände & Kameraden
                double score = 0;

                // Ausgewähltes Feld
                if (spielfeld[bew[i][0]][bew[i][1]] == ' ') {
                    score += 8;
                } else if (spielfeld[bew[i][0]][bew[i][1]] == gegnerfarbe) {
                    score += 5;
                } else if (spielfeld[bew[i][0]][bew[i][1]] == eigenefarbe) {
                    score -= 3;
                } else if (spielfeld[bew[i][0]][bew[i][1]] == gegner) {
                    score += 12;
                } else if (spielfeld[bew[i][0]][bew[i][1]] == kamerade) {
                    score -= 50;
                }

                // Nachbarschaft von dem Feld
                int[][] bewNachbar = new int[4][2];

                // Oben
                bewNachbar[0][0] = bew[i][0] - 1;
                bewNachbar[0][1] = bew[i][1];
                // Rechts
                bewNachbar[1][0] = bew[i][0];
                bewNachbar[1][1] = bew[i][1] + 1;
                // Unten
                bewNachbar[2][0] = bew[i][0] + 1;
                bewNachbar[2][1] = bew[i][1];
                // Links
                bewNachbar[3][0] = bew[i][0];
                bewNachbar[3][1] = bew[i][1] - 1;


                int anzahlgegner = 0;
                int gegnerfaelder = 0;
                int waende = 0;
                int kameraden = 0;

                for (int j = 0; j < 4; j++) {
                    if (spielfeld[bewNachbar[j][0]][bewNachbar[j][1]] == gegner) {
                        anzahlgegner++;
                    } else if (spielfeld[bewNachbar[j][0]][bewNachbar[j][1]] == gegnerfarbe) {
                        gegnerfaelder++;
                    } else if (spielfeld[bewNachbar[j][0]][bewNachbar[j][1]] == wand) {
                        waende++;
                    } else if (spielfeld[bewNachbar[j][0]][bewNachbar[j][1]] == kamerade) {
                        kameraden++;
                    }
                }

                if (anzahlgegner == 1) {
                    score += 5;
                } else if (anzahlgegner >= 2) {
                    score -= anzahlgegner * 5;
                }
                score += gegnerfaelder * 2;
                score -= waende * 3;
                score -= kameraden * 5;

                if (spielerID == 0) {

                } else {
                    if (i % spielerID == 0) {
                        score++;
                    }
                }


                //Anpassung an aktuellen Erfolg
                if ((double) zaehlen(1) / (zaehlen(0) + zaehlen(1) + zaehlen(2)) < 0.5) {
                    // Verlieren
                    score += gegnerfaelder; // agressiver
                } else {
                    // Gewinnen
                    score -= gegnerfaelder; // agressiver
                }
                if (score > bestScore) {
                    bestScore = score;
                    besteRichtung = i;
                }
            }
        }

        // Bewegung ausführen
        if (besteRichtung == -1) return;

        if (spielfeld[bew[besteRichtung][0]][bew[besteRichtung][1]] == gegner) {
            for (int i = 0; i < 8; i++) {
                if (spielerPosX[i] == bew[besteRichtung][0] && spielerPosY[i] == bew[besteRichtung][1]) {
                    spielfeld[spielerPosX[i]][spielerPosY[i]] = gegnerfarbe;
                    respawn(i);
                    darfZiehen[i] = false;
                }
            }
        }
        spielfeld[bew[besteRichtung][0]][bew[besteRichtung][1]] = kamerade;
        spielfeld[spielerPosX[spielerID]][spielerPosY[spielerID]] = eigenefarbe;
        spielerPosX[spielerID] = bew[besteRichtung][0];
        spielerPosY[spielerID] = bew[besteRichtung][1];

    }


    //Only Team 1
    public static void zugJulius(int spielernummer) {
        if (spielernummer < 4) {
            int x = spielerPosX[spielernummer];
            int y = spielerPosY[spielernummer];

            int random = Zufall.zufallGanz(0, 3);
            int neuX = x;
            int neuY = y;

            if (Simulationen.getWesten(spielfeld, x, y, false) == '9') {
                neuX--;
            } else if (Simulationen.getNorden(spielfeld, x, y, false) == '9') {
                neuY--;
            } else if (Simulationen.getSueden(spielfeld, x, y, false) == '9') {
                neuY++;
            } else if (Simulationen.getOsten(spielfeld, x, y, false) == '9') {
                neuX++;
            } else if (Simulationen.getOsten(spielfeld, x, y, false) == ' ') {
                neuX++;
            } else if (random == 0) {
                neuY++;
            } else if (random == 1) {
                neuX--;
            } else if (random == 2) {
                neuY--;
            } else {
                return;
            }

            // Prüfe ob neue Position gültig ist (keine Wand)
            if (spielfeld[neuX][neuY] == '8') {
                return;  // Zug gestrichen
            }



            // Führe Bewegung aus
            spielfeld[x][y] = FARBE1;
            spielfeld[neuX][neuY] = 'P';
            spielerPosX[spielernummer] = neuX;
            spielerPosY[spielernummer] = neuY;
        }
    }

    public static void zugKarol(int spielerNummer) {

            //Variablen

            //Feldwerte Gewichtung (Standard kann verändert werden bei Taktiken)
            int wand = 0;
            int mate = 0;
            int mateFarbe = 1;
            int gegnerFarbe = 4;
            int gegner = 5;
            int leer = 7;
            int unbekannt = 2;

            //Bewegungslogik
            int distanz = 1000;


            //Taktik Bonus rechnung
            if (flaechePushen) {
                leer += 2;
                gegnerFarbe += 6;
                gegner -= 2;
            }
            else if (gegnerJagen) {
                leer --;
                gegnerFarbe += 1;
                gegner += 3;
            }
            else if (search) { //Nicht fertig
                gegner ++;
                gegnerFarbe ++;
            }

    /*
    Übersetzungen:
    10 höchste Priorität
    0 Auslassen
     */
            int[][] sichtfeld = new int[5][5];

            int spielerX = spielerPosX[spielerNummer];
            int spielerY = spielerPosY[spielerNummer];

            int wert;

            //Erstellung von Heatmap und Sichtfeld
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {

                    int feldX = spielerX + i - 2;
                    int feldY = spielerY + j - 2;

                    if (feldX >= 0 && feldX < spielfeld.length &&
                            feldY >= 0 && feldY < spielfeld[0].length) {

                        char c = spielfeld[feldX][feldY];

                        // Mapping
                        if (c == '8') {          // Wand
                            wert = wand;
                        } else if (c == 'O') {   // Spieler Team O (Eigener Spieler)
                            wert = mate;
                        } else if (c == '9') {   // Farbe Team O
                            wert = mateFarbe;
                        } else if (c == 'P') {   // Spieler Team P (Gegnerischer Spieler)
                            wert = gegner;
                        } else if (c == '7') {   // Farbe Team P
                            wert = gegnerFarbe;
                        } else {                 // leer / unbekannt
                            wert = leer;
                        }
                        sichtfeld[i][j] = wert;

                        try {
                            teamHeatmap[feldX][feldY] = wert;
                        } catch (ArrayIndexOutOfBoundsException e) {

                        }


                    } else {
                        sichtfeld[i][j] = 0;
                    }
                }
            }


            if (flaechePushen) {
                //Lokale best places
                int[] lokaleFelderX = new int[25];
                int[] lokaleFelderY = new int[25];

                int counter = 0;

                int bestDist = Integer.MAX_VALUE;
                int bestX = -1;
                int bestY = -1;

                //Sichtfeld basiert
                for (int i = 0; i < sichtfeld.length; i++) {
                    for (int j = 0; j < sichtfeld[0].length; j++) {

                        int index = i * sichtfeld[0].length + j;

                        if (sichtfeld[i][j] == 10) {
                            int d = Math.abs(i - 2) + Math.abs(j - 2);

                            if (d < distanz && d > 0) {
                                distanz = d;

                                bestX = i - 2;
                                bestY = j - 2;

                                lokaleFelderX[index] = i - 2;
                                lokaleFelderY[index] = j - 2;

                                System.out.println("Gegner Feld");
                                counter++;
                            }
                        } else {
                            lokaleFelderX[index] = 0;
                            lokaleFelderY[index] = 0;
                        }
                    }
                }

                if (counter == 0) {
                    for (int i = 0; i < sichtfeld.length; i++) {
                        for (int j = 0; j < sichtfeld[0].length; j++) {

                            int index = i * sichtfeld[0].length + j;

                            if (sichtfeld[i][j] == 9) {
                                int d = Math.abs(i - 2) + Math.abs(j - 2);

                                if (d < distanz && d > 0) {
                                    distanz = d;

                                    bestX = i - 2;
                                    bestY = j - 2;

                                    lokaleFelderX[index] = i - 2;
                                    lokaleFelderY[index] = j - 2;

                                    System.out.println("Leeres Feld");
                                    counter++;
                                }
                            } else {
                                lokaleFelderX[index] = 0;
                                lokaleFelderY[index] = 0;
                            }
                        }
                    }
                }

                if (counter == 0) {
                    for (int i = 0; i < sichtfeld.length; i++) {
                        for (int j = 0; j < sichtfeld[0].length; j++) {

                            int index = i * sichtfeld[0].length + j;

                            if (sichtfeld[i][j] == 3) {
                                int d = Math.abs(i - 2) + Math.abs(j - 2);

                                if (d < distanz && d > 0) {
                                    distanz = d;

                                    bestX = i - 2;
                                    bestY = j - 2;

                                    lokaleFelderX[index] = i - 2;
                                    lokaleFelderY[index] = j - 2;

                                    System.out.println("Gegner");
                                    counter++;
                                }
                            } else {
                                lokaleFelderX[index] = 0;
                                lokaleFelderY[index] = 0;
                            }
                        }
                    }
                }

                //Teamheatmap Fallback
                if (counter == 0) {
                    int[] heatFelderX = new int[teamHeatmap.length * teamHeatmap[0].length];
                    int[] heatFelderY = new int[teamHeatmap.length * teamHeatmap[0].length];

                    for (int i = 0; i < teamHeatmap.length; i++) {
                        for (int j = 0; j < teamHeatmap[0].length; j++) {

                            int index = i * teamHeatmap[0].length + j;

                            if (teamHeatmap[i][j] == 9) {
                                int d = Math.abs(i) + Math.abs(j);

                                if (d < distanz && d > 0) {
                                    distanz = d;

                                    bestX = i;
                                    bestY = j;

                                    heatFelderX[index] = i;
                                    heatFelderY[index] = j;

                                    //System.out.println("Leeres Feld Teamheatmap");
                                    counter++;
                                }
                            } else {
                                heatFelderX[index] = 0;
                                heatFelderY[index] = 0;
                            }
                        }
                    }

                    if (counter == 0) {
                        for (int i = 0; i < teamHeatmap.length; i++) {
                            for (int j = 0; j < teamHeatmap[0].length; j++) {

                                int index = i * teamHeatmap[0].length + j;

                                if (teamHeatmap[i][j] == 6) {
                                    int d = Math.abs(i) + Math.abs(j);

                                    if (d < distanz && d > 0) {
                                        distanz = d;

                                        bestX = i;
                                        bestY = j;

                                        heatFelderY[index] = i;
                                        heatFelderY[index] = j;

                                        //System.out.println("Gegner Feld Teamheatmap");
                                        counter++;
                                    }
                                } else {
                                    heatFelderX[index] = 0;
                                    heatFelderY[index] = 0;
                                }
                            }
                        }
                    }
                    if (counter == 0) {
                        for (int i = 0; i < teamHeatmap.length; i++) {
                            for (int j = 0; j < teamHeatmap[0].length; j++) {

                                int index = i * teamHeatmap[0].length + j;

                                if (teamHeatmap[i][j] == 3) {
                                    int d = Math.abs(i) + Math.abs(j);

                                    if (d < distanz && d > 0) {
                                        distanz = d;

                                        bestX = i;
                                        bestY = j;

                                        heatFelderX[index] = i;
                                        heatFelderY[index] = j;

                                        //System.out.println("Gegner Teamheatmap");
                                        counter++;
                                    }
                                } else {
                                    heatFelderX[index] = 0;
                                    heatFelderY[index] = 0;
                                }
                            }
                        }
                    }

                    //Bewegung Teamheatmap art
                    if (bestX >= 0 && bestY >= 0) {
                        // Alte Position leeren
                        int altX = spielerPosX[spielerNummer];
                        int altY = spielerPosY[spielerNummer];
                        spielfeld[altX][altY] = '9';

                        if (spielfeld[bestX][bestY] == '8') {
                        }else {
                            // Spieler verschieben
                            spielerPosX[spielerNummer] = bestX;
                            spielerPosY[spielerNummer] = bestY;
                            spielfeld[bestX][bestY] = 'O';
                        }
                    } else {
                        //System.out.println("Kein gültiges Teamheatmap-Ziel gefunden, Bewegung übersprungen.");
                    }


                }
                //Normale Bewegung
                else{
                    if (spielfeld[spielerPosX[spielerNummer] + bestX][spielerPosY[spielerNummer] + bestY] == '8') {
                    }
                    else {
                        int altX = spielerPosX[spielerNummer];
                        int altY = spielerPosY[spielerNummer];

                        spielerPosX[spielerNummer] += bestX;
                        spielerPosY[spielerNummer] += bestY;

                        spielfeld[spielerPosX[spielerNummer]][spielerPosY[spielerNummer]] = 'O';

                        spielfeld[altX][altY] = '9';
                    }
                }

            }
            else if (gegnerJagen) {
                int[] lokaleFelderX = new int[25];
                int[] lokaleFelderY = new int[25];

                int counter = 0;

                int bestDist = Integer.MAX_VALUE;
                int bestX = -1;
                int bestY = -1;

                //Sichtfeld suche
                for (int i = 0; i < sichtfeld.length; i++) {
                    for (int j = 0; j < sichtfeld[0].length; j++) {

                        int index = i * sichtfeld[0].length + j;

                        if (sichtfeld[i][j] == 8) {
                            int d = Math.abs(i - 2) + Math.abs(j - 2);

                            if (d < distanz && d > 0) {
                                distanz = d;

                                bestX = i - 2;
                                bestY = j - 2;

                                lokaleFelderX[index] = i - 2;
                                lokaleFelderY[index] = j - 2;

                                //System.out.println("Gegner");
                                counter++;
                            }
                        } else {
                            lokaleFelderX[index] = 0;
                            lokaleFelderY[index] = 0;
                        }
                    }
                }

                if (counter == 0) {
                    for (int i = 0; i < sichtfeld.length; i++) {
                        for (int j = 0; j < sichtfeld[0].length; j++) {

                            int index = i * sichtfeld[0].length + j;

                            if (sichtfeld[i][j] == 5) {
                                int d = Math.abs(i - 2) + Math.abs(j - 2);

                                if (d < distanz && d > 0) {
                                    distanz = d;

                                    bestX = i - 2;
                                    bestY = j - 2;

                                    lokaleFelderX[index] = i - 2;
                                    lokaleFelderY[index] = j - 2;

                                    //System.out.println("Gegner Farbe");
                                    counter++;
                                }
                            } else {
                                lokaleFelderX[index] = 0;
                                lokaleFelderY[index] = 0;
                            }
                        }
                    }
                }

                //Teamheatmap Fallback
                if (counter == 0) {
                    int[] heatFelderX = new int[teamHeatmap.length * teamHeatmap[0].length];
                    int[] heatFelderY = new int[teamHeatmap.length * teamHeatmap[0].length];

                    for (int i = 0; i < teamHeatmap.length; i++) {
                        for (int j = 0; j < teamHeatmap[0].length; j++) {

                            int index = i * teamHeatmap[0].length + j;

                            if (teamHeatmap[i][j] == 8) {
                                int d = Math.abs(i) + Math.abs(j);

                                if (d < distanz && d > 0) {
                                    distanz = d;

                                    bestX = i;
                                    bestY = j;

                                    heatFelderX[index] = i;
                                    heatFelderY[index] = j;

                                    //System.out.println("Gegner Teamheatmap");
                                    counter++;
                                }
                            } else {
                                heatFelderX[index] = 0;
                                heatFelderY[index] = 0;
                            }
                        }
                    }

                    if (counter == 0) {
                        for (int i = 0; i < teamHeatmap.length; i++) {
                            for (int j = 0; j < teamHeatmap[0].length; j++) {

                                int index = i * teamHeatmap[0].length + j;

                                if (teamHeatmap[i][j] == 5) {
                                    int d = Math.abs(i) + Math.abs(j);

                                    if (d < distanz && d > 0) {
                                        distanz = d;

                                        bestX = i;
                                        bestY = j;

                                        heatFelderX[index] = i;
                                        heatFelderY[index] = j;

                                        //System.out.println("Gegner Farbe Teamheatmap");
                                        counter++;
                                    }
                                } else {
                                    heatFelderX[index] = 0;
                                    heatFelderY[index] = 0;
                                }
                            }
                        }
                    }

                    //Bewegung Teamheatmap art
                    if (bestX >= 0 && bestY >= 0) {
                        if (spielfeld[bestX][bestY] == '8') {

                        }
                        else {
                            // Alte Position leeren
                            int altX = spielerPosX[spielerNummer];
                            int altY = spielerPosY[spielerNummer];
                            spielfeld[altX][altY] = '9';

                            // Spieler verschieben
                            spielerPosX[spielerNummer] = bestX;
                            spielerPosY[spielerNummer] = bestY;
                            spielfeld[bestX][bestY] = 'O';
                        }
                    } else {
                        //System.out.println("Kein gültiges Teamheatmap-Ziel gefunden, Bewegung übersprungen.");
                    }


                }
                //Normale Bewegung nach Sichtfeld
                else{
                    if (spielfeld[spielerPosX[spielerNummer] + bestX][spielerPosY[spielerNummer] + bestY] == '8') {

                    }
                    int altX = spielerPosX[spielerNummer];
                    int altY = spielerPosY[spielerNummer];

                    spielerPosX[spielerNummer] += bestX;
                    spielerPosY[spielerNummer] += bestY;

                    spielfeld[spielerPosX[spielerNummer]][spielerPosY[spielerNummer]] = 'O';

                    spielfeld[altX][altY] = '9';
                }
            }

            else if (search) {
                int besterDist = Integer.MAX_VALUE;
                int moveX = 0;
                int moveY = 0;

                // Durchs 5x5-Sichtfeld schauen
                for (int i = -2; i <= 2; i++) {
                    for (int j = -2; j <= 2; j++) {
                        int x = spielerPosX[spielerNummer] + i;
                        int y = spielerPosY[spielerNummer] + j;

                        if (x >= 0 && x < teamHeatmap.length && y >= 0 && y < teamHeatmap[0].length) {
                            int wert3 = teamHeatmap[x][y];

                            // Suche nach nicht-eigenen Feldern
                            if (wert3 != 9 && wert3 != 6) {
                                int dist = Math.abs(i) + Math.abs(j); // Manhattan-Distanz
                                if (dist < besterDist && dist > 0) {
                                    besterDist = dist;
                                    moveX = i;
                                    moveY = j;
                                }
                            }
                        }
                    }
                }

                // Bewegung durchführen
                if (spielfeld[spielerPosX[spielerNummer] + moveX][spielerPosY[spielerNummer] + moveY] == '8') {
                }
                else {
                    int altX = spielerPosX[spielerNummer];
                    int altY = spielerPosY[spielerNummer];

                    spielerPosX[spielerNummer] += moveX;
                    spielerPosY[spielerNummer] += moveY;

                    spielfeld[spielerPosX[spielerNummer]][spielerPosY[spielerNummer]] = 'O';
                    spielfeld[altX][altY] = '9'; // vorheriges Feld wieder als eigene Farbe markieren
                }
            }

            //Taktik switchen
            int eigeneFelderHeat = 0;
            int gegnerFelderHeat = 0;
            int leereFelder = 0;

            for (int i = 0; i < breite; i++) {
                for (int j = 0; j < laenge; j++) {
                    try {
                        if (teamHeatmap[i][j] == 8 || teamHeatmap[i][j] == 5) {
                            gegnerFelderHeat ++;
                        }
                        else if (teamHeatmap[i][j] == 9 || teamHeatmap[i][j] == 6) {
                            eigeneFelderHeat ++;
                        }
                        else if (teamHeatmap[i][j] == 3) {}
                        else {
                            leereFelder ++;
                        }
                    } catch(ArrayIndexOutOfBoundsException e) {

                    }

                }
            }

            // Prozentwerte berechnen
            // Für jeden Spieler:
            int sichtRadius = 2; // 5x5 Sichtfeld
            int eigeneFelder = 0;
            int gegnerFelder = 0;
            int leerFelder = 0;

            int meinX = spielerPosX[spielerNummer];
            int meinY = spielerPosY[spielerNummer];

            for (int i = -sichtRadius; i <= sichtRadius; i++) {
                for (int j = -sichtRadius; j <= sichtRadius; j++) {
                    int x = meinX + i;
                    int y = meinY + j;

                    if (x >= 0 && x < teamHeatmap.length && y >= 0 && y < teamHeatmap[0].length) {
                        int wert2 = teamHeatmap[x][y];

                        if (wert2 == 9 || wert2 == 6) eigeneFelder++;
                        else if (wert2 == 8 || wert2 == 5 || wert2 == 3) gegnerFelder++;
                        else leerFelder++;
                    }
                }
            }

            int gesamt = eigeneFelder + gegnerFelder + leerFelder;

            double eigeneProzent = (eigeneFelder / (double) gesamt) * 100.0;
            double gegnerProzent = (gegnerFelder / (double) gesamt) * 100.0;
            double leereProzent = (leerFelder / (double) gesamt) * 100.0;

            System.out.println("Lokale Sichtfeldwerte:");
            System.out.println("Eigene: " + eigeneProzent + "%");
            System.out.println("Gegner: " + gegnerProzent + "%");
            System.out.println("Leer: " + leereProzent + "%");


            // Taktik basierend auf Anteilen
            if (eigeneProzent > 50) {
                search = true;
                flaechePushen = false;
                gegnerJagen = false;
                //System.out.println("SEARCH aktiv!");
                //System.out.print("\n\n\n\n\n\n");
                searchCount ++;
               // System.out.println("SC " +  searchCount);
            } else if (gegnerProzent > 20) {
                gegnerJagen = true;
                flaechePushen = false;
                search = false;
                //System.out.println("GEGNER JAGEN!");
                //System.out.print("\n\n\n\n\n\n");
                gegegnerJagenCount ++;
               // System.out.println("GC " +  gegegnerJagenCount);
            } else if (leereProzent > 50) {
                flaechePushen = true;
                gegnerJagen = false;
                search = false;
                //System.out.println("FLÄCHE PUSHEN!");
                //System.out.print("\n\n\n\n\n\n");
                flaechePushenCount ++;
               // System.out.println("FC" + flaechePushenCount);
            }



            if (flaechePushen) {
                //System.out.println("fläche pushen!");
            } else if (gegnerJagen) {
               // System.out.println("gegner jagen!");
            } else if (search) {
               // System.out.println("search!");
            }




    }

    //Magenta
    public static void zugEins(int spieler) {
        zugBene(spieler);
    }

    //Grün
    public static void zugZwei(int spieler) {
        zugJan(spieler);

    }

    static boolean imFeld(int x, int y) {
        if (x >= 0 && y >= 0 && x < spielfeld.length && y < spielfeld[0].length) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * bewegt Spieler zufällig; unterscheidet zwischen hinten und vorne
     * @param spielernum Spielernummer
     * @param teamPos Position im Team
     */
    public static void bewegeSpielerR(int spielernum,int teamPos, int felder) {
        if(felder > 5){
            return;
        }

        char farbeTeam = '9';

        if (spielernum < 4) {
            farbeTeam = '7';
        }

        int richtung = (int)(Math.random()*6);

        if(spielernum<4) {
            if (teamPos < 2) { // Angriff
                // Random Schritte: (Manipuliert um eher nach links/rechts zu gehen)

                if (richtung == 0 || richtung == 4) { // Schritt nach rechts
                    if ((spielerPosX[spielernum] < spielfeld.length - 2) && (spielfeld[spielerPosX[spielernum]+1][spielerPosY[spielernum]] != 'P')) {
                        spielerPosX[spielernum]++;
                    }else { // Falls Spieler in Wand laufen würde, wird er jetzt erneut berechnet
                        bewegeSpielerR(spielernum, teamPos, felder+1);
                    }

                } else if ((richtung == 1 || richtung == 5) && (spielfeld[spielerPosX[spielernum]-1][spielerPosY[spielernum]] != 'P')) { // Schritt nach links
                    if (spielerPosX[spielernum] > 1 && (spielfeld[spielerPosX[spielernum]+1][spielerPosY[spielernum]] != 'P')) {
                        spielerPosX[spielernum]--;
                    }else { // Falls Spieler in Wand laufen würde, wird er jetzt erneut berechnet
                        bewegeSpielerR(spielernum, teamPos, felder+1);
                    }

                } else if (richtung == 2) { // Schritt nach oben
                    if (spielerPosY[spielernum] > 1 && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]-1] != 'P')) {
                        spielerPosY[spielernum]--;
                    }else { // Falls Spieler in Wand laufen würde, wird er jetzt erneut berechnet
                        bewegeSpielerR(spielernum, teamPos, felder+1);
                    }

                } else if (richtung == 3) { // Schritt nach unten
                    if (spielerPosY[spielernum] < spielfeld[0].length - 2 && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]+1] != 'P')) {
                        spielerPosY[spielernum]++;
                    }else { // Falls Spieler in Wand laufen würde, wird er jetzt erneut berechnet
                        bewegeSpielerR(spielernum, teamPos, felder+1);
                    }
                }
            } else { //Verteidigung?!
                // Random Schritte: (manipuliert um eher nach oben/unten zu gehen)

                if (richtung == 0) { // Schritt nach rechts
                    if (spielerPosX[spielernum] < spielfeld.length - 2 && (spielfeld[spielerPosX[spielernum]+1][spielerPosY[spielernum]] != 'P')) {
                        spielerPosX[spielernum]++;
                    }else { // Falls Spieler in Wand laufen würde, wird er jetzt erneut berechnet
                        bewegeSpielerR(spielernum, teamPos, felder+1);
                    }

                } else if (richtung == 1) { // Schritt nach links
                    if (spielerPosX[spielernum] > 1 && (spielfeld[spielerPosX[spielernum]-1][spielerPosY[spielernum]] != 'P')) {
                        spielerPosX[spielernum]--;
                    }else { // Falls Spieler in Wand laufen würde, wird er jetzt erneut berechnet
                        bewegeSpielerR(spielernum, teamPos, felder+1);
                    }

                } else if ((richtung == 2 || richtung == 4)) { // Schritt nach oben
                    if (spielerPosY[spielernum] > 1 && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]-1] != 'P')) {
                        spielerPosY[spielernum]--;
                    }else { // Falls Spieler in Wand laufen würde, wird er jetzt erneut berechnet
                        bewegeSpielerR(spielernum, teamPos, felder+1);
                    }

                } else if ((richtung == 3 || richtung == 5)) { // Schritt nach unten
                    if (spielerPosY[spielernum] < spielfeld[0].length - 2 && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]+1] != 'P')) {
                        spielerPosY[spielernum]++;
                    }else { // Falls Spieler in Wand laufen würde, wird er jetzt erneut berechnet
                        bewegeSpielerR(spielernum, teamPos, felder+1);
                    }

                }
            }
        } else{
            if (teamPos > 1) { // Angriff
                // Random Schritte: (Manipuliert um eher nach links/rechts zu gehen)

                if ((richtung == 0 || richtung == 4)) { // Schritt nach rechts
                    if (spielerPosX[spielernum] < spielfeld.length - 2 && (spielfeld[spielerPosX[spielernum]+1][spielerPosY[spielernum]] != 'P')) {
                        spielerPosX[spielernum]++;
                    }else { // Falls Spieler in Wand laufen würde, wird er jetzt erneut berechnet
                        bewegeSpielerR(spielernum, teamPos, felder+1);
                    }

                } else if ((richtung == 1 || richtung == 5)) { // Schritt nach links
                    if (spielerPosX[spielernum] > 1 && (spielfeld[spielerPosX[spielernum]-1][spielerPosY[spielernum]] != 'P')) {
                        spielerPosX[spielernum]--;
                    }else { // Falls Spieler in Wand laufen würde, wird er jetzt erneut berechnet
                        bewegeSpielerR(spielernum, teamPos, felder+1);
                    }

                } else if ((richtung == 2)) { // Schritt nach oben
                    if (spielerPosY[spielernum] > 1 && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]-1] != 'P')) {
                        spielerPosY[spielernum]--;
                    }else { // Falls Spieler in Wand laufen würde, wird er jetzt erneut berechnet
                        bewegeSpielerR(spielernum, teamPos, felder+1);
                    }

                } else if ((richtung == 3)) { // Schritt nach unten
                    if (spielerPosY[spielernum] < spielfeld[0].length - 2 && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]+1] != 'P')) {
                        spielerPosY[spielernum]++;
                    }else { // Falls Spieler in Wand laufen würde, wird er jetzt erneut berechnet
                        bewegeSpielerR(spielernum, teamPos, felder+1);
                    }
                }

            } else { //Verteidigung?!
                // Random Schritte: (manipuliert um eher nach oben/unten zu gehen)

                if ((richtung == 0) && (spielfeld[spielerPosX[spielernum]+1][spielerPosY[spielernum]] != 'P')) { // Schritt nach rechts
                    if (spielerPosX[spielernum] < spielfeld.length - 2) {
                        spielerPosX[spielernum]++;
                    }else { // Falls Spieler in Wand laufen würde, wird er jetzt erneut berechnet
                        bewegeSpielerR(spielernum, teamPos, felder+1);
                    }

                } else if ((richtung == 1) && (spielfeld[spielerPosX[spielernum]-1][spielerPosY[spielernum]] != 'P')) { // Schritt nach links
                    if (spielerPosX[spielernum] > 1) {
                        spielerPosX[spielernum]--;
                    }else { // Falls Spieler in Wand laufen würde, wird er jetzt erneut berechnet
                        bewegeSpielerR(spielernum, teamPos, felder+1);
                    }

                } else if ((richtung == 2 || richtung == 4) && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]-1] != 'P')) { // Schritt nach oben
                    if (spielerPosY[spielernum] > 1) {
                        spielerPosY[spielernum]--;
                    }else { // Falls Spieler in Wand laufen würde, wird er jetzt erneut berechnet
                        bewegeSpielerR(spielernum, teamPos, felder+1);
                    }

                } else if ((richtung == 3 || richtung == 5) && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]+1] != 'P')) { // Schritt nach unten
                    if (spielerPosY[spielernum] < spielfeld[0].length - 2) {
                        spielerPosY[spielernum]++;
                    }else { // Falls Spieler in Wand laufen würde, wird er jetzt erneut berechnet
                        bewegeSpielerR(spielernum, teamPos, felder+1);
                    }

                }
            }
        }
    }

    /**
     * bewegt Spieler zufällig auf ein nicht selbst eingefärbtes Feld; unterscheidet zwischen hinten und vorne
     * @param spielernum Spielernummer
     * @param teamPos Position im Team
     */
    public static void bewegeSpielerO(int spielernum,int teamPos) {
        if(gescannteFelder>=5){
            return;
        }

        char farbeTeam = '9';

        if (spielernum < 4) {
            farbeTeam = '7';
        }
        int richtung = (int) (Math.random() * 6);

        if (teamPos > 1) { // Angriff
            // Random Schritte: (Manipuliert um eher nach links/rechts zu gehen)

            if (richtung == 0 || richtung == 4) { // Schritt nach rechts
                if (spielerPosX[spielernum] < spielfeld.length - 2 && (spielfeld[spielerPosX[spielernum]+1][spielerPosY[spielernum]] != 'P') && (spielfeld[spielerPosX[spielernum]+1][spielerPosY[spielernum]] != farbeTeam)) {
                    spielerPosX[spielernum]++;
                    spielerBewegt = true;
                }else { // Falls Spieler in Wand laufen würde oder ein eigenes Feld erneut einfärben würde, wird er jetzt erneut berechnet
                    gescannteFelder++;
                    bewegeSpielerO(spielernum, teamPos);
                }

            } else if (richtung == 1  || richtung == 5) { // Schritt nach links
                if (spielerPosX[spielernum] > 1 && (spielfeld[spielerPosX[spielernum]-1][spielerPosY[spielernum]] != 'P') && (spielfeld[spielerPosX[spielernum]-1][spielerPosY[spielernum]] != farbeTeam)) {
                    spielerPosX[spielernum]--;
                    spielerBewegt = true;
                }else { // Falls Spieler in Wand laufen würde oder ein eigenes Feld erneut einfärben würde, wird er jetzt erneut berechnet
                    gescannteFelder++;
                    bewegeSpielerO(spielernum, teamPos);
                }

            } else if (richtung == 2) { // Schritt nach oben
                if (spielerPosY[spielernum] > 1 && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]-1] != 'P') && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]-1] != farbeTeam)) {
                    spielerPosY[spielernum]--;
                    spielerBewegt = true;
                }else { // Falls Spieler in Wand laufen würde oder ein eigenes Feld erneut einfärben würde, wird er jetzt erneut berechnet
                    gescannteFelder++;
                    bewegeSpielerO(spielernum, teamPos);
                }

            } else if (richtung == 3) { // Schritt nach unten
                if (spielerPosY[spielernum] < spielfeld[0].length - 2 && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]+1] != 'P') && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]+1] != farbeTeam)) {
                    spielerPosY[spielernum]++;
                    spielerBewegt = true;
                }else { // Falls Spieler in Wand laufen würde oder ein eigenes Feld erneut einfärben würde, wird er jetzt erneut berechnet
                    gescannteFelder++;
                    bewegeSpielerO(spielernum, teamPos);
                }
            }
        } else { //Verteidigung?!
            // Random Schritte: (manipuliert um eher nach oben/unten zu gehen)

            if (richtung == 0) { // Schritt nach rechts
                if (spielerPosX[spielernum] < spielfeld.length - 2 && (spielfeld[spielerPosX[spielernum]+1][spielerPosY[spielernum]] != 'P') && (spielfeld[spielerPosX[spielernum]+1][spielerPosY[spielernum]] != farbeTeam)) {
                    spielerPosX[spielernum]++;
                    spielerBewegt = true;
                } else { // Falls Spieler in Wand laufen würde oder ein eigenes Feld erneut einfärben würde, wird er jetzt erneut berechnet
                    gescannteFelder++;
                    bewegeSpielerO(spielernum, teamPos);
                }

            } else if (richtung == 1) { // Schritt nach links
                if (spielerPosX[spielernum] > 1 && (spielfeld[spielerPosX[spielernum]-1][spielerPosY[spielernum]] != 'P') && (spielfeld[spielerPosX[spielernum]-1][spielerPosY[spielernum]] != farbeTeam)) {
                    spielerPosX[spielernum]--;
                    spielerBewegt = true;
                }else { // Falls Spieler in Wand laufen würde oder ein eigenes Feld erneut einfärben würde, wird er jetzt erneut berechnet
                    gescannteFelder++;
                    bewegeSpielerO(spielernum, teamPos);
                }

            } else if (richtung == 2 || richtung == 4) { // Schritt nach oben
                if (spielerPosY[spielernum] > 1 && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]-1] != 'P') && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]-1] != farbeTeam)) {
                    spielerPosY[spielernum]--;
                    spielerBewegt = true;
                }else { // Falls Spieler in Wand laufen würde oder ein eigenes Feld erneut einfärben würde, wird er jetzt erneut berechnet
                    gescannteFelder++;
                    bewegeSpielerO(spielernum, teamPos);
                }

            } else if (richtung == 3 || richtung == 5) { // Schritt nach unten
                if (spielerPosY[spielernum] < spielfeld[0].length - 2 && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]+1] != 'P') && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]+1] != farbeTeam)) {
                    spielerPosY[spielernum]++;
                    spielerBewegt = true;
                }else {  // Falls Spieler in Wand laufen würde oder ein eigenes Feld erneut einfärben würde, wird er jetzt erneut berechnet
                    gescannteFelder++;
                    bewegeSpielerO(spielernum, teamPos);
                }

            }
        }
    }

    public static void bewegeRaus(int spielernum, int teamPos){
        char farbeTeam = '9';

        if (spielernum < 4) {
            farbeTeam = '7';
        }

        int richtung = richtungLast[spielernum];
        if ((spielfeld[spielerPosX[spielernum]+1][spielerPosY[spielernum]] == farbeTeam) && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]-1] == farbeTeam) && (spielfeld[spielerPosX[spielernum]-1][spielerPosY[spielernum]] == farbeTeam) && (spielfeld[spielerPosX[spielernum]][spielerPosY[spielernum]+1] == farbeTeam)) {
            int disX = (spielfeld.length/2-spielerPosX[spielernum]);
            int disY = (spielfeld[0].length/2-spielerPosY[spielernum]);
            if(Math.abs(disX) > Math.abs(disY)){
                if(disX > 0){
                    richtung = 0;
                } else{
                    richtung = 2;
                }
            } else{
                if(disY > 0){
                    richtung = 3;
                } else{
                    richtung = 1;
                }
            }
        }
        if(spielernum<4) {
            if (teamPos < 2) { // Angriff
                // Random Schritte: (Manipuliert um eher nach links/rechts zu gehen)

                if (richtung == 0 || richtung == 4) { // Schritt nach rechts
                    if (spielerPosX[spielernum] < spielfeld.length - 2) {
                        spielerPosX[spielernum]++;
                        spielerBewegt = true;
                    }

                } else if (richtung == 1 || richtung == 5) { // Schritt nach links
                    if (spielerPosX[spielernum] > 1) {
                        spielerPosX[spielernum]--;
                        spielerBewegt = true;
                    }

                } else if (richtung == 2) { // Schritt nach oben
                    if (spielerPosY[spielernum] > 1) {
                        spielerPosY[spielernum]--;
                        spielerBewegt = true;
                    }

                } else if (richtung == 3) { // Schritt nach unten
                    if (spielerPosY[spielernum] < spielfeld[0].length - 2) {
                        spielerPosY[spielernum]++;
                        spielerBewegt = true;
                    }
                }
            } else { //Verteidigung?!
                // Random Schritte: (manipuliert um eher nach oben/unten zu gehen)

                if (richtung == 0) { // Schritt nach rechts
                    if (spielerPosX[spielernum] < spielfeld.length - 2) {
                        spielerPosX[spielernum]++;
                        spielerBewegt = true;
                    }

                } else if (richtung == 1) { // Schritt nach links
                    if (spielerPosX[spielernum] > 1) {
                        spielerPosX[spielernum]--;
                        spielerBewegt = true;
                    }

                } else if (richtung == 2 || richtung == 4) { // Schritt nach oben
                    if (spielerPosY[spielernum] > 1) {
                        spielerPosY[spielernum]--;
                        spielerBewegt = true;
                    }

                } else if (richtung == 3 || richtung == 5) { // Schritt nach unten
                    if (spielerPosY[spielernum] < spielfeld[0].length - 2) {
                        spielerPosY[spielernum]++;
                        spielerBewegt = true;
                    }

                }
            }
        } else{
            if (teamPos > 1) { // Angriff
                // Random Schritte: (Manipuliert um eher nach links/rechts zu gehen)

                if (richtung == 0 || richtung == 4) { // Schritt nach rechts
                    if (spielerPosX[spielernum] < spielfeld.length - 2) {
                        spielerPosX[spielernum]++;
                        spielerBewegt = true;
                    }

                } else if (richtung == 1 || richtung == 5) { // Schritt nach links
                    if (spielerPosX[spielernum] > 1) {
                        spielerPosX[spielernum]--;
                        spielerBewegt = true;
                    }

                } else if (richtung == 2) { // Schritt nach oben
                    if (spielerPosY[spielernum] > 1) {
                        spielerPosY[spielernum]--;
                        spielerBewegt = true;
                    }

                } else if (richtung == 3) { // Schritt nach unten
                    if (spielerPosY[spielernum] < spielfeld[0].length - 2) {
                        spielerPosY[spielernum]++;
                        spielerBewegt = true;
                    }
                }

            } else { //Verteidigung?!
                // Random Schritte: (manipuliert um eher nach oben/unten zu gehen)

                if (richtung == 0) { // Schritt nach rechts
                    if (spielerPosX[spielernum] < spielfeld.length - 2) {
                        spielerPosX[spielernum]++;
                        spielerBewegt = true;
                    }

                } else if (richtung == 1) { // Schritt nach links
                    if (spielerPosX[spielernum] > 1) {
                        spielerPosX[spielernum]--;
                        spielerBewegt = true;
                    }

                } else if (richtung == 2 || richtung == 4) { // Schritt nach oben
                    if (spielerPosY[spielernum] > 1) {
                        spielerPosY[spielernum]--;
                        spielerBewegt = true;
                    }

                } else if (richtung == 3 || richtung == 5) { // Schritt nach unten
                    if (spielerPosY[spielernum] < spielfeld[0].length - 2) {
                        spielerPosY[spielernum]++;
                        spielerBewegt = true;
                    }

                }
            }
        }
    }

    /**
     * Besigt andere Spieler, falls diese 1 fällt entfernt sind
     * @param spielernum Spielernummer
     */
    public static void attack (int spielernum) {

        char[] umgebung = scanneUmgebung(spielernum);

        /*Umgebung:
               0
           1   2   3
       4   5  6(P) 7   8
           9   10  11
               12
         */
        if (Simulationen.zaehlenVier(spielfeld, spielerPosX[spielernum], spielerPosY[spielernum], 'P', false) > 0) {

            int spielernum2 = spielernum;
            //Finde spieler in der Nähe

            for (int i = 0; i < 8; i++) {

                if (spielerPosX[spielernum] == spielerPosX[i] && (spielerPosY[spielernum]-1) == spielerPosY[i]) {
                    spielernum2 = i;
                    if (umgebung[2] == 'P') { //N
                        if (spielernum < 4) {
                            if (spielernum2 > 3) {
                                spielerPosY[spielernum]--;
                                spielerPosX[spielernum2] = -1;
                                spielerPosY[spielernum2] = -1;
                                spielerBewegt = true;
                                return;
                            }
                        } else {
                            if (spielernum2 < 4) {
                                spielerPosY[spielernum]--;
                                spielerPosX[spielernum2] = -1;
                                spielerPosY[spielernum2] = -1;
                                spielerBewegt = true;
                                return;
                            }
                        }
                    }
                }

                if ((spielerPosX[spielernum]-1) == spielerPosX[i] && spielerPosY[spielernum] == spielerPosY[i]) {
                    spielernum2 = i;
                    if (umgebung[5] == 'P') { //W
                        if (spielernum < 4) {
                            if (spielernum2 > 3) {
                                spielerPosX[spielernum]--;
                                spielerPosX[spielernum2] = -1;
                                spielerPosY[spielernum2] = -1;
                                spielerBewegt = true;
                                return;
                            }
                        } else {
                            if (spielernum2 < 4) {
                                spielerPosX[spielernum]--;
                                spielerPosX[spielernum2] = -1;
                                spielerPosY[spielernum2] = -1;
                                spielerBewegt = true;
                                return;
                            }
                        }
                    }
                }

                if ((spielerPosX[spielernum]+1) == spielerPosX[i] && spielerPosY[spielernum] == spielerPosY[i]) {
                    spielernum2 = i;
                    if (umgebung[7] == 'P') { //O

                        if (spielernum < 4) {
                            if (spielernum2 > 3) {
                                spielerPosX[spielernum]++;
                                spielerPosX[spielernum2] = -1;
                                spielerPosY[spielernum2] = -1;
                                spielerBewegt = true;
                                return;
                            }
                        } else {
                            if (spielernum2 < 4) {
                                spielerPosX[spielernum]++;
                                spielerPosX[spielernum2] = -1;
                                spielerPosY[spielernum2] = -1;
                                spielerBewegt = true;
                                return;
                            }
                        }
                    }
                }

                if (spielerPosX[spielernum] == spielerPosX[i] && (spielerPosY[spielernum]+1) == spielerPosY[i]) {
                    spielernum2 = i;
                    if (umgebung[10] == 'P') { //S

                        if (spielernum < 4) {
                            if (spielernum2 > 3) {
                                spielerPosY[spielernum]++;
                                spielerPosX[spielernum2] = -1;
                                spielerPosY[spielernum2] = -1;
                                spielerBewegt = true;
                            }
                        } else {
                            if (spielernum2 < 4) {
                                spielerPosY[spielernum]++;
                                spielerPosX[spielernum2] = -1;
                                spielerPosY[spielernum2] = -1;
                                spielerBewegt = true;
                            }
                        }
                    }
                }
            }

        }
    }

    /**
     * Verfolgt die Spur des gegnerischen Teams, falls es auf dieses stößt
     * @param spielernum Spielernummer
     */
    public static void follow(int spielernum){
        char farbeGegner = '7';

        if (spielernum < 4) {
            farbeGegner = '9';
        }

        char[] umgebung = scanneUmgebung(spielernum);
                /*Umgebung:
               0
           1   2   3
       4   5  6(P) 7   8
           9   10  11
               12
         */
        if(umgebung[2] == farbeGegner){
            if(umgebung[0] != 'P' && umgebung[1] !='P' && umgebung[3] != 'P'){
                spielerPosY[spielernum] -= 1;
                spielerBewegt = true;
                return;
            }
        }
        if(umgebung[5] == farbeGegner){
            if(umgebung[4] != 'P' && umgebung[1] !='P' && umgebung[9] != 'P'){
                spielerPosX[spielernum] -= 1;
                spielerBewegt = true;
                return;
            }
        }
        if(umgebung[7] == farbeGegner){
            if(umgebung[8] != 'P' && umgebung[3] !='P' && umgebung[11] != 'P'){
                spielerPosX[spielernum] +=1;
                spielerBewegt = true;
                return;
            }
        }
        if(umgebung[10] == farbeGegner){
            if(umgebung[9] != 'P' && umgebung[12] !='P' && umgebung[11] != 'P'){
                spielerPosY[spielernum] +=1;
                spielerBewegt = true;
            }
        }
    }


    /**
     * Gibt die Umgebung um einen Spieler zurück
     * @param spielernum Nummer des Spielers
     * @return Liste der Elemente
     */
    public static char[] scanneUmgebung(int spielernum){
        int[] pos = new int[]{spielerPosX[spielernum],spielerPosY[spielernum]};
        int[] posN = new int[]{spielerPosX[spielernum],spielerPosY[spielernum]-1};
        int[] posS = new int[]{spielerPosX[spielernum],spielerPosY[spielernum]+1};
        int[] posW = new int[]{spielerPosX[spielernum]-1,spielerPosY[spielernum]};
        int[] posO = new int[]{spielerPosX[spielernum]+1,spielerPosY[spielernum]};

        char[] umgebung = new char[13];
        umgebung[0] = Simulationen.getNorden(spielfeld,spielerPosX[spielernum],spielerPosY[spielernum]-1,false);     //0
        umgebung[1] = Simulationen.getNordWest(spielfeld,spielerPosX[spielernum],spielerPosY[spielernum],false);        //1
        umgebung[2] = Simulationen.getNorden(spielfeld,spielerPosX[spielernum],spielerPosY[spielernum],false);          //2
        umgebung[3] = Simulationen.getNordOst(spielfeld,spielerPosX[spielernum],spielerPosY[spielernum],false);         //3
        umgebung[4] = Simulationen.getWesten(spielfeld,spielerPosX[spielernum]-1,spielerPosY[spielernum],false);     //4
        umgebung[5] = Simulationen.getWesten(spielfeld,spielerPosX[spielernum],spielerPosY[spielernum],false);          //5
        umgebung[6] = 'P';                                                                                                   //6
        umgebung[7] = Simulationen.getOsten(spielfeld,spielerPosX[spielernum],spielerPosY[spielernum],false);           //7
        umgebung[8] = Simulationen.getOsten(spielfeld,spielerPosX[spielernum]+1,spielerPosY[spielernum],false);      //8
        umgebung[9] = Simulationen.getSuedWest(spielfeld,spielerPosX[spielernum],spielerPosY[spielernum],false);        //9
        umgebung[10] = Simulationen.getSueden(spielfeld,spielerPosX[spielernum],spielerPosY[spielernum],false);         //10
        umgebung[11] = Simulationen.getSuedOst(spielfeld,spielerPosX[spielernum],spielerPosY[spielernum],false);        //11
        umgebung[12] = Simulationen.getSueden(spielfeld,spielerPosX[spielernum],spielerPosY[spielernum]+1,false);    //12

    /*Umgebung:
           0
       1   2   3
   4   5  6(P) 7   8
       9   10  11
           12
     */
        return umgebung;
    }

    public static void move(int sp, int r) {
        if (spielerPosX[sp] > -1 && spielerPosY[sp] > -1) {
            char[] f = new char[]{'7', '7', '7', '7', '9', '9', '9', '9'};
            spielfeld[spielerPosX[sp]][spielerPosY[sp]] = f[sp];
            if (r == 1 && getNorden(spielfeld, spielerPosX[sp], spielerPosY[sp], false) != '8') {
                if (getNorden(spielfeld, spielerPosX[sp], spielerPosY[sp], false) == 'P') {
                    int[] team = new int[]{1, 1, 1, 1, 2, 2, 2, 2};
                    for (int i = 0; i < spielerPosX.length; i++) {
                        if (spielerPosX[i] == spielerPosX[sp] && spielerPosY[i] == spielerPosY[sp] - 1) {
                            if (team[i] != team[sp]) {
                                spielerPosX[i] = -1;
                                spielerPosY[i] = -1;
                                spielerPosY[sp]--;
                            }
                        }
                    }
                } else {
                    spielerPosY[sp]--;
                }
            } else if (r == 2 && getOsten(spielfeld, spielerPosX[sp], spielerPosY[sp], false) != '8') {
                if (getOsten(spielfeld, spielerPosX[sp], spielerPosY[sp], false) == 'P') {
                    int[] team = new int[]{1, 1, 1, 1, 2, 2, 2, 2};
                    for (int i = 0; i < spielerPosX.length; i++) {
                        if (spielerPosX[i] == spielerPosX[sp] + 1 && spielerPosY[i] == spielerPosY[sp]) {
                            if (team[i] != team[sp]) {
                                spielerPosX[i] = -1;
                                spielerPosY[i] = -1;
                                spielerPosX[sp]++;
                            }
                        }
                    }
                } else {
                    spielerPosX[sp]++;
                }
            } else if (r == 3 && getSueden(spielfeld, spielerPosX[sp], spielerPosY[sp], false) != '8') {
                if (getSueden(spielfeld, spielerPosX[sp], spielerPosY[sp], false) == 'P') {
                    int[] team = new int[]{1, 1, 1, 1, 2, 2, 2, 2};
                    for (int i = 0; i < spielerPosX.length; i++) {
                        if (spielerPosX[i] == spielerPosX[sp] && spielerPosY[i] == spielerPosY[sp] + 1) {
                            if (team[i] != team[sp]) {
                                spielerPosX[i] = -1;
                                spielerPosY[i] = -1;
                                spielerPosY[sp]++;
                            }
                        }
                    }
                } else {
                    spielerPosY[sp]++;
                }
            } else if (r == 4 && getWesten(spielfeld, spielerPosX[sp], spielerPosY[sp], false) != '8') {
                if (getWesten(spielfeld, spielerPosX[sp], spielerPosY[sp], false) == 'P') {
                    int[] team = new int[]{1, 1, 1, 1, 2, 2, 2, 2};
                    for (int i = 0; i < spielerPosX.length; i++) {
                        if (spielerPosX[i] == spielerPosX[sp] - 1 && spielerPosY[i] == spielerPosY[sp]) {
                            if (team[i] != team[sp]) {
                                spielerPosX[i] = -1;
                                spielerPosY[i] = -1;
                                spielerPosX[sp]--;
                            }
                        }
                    }
                } else {
                    spielerPosX[sp]--;
                }
            }
            spielfeld[spielerPosX[sp]][spielerPosY[sp]] = 'P';
        }

    }

    public static char[] sichtfeld(int sp) {
        char[] charr = new char[12];
        charr[0] = getNorden(spielfeld, spielerPosX[sp], spielerPosY[sp], false);
        charr[1] = getOsten(spielfeld, spielerPosX[sp], spielerPosY[sp], false);
        charr[2] = getSueden(spielfeld, spielerPosX[sp], spielerPosY[sp], false);
        charr[3] = getWesten(spielfeld, spielerPosX[sp], spielerPosY[sp], false);
        charr[4] = getNordOst(spielfeld, spielerPosX[sp], spielerPosY[sp], false);
        charr[5] = getSuedOst(spielfeld, spielerPosX[sp], spielerPosY[sp], false);
        charr[6] = getSuedWest(spielfeld, spielerPosX[sp], spielerPosY[sp], false);
        charr[7] = getNordWest(spielfeld, spielerPosX[sp], spielerPosY[sp], false);
        charr[8] = getNorden(spielfeld, spielerPosX[sp], spielerPosY[sp] - 1, false);
        charr[9] = getOsten(spielfeld, spielerPosX[sp] + 1, spielerPosY[sp], false);
        charr[10] = getSueden(spielfeld, spielerPosX[sp], spielerPosY[sp] + 1, false);
        charr[11] = getWesten(spielfeld, spielerPosX[sp] - 1, spielerPosY[sp], false);
        return charr;
    }

    public static boolean istTeammate(int sp, int x, int y) {
        int[] team = new int[]{1, 1, 1, 1, 2, 2, 2, 2};
        for (int i = 0; i < spielerPosX.length; i++) {
            if (spielerPosX[i] == x && spielerPosY[i] == y) {
                if (team[i] != team[sp]) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public static void schritt() {
        //Heatmap reset
        teamHeatmap = new int[laenge][breite];
        reihenfolge();

        for (int i = 0; i < reihenfolge.length; i++) {
            if (spielerPosX[reihenfolge[i]] != -1) {
                if (reihenfolge[i] < 4) {
                    zugEins(reihenfolge[i]);
                } else {
                    zugZwei(reihenfolge[i]);
                }
            }
        }
        for (int i = 0; i < reihenfolge.length; i++) {
            if (spielerPosX[reihenfolge[i]] == -1) {
                //respawn
                respawn(reihenfolge[i]);
            }
        }


        schrittAnzahlOne--;
        schrittAnzahlTwo--;
        schrittAnzahlOneV--;
        schrittAnzahlTwoV--;
    }

    public static void simulation(int n) {
        SchischVisualizer sv = new SchischVisualizer();
        int a = Zufall.zufallGanz(60,80);
        int b = Zufall.zufallGanz(60,80);

        a = 80;
        b = 80;

        laenge = a;
        breite = b;

        teamHeatmap = new int[laenge][breite];

        initialisiereSpielfeld(a,b);
        sv.step(spielfeld);

        startPositionen();
        sv.step(spielfeld);



        for (int i = 0; i < n; i++) {
            schritt();
            sv.step(spielfeld);
        }


        sv.start();

    }

    public static void main(String[] args) {

        simulation(5000);
        System.out.println("Team 1: " + zaehlen(1));
        System.out.println("Team 2: " + zaehlen(2));
    }
}
