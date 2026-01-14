package blatt14;

import blatt13.Zufall;
import schisch_visualizer.SchischVisualizer;

public class Steine {

    static int[] steinx;
    static int[] steiny;

    public static char[][] init() {
        char[][] arr = MultiArrays.createEmpty2DCharArray(10,40);
        return arr;
    }

    public static boolean zeichneI(char[][] feld, int x, int d) {
        if (x < 0 || x > 9) {
            return false;

        }
        if (d == 0) {
            //senkrecht
            if (feld[x][0] == ' ' && feld[x][1] == ' ' && feld[x][2] == ' ' && feld[x][3] == ' ') {
                feld[x][0] = '6';
                feld[x][1] = '6';
                feld[x][2] = '6';
                feld[x][3] = '6';

                steinx = new int[]{x,x,x,x};
                steiny = new int[]{3,2,1,0};

                return true;
            } else {

                return false;
            }
        } else {
            //waagerecht
            if (x > 6) {

                return false;
            }
            if (feld[x][0] == ' ' && feld[x+1][0] == ' ' && feld[x+2][0] == ' ' && feld[x+3][0] == ' ') {
                feld[x][0] = '6';
                feld[x+1][0] = '6';
                feld[x+2][0] = '6';
                feld[x+3][0] = '6';

                steinx = new int[]{x,x+1,x+2,x+3};
                steiny = new int[]{0,0,0,0};
                return true;
            } else {

                return false;
            }
        }
    }

    public static boolean zeichneO(char[][] feld, int x) {
        if (x < 0 || x > 8) {

            return false;
        }

        if (feld[x][0] == ' ' && feld[x][1] == ' ' && feld[x+1][0] == ' ' && feld[x+1][1] == ' ') {
            feld[x][0] = '5';
            feld[x][1] = '5';
            feld[x+1][0] = '5';
            feld[x+1][1] = '5';
            steinx = new int[]{x,x,x+1,x+1};
            steiny = new int[]{1,0,1,0};
            return true;
        } else {

            return false;
        }
    }

