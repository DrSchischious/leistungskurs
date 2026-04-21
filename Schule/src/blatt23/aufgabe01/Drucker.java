package blatt23.aufgabe01;

public class Drucker {
    private Verbindungsmodus verbindungsmodus;
    private int papierfach;
    private Geraetemodus geraetemodus;
    private boolean aufVorlagenglas;
    private double tinteSW;
    private double tinteFarbe;
    private Abonnement abo;

    private int anzahlOfflineSeiten;
    private int restKontingent;

    public Drucker(Abonnement abo) {
        this.abo = abo;
        this.verbindungsmodus = Verbindungsmodus.ONLINE;
        this.papierfach = 0;
        this.geraetemodus = Geraetemodus.STANDBY;
        this.aufVorlagenglas = false;
        this.tinteSW = 100;
        this.tinteFarbe = 100;
        this.anzahlOfflineSeiten = 0;
        this.restKontingent = this.abo.kontingent;
    }

    public Geraetemodus getGeraetemodus() {
        return geraetemodus;
    }

    public Verbindungsmodus getVerbindungsmodus() {
        return verbindungsmodus;
    }

    public int getPapierfach() {
        return papierfach;
    }

    public boolean isAufVorlagenglas() {
        return aufVorlagenglas;
    }

    public double getTinteSW() {
        return tinteSW;
    }

    public double getTinteFarbe() {
        return tinteFarbe;
    }

    public Abonnement getAbo() {
        return abo;
    }

    public int getAnzahlOfflineSeiten() {
        return anzahlOfflineSeiten;
    }

    public void zuOnline() {
        this.verbindungsmodus = Verbindungsmodus.ONLINE;
        this.anzahlOfflineSeiten = 0;
    }

    public void zuOffline() {
        this.verbindungsmodus = Verbindungsmodus.OFFLINE;
    }

    public void zuLokal() {
        this.verbindungsmodus = Verbindungsmodus.LOKAL;
    }

    public void papierAuffuellen(int seiten) {
        if (seiten > 0) {
            if (this.papierfach + seiten > 250) {
                this.papierfach = 250;
            } else {
                this.papierfach += seiten;
            }
        } else {
            System.out.println("Seitenanzahl muss positiv sein.");
        }
    }

    public void legeAufGlas() {
        this.aufVorlagenglas = true;
    }

    public void entferneVonGlas() {
        this.aufVorlagenglas = false;
    }

    public boolean scannen() {
        if (this.verbindungsmodus == Verbindungsmodus.ONLINE || this.verbindungsmodus == Verbindungsmodus.LOKAL) {
            if (this.aufVorlagenglas) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private void tinteAuffuellen(Patronenart patronenart) {
        if (patronenart == Patronenart.SCHWARZ) {
            this.tinteSW = 100;
        } else if (patronenart == Patronenart.FARBE) {
            this.tinteFarbe = 100;
        } else {
            System.out.println("Unbekannter Tintenfehler.");
        }
    }

    public boolean aboWechseln(int i) {
        if (i < 0 || i > 5) {
            return false;
        } else {
            if (i == 1) {
                this.abo = Abonnement.EXTRASMALL;
            } else if (i == 2) {
                this.abo = Abonnement.SMALL;
            } else if (i == 3) {
                this.abo = Abonnement.MEDIUM;
            } else if (i == 4) {
                this.abo = Abonnement.LARGE;
            } else {
                this.abo = Abonnement.EXTRALARGE;
            }
            neuesKontingent();
            return true;
        }
    }

    private void neuesKontingent() {
        this.restKontingent += this.abo.kontingent;
    }

    public void neuerMonat() {
        this.neuesKontingent();
    }

    public void drucken(Druckart druckart, Druckmodus modus, int seiten) {
        //Berechne Verbrauch
        double verbrauchSW = 0;
        double verbrauchFarbe = 0;

        if (modus == Druckmodus.SCHWARZWEISS) {
            if (druckart == Druckart.SPAREN) {
                verbrauchSW = 0.3*seiten;

            } else if (druckart == Druckart.NORMAL) {
                verbrauchSW = 0.5*seiten;

            } else {
                verbrauchSW = 0.8*seiten;

            }
        } else if (modus == Druckmodus.FARBE) {
            if (druckart == Druckart.SPAREN) {
                verbrauchSW = 0.1*seiten;
                verbrauchFarbe = 0.3*seiten;
            } else if (druckart == Druckart.NORMAL) {
                verbrauchSW = 0.2*seiten;
                verbrauchFarbe = 0.6*seiten;
            } else {
                verbrauchSW = 0.3*seiten;
                verbrauchFarbe = 1.1*seiten;
            }
        }


        //Check if possible
        if (seiten > 0) {
            if (this.verbindungsmodus != Verbindungsmodus.ONLINE && seiten + this.anzahlOfflineSeiten <= 20) {
                if (seiten <= this.papierfach) {
                    if (seiten <= this.restKontingent) {
                        if (verbrauchFarbe <= this.tinteFarbe) {
                            if (verbrauchSW <= this.tinteSW) {
                                //Everything is fine.
                                this.tinteFarbe -= verbrauchFarbe;
                                this.tinteSW -= verbrauchSW;
                                this.papierfach -= seiten;
                                if (this.verbindungsmodus != Verbindungsmodus.ONLINE) {
                                    this.anzahlOfflineSeiten += seiten;
                                }
                                Druckauftrag a = new Druckauftrag((int)(Math.random()*10000),seiten,modus,druckart,this.tinteSW, this.tinteFarbe);
                                //Senden an Zentrale

                            }
                        }
                    }
                }
            }
        }

        //Never-Nester (Alternativfall)
        if (seiten <= 0) {
            //Error
            return ;
        }
        if (this.verbindungsmodus == Verbindungsmodus.ONLINE && seiten + this.anzahlOfflineSeiten > 20) {
            //Error
            return ;
        }
        if (seiten > this.papierfach) {

            return;
        }

        if (seiten > this.restKontingent) {
            return;
        }
        if (verbrauchFarbe > this.tinteFarbe) {
            return;
        }
        if (verbrauchSW > this.tinteSW) {
            return;
        }
        //Everything is fine.
        this.tinteFarbe -= verbrauchFarbe;
        this.tinteSW -= verbrauchSW;
        this.papierfach -= seiten;
        if (this.verbindungsmodus != Verbindungsmodus.ONLINE) {
            this.anzahlOfflineSeiten += seiten;
        }
        Druckauftrag a = new Druckauftrag((int)(Math.random()*10000),seiten,modus,druckart,this.tinteSW, this.tinteFarbe);
        //Senden an Zentrale


    }


}
