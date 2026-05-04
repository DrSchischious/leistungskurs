package blatt28.aufgabe02;

import java.util.ArrayList;

public class Warteschlange<T> {
    private ArrayList<T> list;

    public Warteschlange() {
        this.list = new ArrayList<T>();
    }

    public boolean add(T element) {
        this.list.add(element);
        return true;
    }

    public T peek() {
        if (this.empty()) {
            return null;
        } else {
            return this.list.get(0);
        }
    }

    public T poll() {
        if (this.empty()) {
            return null;
        } else {
            return this.list.remove(0);
        }
    }

    public boolean empty() {
        return this.list.isEmpty();
    }

    public void duplicate() {

    }
}
