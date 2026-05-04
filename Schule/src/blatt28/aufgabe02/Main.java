package blatt28.aufgabe02;

public class Main {

    public static void main(String[] args) {
        Stapel<Integer> s1 = new Stapel<Integer>();
        Stapel<String> s2 = new Stapel<String>();

        s1.push(1);
        s1.push(2);
        s1.push(3);

        System.out.println(s1);
        s1.rotateRight(3);
        System.out.println(s1);
        s1.rotateLeft(3);
        System.out.println(s1);
        s1.rotateLeft(3);
        System.out.println(s1);

        s1.pop();
        System.out.println(s1);

        s2.push("Hallo");
        s2.push("Max");
        s2.push("Noah");

        System.out.println(s2);
        s2.pop();
        System.out.println(s2);

    }
}
