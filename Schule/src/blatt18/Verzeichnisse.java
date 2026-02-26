package blatt18;

import java.io.File;

public class Verzeichnisse {

    public static void showDirectory(String path, int depth) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (int i = 0; i < depth; i++) {
            System.out.print("\t");
        }
        System.out.println(dir.getName());

        if (files != null) {
            for (File f : files) {
                showDirectory(f.getAbsolutePath(), depth + 1);
            }
        }

    }

    public static void main(String[] args) {
        showDirectory("C:\\Users\\cloud\\OneDrive\\Schule\\Informatik Materialien",0);
    }
}
