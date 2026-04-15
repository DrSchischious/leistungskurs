package blatt21;

public class Matrix {
    double[][] values;


    public void solve() {
        for (int i = 0; i < values.length; i++) {
            // Pivot suchen
            int max = i;
            for (int j = i + 1; j < values[i].length-1; j++) {
                if (Math.abs(values[j][i]) > Math.abs(values[max][i])) {
                    max = j;
                }
            }

            // Tauschen
            double[] temp = values[i];
            values[i] = values[max];
            values[max] = temp;

            // Eliminieren
            for (int j = i + 1; j < values[i].length-1; j++) {
                double factor = values[j][i] / values[i][i];
                for (int k = i; k < values.length; k++) {
                    values[j][k] -= factor * values[i][k];
                }
            }
        }
    }
}
