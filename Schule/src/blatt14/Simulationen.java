package blatt14;

import schisch_visualizer.SchischVisualizer;

public class Simulationen {

    public static void fuellen(char[][] charr, char ein, double ws) {
        for (int i = 0; i < charr.length; i++) {
            for (int j = 0; j < charr[0].length; j++) {
                if (Math.random() < ws) {
                    charr[i][j] = ein;
                }
            }
        }
    }

    public static void fuellen(char[][] charr, char ein, double ws, char neutral) {
        for (int i = 0; i < charr.length; i++) {
            for (int j = 0; j < charr[0].length; j++) {
                if (charr[i][j] == neutral) {
                    if (Math.random() < ws) {
                        charr[i][j] = ein;
                    }
                }

            }
        }
    }

    public static void platzieren(char[][] charr, char ein, boolean dopplung) {
        if (!dopplung) {
            int x = (int)(Math.random()*charr.length);
            int y = (int)(Math.random()*charr[0].length);
            charr[x][y] = ein;
        }
        while (dopplung) {
            int x = (int)(Math.random()*charr.length);
            int y = (int)(Math.random()*charr[0].length);
            if (charr[x][y] != ein) {
                charr[x][y] = ein;
                break;
            }

        }



    }

    public static void main(String[] args) {
        SchischVisualizer sv = new SchischVisualizer();
        char[][] charr = new char[10][10];
        fuellen(charr,' ',1);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        platzieren(charr,'O',true);
        sv.step(charr);
        sv.start();
    }
}
