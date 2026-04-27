package blatt26.aufgabe03;

public class Simulation {


    private Spieler[] spieler;
    private int versklavt;
    private int gewonnen;

    public Simulation(int anzahlSpieler) {
        this.spieler = new Spieler[anzahlSpieler];
        for (int i = 0; i < anzahlSpieler; i++) {
            spieler[i] = new Spieler();
        }
    }

    public int getVersklavt() {
        return versklavt;
    }

    public int getGewonnen() {
        return gewonnen;
    }

    public int anzahlSpieler() {
        int n = 0;
        for (int i = 0; i < spieler.length; i++) {
            if (spieler[i] != null) {
                n++;
            }
        }
        return n;
    }

    public void auswertung(int z1, int z2) {
        if (spieler[z1].hatGewonnen()) {
            spieler[z1] = null;
            this.gewonnen++;
        } else if (spieler[z1].istVersklavt()) {
            spieler[z1] = null;
            this.versklavt++;
        }
        if (spieler[z2].hatGewonnen()) {
            spieler[z2] = null;
            this.gewonnen++;
        } else if (spieler[z2].istVersklavt()) {
            spieler[z2] = null;
            this.versklavt++;
        }
    }

    public boolean runde() {
        //Kann man noch spielen?
        if (anzahlSpieler() >= 2) {
            while (true) {
                int z1 = blatt13.Zufall.zufallGanz(0,spieler.length-1);
                int z2 = blatt13.Zufall.zufallGanz(0,spieler.length-1);

                if (z1 != z2 && (spieler[z1] != null) && (spieler[z2] != null)) {
                    Karte k1 = spieler[z1].spieleKarte();
                    Karte k2 = spieler[z2].spieleKarte();

                    int ergebnis = k1.compareTo(k2);
                    if (ergebnis == 0) {
                        //Unentschieden
                        this.auswertung(z1, z2);

                    } else if (ergebnis == -1) {
                        //Spieler 2 gewonnen
                        spieler[z1].sternVerlieren();
                        spieler[z2].sternErhalten();

                        this.auswertung(z1, z2);

                    } else {
                        //Spieler 1 gewonnen

                        spieler[z2].sternVerlieren();
                        spieler[z1].sternErhalten();

                        this.auswertung(z1, z2);

                    }
                    return true;

                }

            }
        }

        if (anzahlSpieler() == 1) {
            for (int i = 0; i < this.spieler.length; i++) {
                if (spieler[i] != null) {
                    spieler[i] = null;
                    this.versklavt++;
                }
            }
        }

        return false;
    }



}
