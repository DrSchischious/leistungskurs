package blatt30.aufgabe02;

public class Main {
    public static void main(String[] args) {
        BinBaum b = new BinBaum();
        b.add(8);
        b.add(6);
        b.add(7);
        b.add(-2);
        b.add(-4);
        b.add(0);

        b.add(15);
        b.add(12);
        b.add(10);
        b.add(11);
        b.add(13);
        b.add(19);
        b.add(17);
        b.add(18);
        b.add(1);

        System.out.println(b.depth());
        System.out.println(b.count());

        b.remove(-2);


        //b.remove(-4);

        System.out.println(b.depth());
        System.out.println(b.count());



    }


}
