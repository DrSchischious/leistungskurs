package blatt27.aufgabe01;

/**
 * Diese Klasse dient zur Dynamisierung eines Arrays.
 */
public class DynArray {

    private int[] arr;

    /**
     * Default-Konstruktor
     * Legt leeren Array an.
     */
    public DynArray() {
        this.arr = new int[0];
    }

    public boolean isEmpty() {
        if (this.arr.length == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return this.arr.length;
    }

    public int get(int pos) {
        if (pos > this.size()-1 || pos < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return this.arr[pos];
        }
    }

    public boolean contains(int zahl) {
        for (int i = 0; i < this.size(); i++) {
            if (this.arr[i] == zahl) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(int zahl) {
        for (int i = 0; i < this.size(); i++) {
            if (this.arr[i] == zahl) {
                return i;
            }
        }
        return -1;
    }

    public void add(int zahl) {
        int[] arrN = new int[this.size()+1];
        for (int i = 0; i < this.size(); i++) {
            arrN[i] = this.arr[i];
        }
        arrN[this.size()] = zahl;
        this.arr = arrN;
    }

    public void add(int zahl, int pos) {
        if (pos < 0 || pos > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            int[] arrN = new int[this.size() + 1];
            for (int i = 0; i < this.size(); i++) {
                if (i < pos) {
                    arrN[i] = this.arr[i];
                } else if (i == pos) {
                    arrN[i] = zahl;
                } else {
                    arrN[i] = this.arr[i - 1];
                }
            }
            this.arr = arrN;
        }
    }

    public void set(int zahl, int pos) {
        if (pos < 0 || pos > this.size()-1) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            this.arr[pos] = zahl;
        }
    }

    public int remove(int pos) {
        if (pos < 0 || pos > this.size()-1) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            int[] arrN = new int[this.size()-1];
            int element = 0;
            for (int i = 0; i < this.size(); i++) {
                if (i < pos) {
                    arrN[i] = this.arr[i];
                } else if (i == pos) {
                    element = arr[i];
                } else {
                    arrN[i] = this.arr[i+1];
                }
            }
            this.arr = arrN;
            return element;
        }
    }

    public void clear() {
       this.arr = new int[0];
    }











    @Override
    public String toString() {
        String s = "[ ";
        for (int i = 0; i < this.arr.length; i++) {
            if (i != this.arr.length - 1) {
                s += this.arr[i] + " | ";
            } else {
                s += this.arr[i];
            }
        }
        s = s + " ]";

        return s;
    }
}
