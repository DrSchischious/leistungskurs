package blatt14;

import schisch_visualizer.SchischVisualizer;

public class Mehrdimensional {
    public static void change(char[][] charr) {
        charr[blatt13.Zufall.zufallGanz(charr.length-1)][blatt13.Zufall.zufallGanz(charr[0].length-1)] = (char)('0' + (blatt13.Zufall.zufallGanz(0,10)));
    }

    public static void main(String[] args) {
        char[][] charr = new char[20][40];
        for (int i = 0; i < charr.length; i++) {
            for (int j = 0; j < charr[i].length; j++) {
                charr[i][j] = ' ';
            }
        }
        SchischVisualizer sv = new SchischVisualizer();
        sv.step(charr);
        charr[2][1] = '3';
        int i = 0;
        while (true) {
            sv.step(charr);
            change(charr);
            change(charr);
            change(charr);
            change(charr);

            i++;
            if (i == 4000) {
                break;
            }
        }


        sv.start();




    }
}
