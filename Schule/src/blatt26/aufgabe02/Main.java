package blatt26.aufgabe02;

public class Main {
    public static void main(String[] args) {
        Prozessor p = new Prozessor(8,3.55,4);
        Computer c = new Computer(p,8,1000);

        Prozessor p2 = new Prozessor(8,3.55,2);
        Computer c2 = new Computer(p,4,512);

        System.out.println(c2.compareTo(c));
        System.out.println(c.compareTo(c2));


    }
}
