package blatt01;

public class Zinssatz {
    public static void main(String[] args) {
        double kapital = 1220.0;
        double zinssatz = 2.6395;
        double zinsen = kapital * zinssatz / 100;
        double kapitalNeu = kapital + zinsen;

        System.out.println("Altes Kapital: " + kapital + " €");
        System.out.println("Zinssatz: " + zinssatz + " %");
        System.out.println();
        System.out.printf("Zinsen: %.2f €\n", zinsen);
        //System.out.println("Zinsen: " + zinsen + " €");
        System.out.println("Neues Kapital: " + kapitalNeu + " €");
    }
}
