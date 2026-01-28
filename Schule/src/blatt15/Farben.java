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

    public static void zugZwei(int sp) {
        if (spielerPosX[sp] > -1) {
            char[] sicht = sichtfeld(sp);
            int counter = 0;
            for (int i = 4; i < 8; i++) {
                if (spielerPosX[i] < spielerPosX[sp] && spielerPosX[i] > -1) {
                    counter++;
                }
            }
            if (counter > 1) { //Defensive
                if (spielerPosX[sp] < spielfeld.length - 2) {
                    char[] charr = new char[]{'P', '7', ' ', '9'};
                    for (int i = 0; i < charr.length; i++) {
                        if (sicht[1] == charr[i]) {
                            if (!istTeammate(sp, spielerPosX[sp] - 1, spielerPosY[sp])) {
                                move(sp, 2);
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
                        } else if (sicht[3] == charr[i]) {
                            if (!istTeammate(sp, spielerPosX[sp] + 1, spielerPosY[sp])) {
                                move(sp, 4);
                                i = charr.length;
                            }
                        } else {
                            if (i == charr.length - 1) {
                                move(sp, zufallGanz(1, 4));
                            }
                        }
                    }
                } else {
                    int max;
                    if (sp == 0) {
                        max = 1;
                    } else {
                        max = 0;
                    }
                    int dir;
                    for (int i = 0; i < 4; i++) {
                        if (spielerPosX[i] > spielerPosX[max] && i != sp) {
                            max = i;
                        }
                    }
                    int y;
                    if (spielerPosY[sp] < spielerPosY[max]) {
                        dir = 1;
                        y = -1;
                    } else {
                        dir = 3;
                        y = 1;
                    }
                    boolean c = true;
                    if (!istTeammate(sp,spielerPosX[sp], spielerPosY[sp] + y)) {
                        if (sicht[dir - 1] != '9' && sicht[dir - 1] != '8') {
                            move(sp, dir);
                            c = false;
                        }
                    }
                    char[] charr = new char[]{'P', '7', ' ', '9'};
                    if ((sicht[0] == '7' || sicht[0] == '8') && (sicht[1] == '7' || sicht[1] == '8') && sicht[2] == '7' && (sicht[3] == '7' || sicht[3] == '8')) {
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
                char[] charr = new char[]{'P', '7', ' ', '9'};
                for (int i = 0; i < charr.length; i++) {
                    if (sicht[3] == charr[i]) {
                        if (!istTeammate(sp, spielerPosX[sp] - 1, spielerPosY[sp])) {
                            move(sp, 4);
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
                    } else if (sicht[1] == charr[i]) {
                        if (!istTeammate(sp, spielerPosX[sp] + 1, spielerPosY[sp])) {
                            move(sp, 2);
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
        int a = Zufall.zufallGanz(10,40);
        int b = Zufall.zufallGanz(10,40);

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
