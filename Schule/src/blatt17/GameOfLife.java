package blatt17;

import blatt14.MultiArrays;
import blatt14.Simulationen;
import schisch_visualizer.SchischVisualizer;

public class GameOfLife {
    static char[][] spielfeld;

    public static int zaehlen(int x, int y) {
        boolean left = false;
        boolean right = false;
        boolean up = false;
        boolean down = false;
        if (x - 1 > 0) {
            left = true;
        }
        if (y - 1 > 0) {
            up = true;
        }
        if (x + 1 < spielfeld.length) {
            right = true;
        }
        if (y + 1 < spielfeld[0].length) {
            down = true;
        }

        int count = 0;
        if (left == true) {
            if (spielfeld[x-1][y] == '1') {
                count++;
            }
        }
        if (right == true) {
            if (spielfeld[x+1][y] == '1') {
                count++;
            }
        }
        if (up == true) {
            if (spielfeld[x][y-1] == '1') {
                count++;
            }
        }
        if (down == true) {
            if (spielfeld[x][y+1] == '1') {
                count++;
            }
        }
        if (left == true && up == true) {
            if (spielfeld[x-1][y-1] == '1') {
                count++;
            }
        }
        if (left == true && down == true) {
            if (spielfeld[x-1][y+1] == '1') {
                count++;
            }
        }
        if (right == true && up == true) {
            if (spielfeld[x+1][y-1] == '1') {
                count++;
            }
        }
        if (right == true && down == true) {
            if (spielfeld[x+1][y+1] == '1') {
                count++;
            }
        }
        return count;
    }

    public static void initRandom(double ws) {
        spielfeld = blatt14.MultiArrays.createEmpty2DCharArray(100,100);

        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                if (Math.random() < ws) {
                    spielfeld[x][y] = '1';
                }
            }
        }
    }

    public static void schritt() {
        char[][] spielfeld_neu = MultiArrays.copy2DCharArray(spielfeld);
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                int lebend = zaehlen(x,y);
                if (lebend == 3 && spielfeld[x][y] == ' ') {
                    spielfeld_neu[x][y] = '1';
                } else if (spielfeld[x][y] == '1' && lebend < 2) {
                    spielfeld_neu[x][y] = ' ';
                } else if (spielfeld[x][y] == '1' && (lebend == 3 || lebend == 2)) {
                    spielfeld_neu[x][y] = '1';
                } else if (spielfeld[x][y] == '1' && lebend > 3) {
                    spielfeld_neu[x][y] = ' ';
                }
            }
        }
        spielfeld = spielfeld_neu;
    }

    public static void main(String[] args) {
        initRandom(0.05);
        SchischVisualizer sv = new SchischVisualizer();
        sv.step(spielfeld);
        for (int i = 0; i < 6700; i++) {
            schritt();
            sv.step(spielfeld);
        }
        sv.start();
    }
}
