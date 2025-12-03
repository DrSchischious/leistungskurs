package blatt14;

import schisch_visualizer.SchischVisualizer;

public class OasenSuche {

    static char[][] spielfeld;

    public static void initialisiereSpielfeld(int l, int b) {
        spielfeld = new char[l][b];
        Simulationen.fuellen(spielfeld,' ',1);
    }

    public static void zufallsPositionSpieler() {
        Simulationen.platzieren(spielfeld,'O',true);
    }

    public static void wasserZufall(double ws) {
        if (ws >= 0 && ws <= 1) {
            Simulationen.fuellen(spielfeld,'6',ws,' ');
        }
    }

    public static void steinZufall(double ws) {
        if (ws >= 0 && ws <= 1) {
            Simulationen.fuellen(spielfeld,'A',ws,' ');
        }
    }



    public static void main(String[] args) {
        SchischVisualizer sv = new SchischVisualizer();
        initialisiereSpielfeld(20,20);
        zufallsPositionSpieler();
        sv.step(spielfeld);
        wasserZufall(0.1);
        sv.step(spielfeld);
        steinZufall(0.3);
        sv.step(spielfeld);
        sv.start();
    }
}
