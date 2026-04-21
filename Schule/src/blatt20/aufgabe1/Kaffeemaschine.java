package blatt20.aufgabe1;

public class Kaffeemaschine {
    private double bohnen;
    private int wasser;
    int dreck;
    int kaffeesatz;

    public void bohnenAuffuellen(int bohnen) {
        if (bohnen > 0) {
            if (this.bohnen + bohnen >= 350) {
                this.bohnen = 350;
            } else {
                this.bohnen += bohnen;
            }
        }
    }

    public void bohnenLeeren() {
        this.bohnen = 0;
    }

    public void wasserAuffuellen(int wasser) {
        if (wasser > 0) {
            if (this.wasser + wasser >= 800) {
                this.wasser = 800;
            } else {
                this.wasser += wasser;
            }
        }
    }

    public void wasserLeeren() {
        this.wasser = 0;
    }

    public void wasserReinigen() {
        this.dreck = 0;
    }

    public void kaffeesatzLeeren() {
        this.kaffeesatz = 0;
    }

    public void kaffeeZiehen(int intensitaet, int menge) {
        if (intensitaet < 1 || intensitaet > 10 || menge < 10 || menge > 200) {
            //Fehlermeldung
            System.out.println("Fehlerhafte Einstellung");
        } else {
            if (this.bohnen < (double)(intensitaet*0.5*menge / 10)) {
                //Fehlermeldung
                System.out.println("Es sind nicht genügend Kaffeebohnen in der Maschine vorhanden.");
            } else if (this.wasser < menge) {
                System.out.println("Es ist nicht genügend Wasser in der Maschine.");
            } else if (this.kaffeesatz == 600) {
                System.out.println("Der Kaffeesatzbehälter muss geleert werden.");
            } else if (this.dreck >= 90) {
                System.out.println("Die Maschine muss gesäubert werden, ehe sie weiterverwendet werden kann.");
            } else {
                //Wahrscheinlichkeit:
                if (this.dreck >= 75) {
                    if (Math.random() <= 0.5) {
                        System.out.println("Aufgrund der Verschmutzung kam es zu einem Fehler.");
                    }
                } else if (this.dreck >= 50) {
                    if (Math.random() <= 0.25) {
                        System.out.println("Aufgrund der Verschmutzung kam es zu einem Fehler.");
                    }
                } else {
                    //Kaffee wird gezogen!
                    this.bohnen = this.bohnen - intensitaet*0.5*menge;
                    this.dreck++;
                    if (this.dreck > 100) {
                        this.dreck = 100;
                    }
                    this.kaffeesatz = (int)(this.kaffeesatz + intensitaet*0.5*menge);
                    if (this.kaffeesatz > 600) {
                        this.kaffeesatz = 600;
                    }
                    this.wasser = this.wasser - menge;
                    System.out.println("Es wurde ein Kaffee in der Größe von " + menge + " ml mit einer Intesität von " + intensitaet + " gezogen.");
                }
            }
        }
    }

    public void status() {
        System.out.println("Kaffebohnen: " + this.bohnen + " g");
        System.out.println("Wasser: " + this.wasser + " ml");
        System.out.println("Dreck: " + this.dreck + " %");
        System.out.println("Kaffeesatz: " + this.kaffeesatz + " g");
    }
}
