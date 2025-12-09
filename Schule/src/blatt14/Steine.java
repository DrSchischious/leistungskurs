package blatt14;

import schisch_visualizer.SchischVisualizer;

public class Steine {


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
                return true;
            } else {
                return false;
            }
        }

        if (feld[x][0] == ' ' && feld[x][1] == ' ' && feld[x+1][0] == ' ' && feld[x+1][1] == ' ') {
            feld[x][0] = '5';
            feld[x][1] = '5';
            feld[x+1][0] = '5';
            feld[x+1][1] = '5';
            return true;
        } else {
            return false;
        }
    }






    public static void main(String[] args) {
        char[][] arr = init();
        SchischVisualizer sv = new SchischVisualizer();
        sv.step(arr);
        zeichneI(arr,0,0);
        sv.step(arr);
        zeichneO(arr,4);
        sv.step(arr);
        zeichneT(arr,6,0);
        sv.step(arr);
        sv.start();

    }

}