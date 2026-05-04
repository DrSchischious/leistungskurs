package blatt28.aufgabe02;

import java.util.ArrayList;

public class Stapel<T>{
    private ArrayList<T> list;

    public Stapel() {
        this.list = new ArrayList<T>();
    }

    public boolean empty() {
        return this.list.isEmpty();
    }

    public T peek() {
        if (empty()) {
            return null;
        }  else {
            return this.list.get(this.list.size()-1);
        }
    }

    public T pop() {
        if (empty()) {
            return null;
        } else {
            return this.list.remove(this.list.size()-1);
        }
    }

    public void push(T element) {
        this.list.add(element);
    }

    public void duplicate() {
        if (empty()) {

        } else {
            T dopp = this.pop();
            this.push(dopp);
            this.push(dopp);
        }
    }

    public void swap() {
        if (empty()) {

        } else {
            T first = this.pop();
            if (empty()) {
                this.push(first);
            } else {
                T second = this.pop();
                this.push(first);
                this.push(second);
            }
        }
    }

    public void rotateRight(int n) {
        if (empty()) {

        } else {
            Stapel<T> zwischen = new Stapel<T>();
            T first = this.pop();
            for (int i = 0; i < n-1; i++) {
                zwischen.push(this.pop());
            }
            zwischen.push(first);

            for (int i = 0; i < n; i++) {
                this.push(zwischen.pop());
            }
        }
    }

    public void rotateLeft(int n) {
        if (empty()) {

        } else {
            Stapel<T> zwischen = new Stapel<T>();

            for (int i = 0; i < n-1; i++) {
                zwischen.push(this.pop());
            }
            T last = this.pop();

            for (int i = 0; i < n-1; i++) {
                this.push(zwischen.pop());
            }

            this.push(last);

        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.list.size(); i++) {
            s += this.list.get(i).toString();
        }
        return s;
    }
}
