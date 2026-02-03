package blatt16;

import java.io.File;

public class ErrorHandling {
    public static void main(String[] args) {
        int[] arr = new int[8];

        try {
            System.out.println(arr[8]);
        } catch(ArrayIndexOutOfBoundsException j) {

        }


        //System.out.println(arr[0]);


        File f = new File("nichtHallo.txt");

        System.out.println(f.exists());
        System.out.println(f.isFile());
        System.out.println(f.isDirectory());
        System.out.println(f.mkdir());
        System.out.println(f.getPath());
        System.out.println(f.getAbsolutePath());
        System.out.println(f.lastModified());
        System.out.println(f.canRead());
        System.out.println(f.canWrite());

        //f.renameTo(new File("nichtHallo.txt"));
        //f.delete();
        File f2 = new File("C:\\Users\\DanielSchisch.AzureAD\\IdeaProjects\\leistungskurs\\");
        blatt07.ArbeitMitArrays.printArray(f2.list());
        for (String d : f2.list()) {
            File f3 = new File(d);
            if (f3.isDirectory()) {
                System.out.println("Verzeichnis");
            } else {
                System.out.println("Datei");
            }
        }

    }

}
