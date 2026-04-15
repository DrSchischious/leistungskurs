package blatt21;

public class Vektor {
    private double[] values;

    public Vektor(double[] values) {
        this.values = values;
    }

    public Vektor(double px, double py, double qx, double qy) {
        this.values = new double[]{qx-px,qy-py};
    }

    public Vektor(double px, double py, double pz, double qx, double qy, double qz) {
        this.values = new double[]{qx-px,qy-py,qz-pz};
    }

    public Vektor(double x, double y) {
        this.values = new double[]{x, y};
    }

    public Vektor(double x, double y, double z) {
        this.values = new double[]{x, y, z};
    }

    public double abs() {
        double sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i]*values[i];
        }
        return Math.sqrt(sum);
    }

    public void skalieren(double r) {
        for (int i = 0; i < values.length; i++) {
            values[i] = r*values[i];
        }
    }

    public double getValue(int k) {
        if (k >= 0 && k < values.length) {
            return values[k];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int getDimension() {
        return values.length;
    }

    //TODO: toString
    public String toString() {
        String s = "(";
        return s;
    }
}
