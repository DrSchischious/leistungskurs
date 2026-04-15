package blatt21;

import blatt14.MultiArrays;

public class Main {
    public static void main(String[] args) {
        Matrix m = new Matrix();
        m.values = new double[][]{new double[]{4,-2,2,2},new double[]{-2,3,-2,0}, new double[]{3,-5,1,-7}};
        MultiArrays.print2DArray(m.values);
        m.solve();
        MultiArrays.print2DArray(m.values);
    }
}
