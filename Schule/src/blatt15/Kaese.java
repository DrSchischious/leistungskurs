package blatt15;

import blatt14.MultiArrays;
import blatt14.Simulationen;
import schisch_visualizer.SchischVisualizer;

public class Kaese {

    public static char[][] init(int n, int m, double ws) {
        char[][] charr = MultiArrays.createEmpty2DCharArray(n, m);
        Simulationen.fuellen(charr, '5',ws);

        return charr;
    }

    public static void simulationStart(char[][] charr) {
        //Erster Schritt, oberste Zeile füllen
        for (int i = 0; i < charr.length; i++) {
            if (charr[i][0] == ' ') {
                charr[i][0] = '2';
            }
        }
    }

    public static char[][] simulation(char[][] charr) {
        char[][] feldNeu = MultiArrays.copy2DCharArray(charr);
        for (int x = 0; x < charr.length; x++) {
            for (int y = 0; y < charr[x].length; y++) {
                if (charr[x][y] == '2') {
                    if (Simulationen.zaehlenVier(charr,x,y,' ',false) > 0) {
                        if (Simulationen.getNorden(charr,x,y,false) == ' ') {
                            feldNeu[x][y-1] = '2';
                        }
                        if (Simulationen.getSueden(charr,x,y,false) == ' ') {
                            feldNeu[x][y+1] = '2';
                        }
                        if (Simulationen.getWesten(charr,x,y,false) == ' ') {
                            feldNeu[x-1][y] = '2';
                        }
                        if (Simulationen.getOsten(charr,x,y,false) == ' ') {
                            feldNeu[x+1][y] = '2';
                        }
                    }
                }
            }
        }
        return feldNeu;
    }


    public static void main(String[] args) {
        SchischVisualizer sv = new SchischVisualizer();

        char[][] charr = init(300, 200,0.40);

        sv.step(charr);

        simulationStart(charr);
        sv.step(charr);

        char[][] feldNeu = simulation(charr);

        while (MultiArrays.istIdentisch(charr,feldNeu) == false) {
            charr = feldNeu;
            sv.step(charr);
            feldNeu = simulation(charr);
        }

        //Prüfe Wasserdichte

        boolean wasserdicht = true;
        for (int i = 0; i < charr.length; i++) {
            if (charr[i][charr[i].length - 1] == '2') {
                wasserdicht = false;
            }
        }

        if (wasserdicht) {
            System.out.println("Der Käse ist wasserdicht");
        } else {
            System.out.println("Der Käse ist nicht wasserdicht");
        }



        sv.start();

    }

}