    public static boolean zeichneT(char[][] feld, int x, int d) {
        if (x < 0 || x > 9) {
            return false;
        }
        if (d == 0) {
            if (x > 7) {
                return false;
            }
            if (feld[x][0] == ' ' && feld[x+1][0] == ' ' && feld[x+1][1] == ' ' && feld[x+2][0] == ' ') {
                feld[x][0] = 'C';
                feld[x+1][0] = 'C';
                feld[x+2][0] = 'C';
                feld[x+1][1] = 'C';
                steinx = new int[]{x,x+1,x+2,x+1};
                steiny = new int[]{0,1,0,0};
                return true;
            } else {
                return false;
            }
            //T
        } else if (d == 1) {
            //Nach rechts
            if (x > 8) {
                return false;
            }
            if (feld[x][0] == ' ' && feld[x][1] == ' ' && feld[x][2] == ' ' && feld[x+1][1] == ' ') {
                feld[x][0] = 'C';
                feld[x][1] = 'C';
                feld[x][2] = 'C';
                feld[x+1][1] = 'C';
                steinx = new int[]{x,x,x,x+1};
                steiny = new int[]{2,1,0,1};
                return true;
            } else {
                return false;
            }
        } else if (d == 2) {
            //Nach unten
            if (x > 7) {
                return false;
            }
            if (feld[x][1] == ' ' && feld[x+1][1] == ' ' && feld[x+1][0] == ' ' && feld[x+2][1] == ' ') {
                feld[x][1] = 'C';
                feld[x+1][1] = 'C';
                feld[x+2][1] = 'C';
                feld[x+1][0] = 'C';
                steinx = new int[]{x,x+1,x+2,x+1};
                steiny = new int[]{1,1,1,0};
                return true;
            } else {
                return false;
            }
        } else if (d == 3) {
            //Nach links
            if (x > 8) {
                return false;
            }
            if (feld[x+1][0] == ' ' && feld[x+1][1] == ' ' && feld[x+1][2] == ' ' && feld[x][1] == ' ') {
                feld[x+1][0] = 'C';
                feld[x+1][1] = 'C';
                feld[x+1][2] = 'C';
                feld[x][1] = 'C';
                steinx = new int[]{x+1,x+1,x+1,x};
                steiny = new int[]{2,1,0,1};
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }


    }

    public static boolean zeichneS(char[][] feld, int x, int d) {
        if (x < 0 || x > 9) {
            return false;
        }
        if (d == 0) {
            //Waagerecht
            if (x > 7) {
                return false;
            }
            if (feld[x][1] == ' ' && feld[x+1][1] == ' ' && feld[x+1][0] == ' ' && feld[x+2][0] == ' ') {
                feld[x][1] = 'B';
                feld[x+1][1] = 'B';
                feld[x+1][0] = 'B';
                feld[x+2][0] = 'B';
                steinx = new int[]{x,x+1,x+1,x+2};
                steiny = new int[]{1,1,0,0};
                return true;
            } else {
                return false;
            }

        } else {
            //Senkrecht
            if (x > 8) {
                return false;
            }
            if (feld[x][0] == ' ' && feld[x][1] == ' ' && feld[x+1][1] == ' ' && feld[x+1][2] == ' ') {
                feld[x][0] = 'B';
                feld[x][1] = 'B';
                feld[x][2] = 'B';
                feld[x+1][1] = 'B';
                steinx = new int[]{x,x,x,x+1};
                steiny = new int[]{2,1,0,1};
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean zeichneZ(char[][] feld, int x, int d) {
        if (x < 0 || x > 9) {
            return false;
        }
        if (d == 0) {
            //Waagerecht
            if (x > 7) {
                return false;
            }
            if (feld[x][0] == ' ' && feld[x+1][0] == ' ' && feld[x+1][1] == ' ' && feld[x+2][1] == ' ') {
                feld[x][0] = 'F';
                feld[x+1][0] = 'F';
                feld[x+1][1] = 'F';
                feld[x+2][1] = 'F';
                steinx = new int[]{x,x+1,x+1,x+2};
                steiny = new int[]{0,1,0,1};
                return true;
            } else {
                return false;
            }

        } else {
            //Senkrecht
            if (x > 8) {
                return false;
            }
            if (feld[x+1][0] == ' ' && feld[x+1][1] == ' ' && feld[x][1] == ' ' && feld[x][2] == ' ') {
                feld[x+1][0] = 'F';
                feld[x+1][1] = 'F';
                feld[x][1] = 'F';
                feld[x][2] = 'F';
                steinx = new int[]{x+1,x+1,x,x};
                steiny = new int[]{1,0,2,1};
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean zeichneJ(char[][] feld, int x, int d) {
        if (x < 0 || x > 9) {
            return false;
        }
        if (d == 0) {
            //Waagerecht
            if (x > 7) {
                return false;
            }
            if (feld[x][0] == ' ' && feld[x][1] == ' ' && feld[x+1][1] == ' ' && feld[x+2][1] == ' ') {
                feld[x][0] = '2';
                feld[x+1][0] = '2';
                feld[x+1][1] = '2';
                feld[x+2][1] = '2';
                steinx = new int[]{x,x+1,x+1,x+2};
                steiny = new int[]{0,1,0,1};
                return true;
            } else {
                return false;
            }

        } else if (d == 1) {
            //Senkrecht
            if (x > 8) {
                return false;
            }
            if (feld[x+1][0] == ' ' && feld[x+1][1] == ' ' && feld[x+1][2] == ' ' && feld[x][2] == ' ') {
                feld[x+1][0] = '2';
                feld[x+1][1] = '2';
                feld[x+1][2] = '2';
                feld[x][2] = '2';
                steinx = new int[]{x+1,x+1,x+1,x};
                steiny = new int[]{2,1,0,2};
                return true;
            } else {
                return false;
            }
        } else if (d == 2) {
            //Senkrecht
            if (x > 7) {
                return false;
            }
            if (feld[x][0] == ' ' && feld[x+1][0] == ' ' && feld[x+2][0] == ' ' && feld[x+2][1] == ' ') {
                feld[x][0] = '2';
                feld[x+1][0] = '2';
                feld[x+2][0] = '2';
                feld[x+2][1] = '2';
                steinx = new int[]{x,x+1,x+2,x+2};
                steiny = new int[]{0,0,1,0};
                return true;
            } else {
                return false;
            }
        } else if (d == 3) {
            //Senkrecht
            if (x > 8) {
                return false;
            }
            if (feld[x][0] == ' ' && feld[x+1][0] == ' ' && feld[x][1] == ' ' && feld[x][2] == ' ') {
                feld[x][0] = '2';
                feld[x+1][0] = '2';
                feld[x][1] = '2';
                feld[x][2] = '2';
                steinx = new int[]{x,x+1,x,x};
                steiny = new int[]{2,0,1,0};
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean zeichneL(char[][] feld, int x, int d) {
        if (x < 0 || x > 9) {
            return false;
        }
        if (d == 0) {
            //Waagerecht
            if (x > 7) {
                return false;
            }
            if (feld[x][1] == ' ' && feld[x+1][1] == ' ' && feld[x+2][1] == ' ' && feld[x+2][0] == ' ') {
                feld[x][1] = 'A';
                feld[x+1][1] = 'A';
                feld[x+2][1] = 'A';
                feld[x+2][0] = 'A';
                steinx = new int[]{x,x+1,x+2,x+2};
                steiny = new int[]{1,1,1,0};
                return true;
            } else {
                return false;
            }

        } else if (d == 1) {
            //Senkrecht
            if (x > 8) {
                return false;
            }
            if (feld[x][0] == ' ' && feld[x][1] == ' ' && feld[x][2] == ' ' && feld[x+1][2] == ' ') {
                feld[x][0] = 'A';
                feld[x][1] = 'A';
                feld[x][2] = 'A';
                feld[x+1][2] = 'A';
                steinx = new int[]{x,x,x,x+1};
                steiny = new int[]{2,1,0,2};
                return true;
            } else {
                return false;
            }
        } else if (d == 2) {
            //Senkrecht
            if (x > 7) {
                return false;
            }
            if (feld[x][0] == ' ' && feld[x+1][0] == ' ' && feld[x+2][0] == ' ' && feld[x][1] == ' ') {
                feld[x][0] = 'A';
                feld[x+1][0] = 'A';
                feld[x+2][0] = 'A';
                feld[x][1] = 'A';
                steinx = new int[]{x,x+1,x+2,x};
                steiny = new int[]{1,0,0,0};
                return true;
            } else {
                return false;
            }
        } else if (d == 3) {
            //Senkrecht
            if (x > 8) {
                return false;
            }
            if (feld[x][0] == ' ' && feld[x+1][0] == ' ' && feld[x+1][1] == ' ' && feld[x+1][2] == ' ') {
                feld[x][0] = 'A';
                feld[x+1][0] = 'A';
                feld[x+1][1] = 'A';
                feld[x+1][2] = 'A';
                steinx = new int[]{x,x+1,x+1,x+1};
                steiny = new int[]{0,2,1,0};
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Simuliert einen Schritt bei Tetris.
     * @param feld Spielfeld im aktuellen Zustand
     */
    public static char[][] fall(char[][] feld) {
        //Schritt 1: Kopiere Spielfeld
        char[][] copy = MultiArrays.copy2DCharArray(feld);

        //Schritt 2: Durchlaufe einen Schritt für die Figur
        boolean konflikt = false;
        for (int i = 0; i < 4; i++) {
            if (schieben(copy,steinx[i], steiny[i])) {


            } else {
                konflikt = true;
                break;
            }
        }


        //Schritt 3: Falls Konflikt, melde diesen Zurück, ansonsten akzeptiere verändertes Feld.
        if (konflikt == true) {
            return null;
        } else {
            //Setze Koordinaten neu!
            for (int i = 0; i < 4; i++) {
                steiny[i]++;
            }
            return copy;

        }

    }

    public static boolean schieben(char[][] feld, int x, int y) {
        if (y == 39) {
            return false;
        }
        if (feld[x][y+1] != ' ') {
            return false;
        } else {
            feld[x][y+1] = feld[x][y];
            feld[x][y] = ' ';
            return true;
        }
    }

    public static void simulateTetris() {
        char[][] arr = init();
        SchischVisualizer sv = new SchischVisualizer();
        sv.step(arr);

        while(true) {
            //Zeichne Random Figur.
            if (randomDraw(arr) == true) {

            } else {
                //FEHLER
                //Game over
                break;
            }

            sv.step(arr);

            while (true) {


                char[][] step = fall(arr);
                if (step == null) {
                    //Done
                    break;
                } else {
                    arr = step;
                }

                sv.step(arr);
            }

            //Check for Finished Row[!]
            while (true) {
                int y = hasFinished(arr);
                if (y < 0) {
                    break;
                } else {
                    arr = killRow(arr,y);
                    sv.step(arr);
                }

                //Scoop everything down!
                arr = scoopRow(arr, y);
                sv.step(arr);
            }


        }




        sv.start();



    }

    public static char[][] scoopRow(char[][] feld, int yrow) {
        for (int y = yrow; y > 0; y--) {
            for (int x = 0; x < 10; x++) {
                feld[x][y] = feld[x][y-1];
            }
        }
        for (int x = 0; x < 10; x++) {
            feld[x][0] = ' ';
        }
        return feld;
    }

    public static char[][] killRow(char[][] feld, int y) {
        for (int i = 0; i < 10; i++) {
            feld[i][y] = ' ';
        }
        return feld;
    }
    public static int hasFinished(char[][] feld) {
        for (int y = 0; y < feld[0].length; y++) {
            int count = 0;
            for (int x = 0; x < feld.length;x++) {

                if (feld[x][y] == ' ') {
                    break;
                }
                count++;
            }
            if (count == 10) {
                return y;
            }
        }
        return -1;

    }

    public static boolean randomDraw(char[][] feld) {
        int i = Zufall.zufallGanz(1,7);

        int d = 0;
        int x = 0;
        boolean good = true;
        switch(i) {
            case 1:
                d = Zufall.zufallGanz(0,1);
                if (d == 0) {
                    x = Zufall.zufallGanz(0,9);
                } else {
                    x = Zufall.zufallGanz(0,6);
                }
                good = zeichneI(feld,x,d);

                break;
            case 2:

                x = Zufall.zufallGanz(0,8);
                good = zeichneO(feld,x);
                break;

            case 3:

                d = Zufall.zufallGanz(0,3);
                if (d == 0) {
                    x = Zufall.zufallGanz(0,7);
                } else if (d == 1) {
                    x = Zufall.zufallGanz(0,8);
                } else if (d == 2) {
                    x = Zufall.zufallGanz(0,7);
                } else if (d == 3) {
                    x = Zufall.zufallGanz(0,8);
                }
                good = zeichneT(feld,x,d);

                break;

            case 4:

                d = Zufall.zufallGanz(0,1);
                if (d == 0) {
                    x = Zufall.zufallGanz(0,7);
                } else if (d == 1) {
                    x = Zufall.zufallGanz(0,8);
                }
                good = zeichneS(feld,x,d);

                break;

            case 5:

                d = Zufall.zufallGanz(0,1);
                if (d == 0) {
                    x = Zufall.zufallGanz(0,7);
                } else if (d == 1) {
                    x = Zufall.zufallGanz(0,8);
                }
                good = zeichneZ(feld,x,d);
                break;

            case 6:

                d = Zufall.zufallGanz(0,3);
                if (d == 0) {
                    x = Zufall.zufallGanz(0,7);
                } else if (d == 1) {
                    x = Zufall.zufallGanz(0,8);
                } else if (d == 2) {
                    x = Zufall.zufallGanz(0,7);
                } else if (d == 3) {
                    x = Zufall.zufallGanz(0,8);
                }
                good = zeichneJ(feld,x,d);
                break;

            case 7:

                d = Zufall.zufallGanz(0,3);
                if (d == 0) {
                    x = Zufall.zufallGanz(0,7);
                } else if (d == 1) {
                    x = Zufall.zufallGanz(0,8);
                } else if (d == 2) {
                    x = Zufall.zufallGanz(0,7);
                } else if (d == 3) {
                    x = Zufall.zufallGanz(0,8);
                }
                good = zeichneL(feld,x,d);
                break;
        }
        return good;
    }






    public static void main(String[] args) {

       simulateTetris();

    }

}