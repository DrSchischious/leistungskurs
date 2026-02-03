package blatt16;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReadWrite {
    public static void main(String[] args) {
        File f = new File("b.txt");
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Hello World\n");
            bw.write("Tim, danke!");
            bw.close();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
