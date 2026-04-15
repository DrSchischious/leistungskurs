package blatt21;

public class ArbeitMitVektoren {

    public static Vektor linearKombination(double a, Vektor v1, double b, Vektor v2) {
        v1.skalieren(a);
        v2.skalieren(b);
        return add(v1,v2);
    }

    public static Vektor linearKombination(double a, Vektor v1, double b, Vektor v2, double c, Vektor v3) {
        v1.skalieren(a);
        v2.skalieren(b);
        v3.skalieren(c);
        Vektor v = add(v1,v2);
        return add(v,v3);
    }


    public static Vektor add(Vektor v1, Vektor v2) {
        if (v1.getDimension() != v2.getDimension()) {
            return null;
        } else {
            if (v1.getDimension() == 2) {
                return new Vektor(v1.getValue(0)+v2.getValue(0), v1.getValue(1)+v2.getValue(1));
            } else if (v1.getDimension() == 3) {
                return new Vektor(v1.getValue(0)+v2.getValue(0), v1.getValue(1)+v2.getValue(1), v1.getValue(2)+v2.getValue(2));
            } else {
                return null;
            }
        }
    }
}
