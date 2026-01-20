package blatt15;

import blatt13.Zufall;
import blatt14.MultiArrays;
import blatt14.Simulationen;
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
                    if (Simulationen.getOsten(spielfeld,x,y,false) == 'P' && done == false) {
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
                    if (Simulationen.getSueden(spielfeld,x,y,false) == 'P' && done == false) {
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
                    if (Simulationen.getWesten(spielfeld,x,y,false) == 'P' && done == false) {
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
                    if (done == false && ((Simulationen.getOsten(spielfeld,x,y,false) == FARBE1 && team == 2) || (Simulationen.getOsten(spielfeld,x,y,false) == FARBE2 && team == 1))) {
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
                    if (done == false && ((Simulationen.getSueden(spielfeld,x,y,false) == FARBE1 && team == 2) || (Simulationen.getSueden(spielfeld,x,y,false) == FARBE2 && team == 1))) {
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
                    if (done == false && ((Simulationen.getWesten(spielfeld,x,y,false) == FARBE1 && team == 2) || (Simulationen.getWesten(spielfeld,x,y,false) == FARBE2 && team == 1))) {
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
                    if (done == false && Simulationen.getOsten(spielfeld,x,y,false) == ' ') {
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
                    if (done == false && Simulationen.getSueden(spielfeld,x,y,false) == ' ') {
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
                    if (done == false && Simulationen.getWesten(spielfeld,x,y,false) == ' ') {
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
                        if (Simulationen.getOsten(spielfeld,x,y,false) != WAND) {
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
                        if (Simulationen.getSueden(spielfeld,x,y,false) != WAND) {
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
                        if (Simulationen.getWesten(spielfeld,x,y,false) != WAND) {
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
                        if (Simulationen.getOsten(spielfeld,x,y,false) != WAND) {
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
                        if (Simulationen.getSueden(spielfeld,x,y,false) != WAND) {
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
                        if (Simulationen.getWesten(spielfeld,x,y,false) != WAND) {
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
        //Check around
        int x = spielerPosX[spieler];
        int y = spielerPosY[spieler];
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
            } else if (Simulationen.getOsten(spielfeld,x,y,false) == 'P' && done == false) {
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
            } else if (Simulationen.getSueden(spielfeld,x,y,false) == 'P' && done == false) {
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
            } else if (Simulationen.getWesten(spielfeld,x,y,false) == 'P' && done == false) {
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

            //if no enemy is around go ahead and check for enemy field
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
            } else if (done == false && ((Simulationen.getOsten(spielfeld,x,y,false) == FARBE1 && team == 2) || (Simulationen.getOsten(spielfeld,x,y,false) == FARBE2 && team == 1))) {
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
            } else if (done == false && ((Simulationen.getSueden(spielfeld,x,y,false) == FARBE1 && team == 2) || (Simulationen.getSueden(spielfeld,x,y,false) == FARBE2 && team == 1))) {
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
            } else if (done == false && ((Simulationen.getWesten(spielfeld,x,y,false) == FARBE1 && team == 2) || (Simulationen.getWesten(spielfeld,x,y,false) == FARBE2 && team == 1))) {
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

            //if no enemy field is around go ahead and check for free field
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
            } else if (done == false && Simulationen.getOsten(spielfeld,x,y,false) == ' ') {
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
            } else if (done == false && Simulationen.getSueden(spielfeld,x,y,false) == ' ') {
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
            } else if (done == false && Simulationen.getWesten(spielfeld,x,y,false) == ' ') {
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
                        if (Simulationen.getOsten(spielfeld,x,y,false) != WAND) {
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
                        if (Simulationen.getSueden(spielfeld,x,y,false) != WAND) {
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
                        if (Simulationen.getWesten(spielfeld,x,y,false) != WAND) {
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
                        if (Simulationen.getOsten(spielfeld,x,y,false) != WAND) {
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
                        if (Simulationen.getSueden(spielfeld,x,y,false) != WAND) {
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
                        if (Simulationen.getWesten(spielfeld,x,y,false) != WAND) {
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
        int a = Zufall.zufallGanz(40,80);
        int b = Zufall.zufallGanz(40,80);

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
        simulation(1000);
    }
}
