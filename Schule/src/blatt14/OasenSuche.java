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

            int count = 0;
            while (true) {
                count++;



                int c = Simulationen.zaehlenVier(spielfeld,pos[0],pos[1],'4',true);
                c = c + Simulationen.zaehlenVier(spielfeld,pos[0],pos[1],'A',true);
                int z = blatt13.Zufall.zufallGanz(1,5);
                //System.out.println(z);
                if (z == 1) {
                    //Norden
                    if (Simulationen.getNorden(spielfeld,pos[0],pos[1],true) == ' ' || ((count >= 4 && Simulationen.getNorden(spielfeld,pos[0],pos[1],true) == '4')) || c == 4) {
                        spielfeld[pos[0]][pos[1]] = '4';
                        if (pos[1] == 0) {
                            spielfeld[pos[0]][spielfeld[pos[0]].length-1] = 'P';
                        } else {
                            spielfeld[pos[0]][pos[1]-1] = 'P';
                        }

                        count = 0;
                        break;
                    }
                } else if (z == 2) {
                    //Osten

                    if (Simulationen.getOsten(spielfeld,pos[0],pos[1],true) == ' ' || ((count >= 4 && Simulationen.getOsten(spielfeld,pos[0],pos[1],true) == '4')) || c == 4) {
                        spielfeld[pos[0]][pos[1]] = '4';
                        if (pos[0] == spielfeld.length - 1) {
                            spielfeld[0][pos[1]] = 'P';
                        } else {
                            spielfeld[pos[0]+1][pos[1]] = 'P';
                        }

                        count = 0;
                        break;
                    }

                } else if (z == 3) {
                    //Sueden

                    if (Simulationen.getSueden(spielfeld,pos[0],pos[1],true) == ' ' || ((count >= 4 && Simulationen.getSueden(spielfeld,pos[0],pos[1],true) == '4')) || c == 4) {
                        spielfeld[pos[0]][pos[1]] = '4';
                        if (pos[1] == spielfeld[0].length - 1) {
                            spielfeld[pos[0]][0] = 'P';
                        } else {
                            spielfeld[pos[0]][pos[1]+1] = 'P';
                        }

                        count = 0;
                        break;
                    }
                } else {
                    //Westen
                    if (Simulationen.getWesten(spielfeld,pos[0],pos[1],true) == ' ' || ((count >= 4 && Simulationen.getWesten(spielfeld,pos[0],pos[1],true) == '4')) || c == 4) {
                        spielfeld[pos[0]][pos[1]] = '4';
                        if (pos[0] == 0) {
                            spielfeld[spielfeld[pos[0]].length-1][pos[1]] = 'P';
                        } else {
                            spielfeld[pos[0]-1][pos[1]] = 'P';
                        }

                        count = 0;
                        break;
                    }
                }
                if (count == 4) {
                    count = 0;
                }
            }


            sv.step(spielfeld);
            energy--;
        }
    }



    public static void main(String[] args) {
        SchischVisualizer sv = new SchischVisualizer();
        initialisiereSpielfeld(100,100);
        zufallsPositionSpieler();
        sv.step(spielfeld);
        wasserZufall(0.01);
        sv.step(spielfeld);
        steinZufall(0.1);
        sv.step(spielfeld);
        findeWasser(sv, 75);
        sv.start();
    }
}
