package blatt23.aufgabe01;

public enum Abonnement {

    EXTRASMALL(0.99,10),
    SMALL(3.99,50),
    MEDIUM(5.99,100),
    LARGE(11.99,300),
    EXTRALARGE(24.99,700);

    final double preis;
    final int kontingent;

    Abonnement(double preis, int kontingent) {
        this.preis = preis;
        this.kontingent = kontingent;
    }


}
