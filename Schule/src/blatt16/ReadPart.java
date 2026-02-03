package blatt16;

import java.io.*;

public class ReadPart {
    public static void main(String[] args) {
        File f = new File("b.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while (br.ready()) {
                System.out.println(br.readLine());
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
