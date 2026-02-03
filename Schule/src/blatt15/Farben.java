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

    public static void zugEins(int spieler) {

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

    public static void zugZwei(int spieler) {
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

            sichtfeld[5] = Simulationen.getNordWest(spielfeld, x, y, false);
            sichtfeld[0] = Simulationen.getNorden(spielfeld, x, y, false);
            sichtfeld[4] = Simulationen.getNordOst(spielfeld, x, y, false);
            sichtfeld[1] = Simulationen.getOsten(spielfeld, x, y, false);
            sichtfeld[3] = Simulationen.getWesten(spielfeld, x, y, false);
            sichtfeld[7] = Simulationen.getSuedOst(spielfeld, x, y, false);
            sichtfeld[2] = Simulationen.getSueden(spielfeld, x, y, false);
            sichtfeld[6] = Simulationen.getSuedWest(spielfeld, x, y, false);

/*
        8
     7  0  4
  11 3 'P' 1  9
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

            if (sichtfeld[0] == farbe && sichtfeld[1] == farbe && sichtfeld[2] == farbe && sichtfeld[3] == farbe && sichtfeld[5] == farbe && sichtfeld[6] == farbe && sichtfeld[7] == farbe) {
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
            if (kante == 0 && sichtfeld[0] == farbe && sichtfeld[1] == farbe && sichtfeld[2] == farbe && sichtfeld[3] == farbe) {
                if (sichtfeld[4] == farbe) {
                    richtung = 0;
                    kante = 1;
                }
                if (sichtfeld[5] == farbe) {
                    richtung = 1;
                    kante = 2;
                }
                if (sichtfeld[6] == farbe) {
                    richtung = 2;
                }
                if (sichtfeld[7] == farbe) {
                    richtung = 3;
                    kante = 3;
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

            if (v) {
                if (x < 40) {
                    richtung = 1;
                } else if (x > 40) {
                    richtung = 3;
                } else if (y < 40) {
                    richtung = 2;
                } else if (y > 40) {
                    richtung = 0;
                } else {
                    richtung = zufallGanz(3);
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
    }

    public static void simulation(int n) {
        SchischVisualizer sv = new SchischVisualizer();
        int a = Zufall.zufallGanz(60,80);
        int b = Zufall.zufallGanz(60,80);

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
        simulation(2000);
        System.out.println("Team 1: " + zaehlen(1));
        System.out.println("Team 2: " + zaehlen(2));
    }
}
