package blatt20.aufgabe1;

public class Km {
    double bohnen;
    int wasser;
    int dreck;
    double kaffeesatz;

    public void bohnenAuffuellen(double maxBohnen) {
        this.bohnen = maxBohnen;
        if (this.bohnen > 350) {
            this.bohnen = 350;
        }
    }

    public void bohnenLeeren() {
        this.bohnen = 0;
    }

    public void wasserAuffuellen(int maxWasser) {
        this.wasser = maxWasser;
        if (this.wasser > 800) {
            this.wasser = 800;
        }
    }

    public void wasserLeeren() {
        this.wasser = 0;
    }

    public void wasserReinigen() {
        if (this.wasser == 0) {
            this.dreck = 0;
        } else {
            System.out.println("Error: WaterNotLeerException");
        }
    }

    public void kaffeesatzLeeren() {
        this.kaffeesatz = 0;
    }

    public void kaffeeZiehen(int intensitaet, int mengeAnKaffee) {
        //Dreck handle
        if (this.dreck > 90) {
            System.out.println("Zu viel Dreck. Mach den Dreck leer.");
            return;
        } //else if () {
    }
}
