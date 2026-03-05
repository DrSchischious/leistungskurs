package kursarbeit02;

public class Rec {
    public static int uni(int a, int b) {
        System.out.println("uni("+a+","+b+")");
        if (a+b <= 2) {
            System.out.println("Case 1");
            return a+b;
        } else if (a >= 2) {
            System.out.println("Case 2");
            return a + uni(a-2,b+1);
        } else if (b >= 4) {
            System.out.println("Case 3");
            return uni(a+1,b-2) + b;
        } else {
            System.out.println("Case 4");
            return uni(a,b-1) + uni(a-1,b);
        }
    }

    public static void main(String[] args) {
        System.out.println(uni(4,5));
    }


}
