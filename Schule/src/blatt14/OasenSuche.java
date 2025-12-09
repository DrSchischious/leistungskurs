package blatt14;

import schisch_visualizer.SchischVisualizer;

public class OasenSuche {

    static char[][] spielfeld;

    public static void initialisiereSpielfeld(int l, int b) {
        spielfeld = new char[l][b];
        Simulationen.fuellen(spielfeld,' ',1);
    }

    public static void zufallsPositionSpieler() {
        Simulationen.platzieren(spielfeld,'P',true);
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

    public static int[] findeSpieler() {
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[i].length;j++) {
                if (spielfeld[i][j] == 'P') {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public static void findeWasser(SchischVisualizer sv, int energy) {

        while (energy > 0) {
            int[] pos = findeSpieler();
            if (Simulationen.zaehlenVier(spielfeld, pos[0],pos[1],'6',true) > 0) {
                System.out.println("Wasser gefunden");
                break;
            }

            while (true) {
                int z = blatt13.Zufall.zufallGanz(1,5);
                //System.out.println(z);
                if (z == 1) {
                    //Norden
                    if (Simulationen.getNorden(spielfeld,pos[0],pos[1],true) == ' ') {
                        spielfeld[pos[0]][pos[1]] = '4';
                        if (pos[1] == 0) {
                            spielfeld[pos[0]][spielfeld[pos[0]].length-1] = 'P';
                        } else {
                            spielfeld[pos[0]][pos[1]-1] = 'P';
                        }

                        break;
                    }
                } else if (z == 2) {
                    //Osten

                    if (Simulationen.getOsten(spielfeld,pos[0],pos[1],true) == ' ') {
                        spielfeld[pos[0]][pos[1]] = '4';
                        if (pos[0] == spielfeld.length - 1) {
                            spielfeld[0][pos[1]] = 'P';
                        } else {
                            spielfeld[pos[0]+1][pos[1]] = 'P';
                        }

                        break;
                    }

                } else if (z == 3) {
                    //Sueden

                    if (Simulationen.getSueden(spielfeld,pos[0],pos[1],true) == ' ') {
                        spielfeld[pos[0]][pos[1]] = '4';
                        if (pos[1] == spielfeld[0].length - 1) {
                            spielfeld[pos[0]][0] = 'P';
                        } else {
                            spielfeld[pos[0]][pos[1]+1] = 'P';
                        }

                        break;
                    }
                } else {
                    //Westen
                    if (Simulationen.getSueden(spielfeld,pos[0],pos[1],true) == ' ') {
                        spielfeld[pos[0]][pos[1]] = '4';
                        if (pos[0] == 0) {
                            spielfeld[spielfeld[pos[0]].length-1][pos[1]] = 'P';
                        } else {
                            spielfeld[pos[0]-1][pos[1]] = 'P';
                        }

                        break;
                    }
                }
            }


            sv.step(spielfeld);
            energy--;
        }
    }



    public static void main(String[] args) {
        SchischVisualizer sv = new SchischVisualizer();
        initialisiereSpielfeld(60,60);
        zufallsPositionSpieler();
        sv.step(spielfeld);
        //wasserZufall(0.03);
        sv.step(spielfeld);
        //steinZufall(0.15);
        sv.step(spielfeld);
        findeWasser(sv, 50);
        sv.start();
    }
}
