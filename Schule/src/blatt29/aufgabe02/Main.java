package blatt29.aufgabe02;

public class Main {

    public static void main(String[] args) {

        LinkList<Integer> ll = new LinkList<Integer>();
        System.out.println(ll);

        ll.add(4);
        System.out.println(ll);
        ll.add(3);
        System.out.println(ll);
        ll.add(7);
        System.out.println(ll);
        ll.add(3);
        System.out.println(ll);
        ll.add(15,2);
        System.out.println(ll);
        ll.remove(3);
        System.out.println(ll);

    }
}
