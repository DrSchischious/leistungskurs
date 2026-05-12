package blatt29.aufgabe02;

public class LinkList<T> {
    private Node<T> head;

    public LinkList() {
        this.head = null;
    }

    public int size() {
        Node<T> n = this.head;
        int count = 0;

        while(n != null) {
            count++;
            n = n.getNext();
        }

        return count;
    }

    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        } else {
            return false;
        }
    }

    public T get(int pos) {
        if (pos < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> n = this.head;
        while(pos > 0) {
            if (n != null) {
                pos--;
                n = n.getNext();
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
        if (n == null) {
            throw new IndexOutOfBoundsException();
        } else {
            return n.getValue();
        }
    }
    public boolean contains(T value) {
        Node<T> n = this.head;
        while (true) {
            if (n == null){
                return false;
            }
            if (n.getValue().equals(value)) {
                return true;
            }else {
                n = n.getNext();
            }
        }
    }
    public void add(T value) {
        Node<T> a = new Node<T>(value);
        if (this.head == null) {
            this.head = a;
        } else {
            Node<T> n = this.head;
            while (n.getNext() != null) {
                n = n.getNext();
            }
            n.setNext(a);
        }

    }

    public void add(T value, int pos) {
        if (pos < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> a = new Node<T>(value);

        Node<T> n = this.head;

        while(pos > 1) {
            if (n != null) {
                pos--;
                n = n.getNext();
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
        if (n == null) {
            throw new IndexOutOfBoundsException();
        } else {
            a.setNext(n.getNext());
            n.setNext(a);

        }
    }

    public T remove(int pos) {
        if (pos < 0) {
            throw new IndexOutOfBoundsException();
        }


        Node<T> n = this.head;

        while(pos > 1) {
            if (n != null) {
                pos--;
                n = n.getNext();
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
        if (n == null) {
            throw new IndexOutOfBoundsException();
        } else {
            T value = n.getNext().getValue();
            n.setNext(n.getNext().getNext());
            return value;
        }
    }

    public void clear() {
        this.head = null;
    }


    @Override
    public String toString() {
        String s = "->";
        if (this.head == null) {
            s = s+"null";
        } else {
            Node<T> n = this.head;
            while (n != null) {

                s = s+n.toString();
                n = n.getNext();
                if (n != null) {
                    s = s+"->";
                }
            }
        }

        return s;
    }

}
