package blatt08;

public class Palindrom {

    public static String umdrehen(String wort) {
        String s = "";
        char[] charr = wort.toCharArray();
        for (int i = 0; i < charr.length; i++) {
            s = charr[i]+s;
        }
        return s;
    }

    public static boolean istPalindrom(String wort) {
        if (wort.toLowerCase().equals(umdrehen(wort).toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(umdrehen("Hallo"));
        System.out.println(istPalindrom("Hallo"));
        System.out.println(istPalindrom("Anna"));
        System.out.println(istPalindrom("Ana"));
    }
}
