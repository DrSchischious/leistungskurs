package blatt16;

import blatt14.MultiArrays;
import schisch_visualizer.SchischVisualizer;

import java.io.*;

public class Stroeme {

    /**
     * Liest einen zweidimensionalen char-Array aus einer .txt aus und gibt diesen zurück.
     * @param path Dateipfad der auszulesenden Datei
     * @return zweidimensionaler char-Array
     */
    public static char[][] readCharArray(String path) {
        if (!path.contains(".txt")) {
            return null;
        } else {

            char[][] charr = new char[1][1];
            try {
                File f = new File(path);
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                int a = -1;
                int b = -1;

                String s = "";
                while (br.ready()) {
                    if (a == -1) {
                        a = Integer.parseInt(br.readLine());
                    } else if (b == -1) {
                        b = Integer.parseInt(br.readLine());
                        charr = new char[a][b];
                    } else {
                        s = s + br.readLine();
                    }
                }
                int c = 0;
                for (int i = 0; i < charr.length; i++) {
                    for (int j = 0; j < charr[i].length; j++) {
                        charr[i][j] = s.charAt(c);
                        c++;
                    }
                }
                br.close();
                fr.close();
                return charr;
            } catch(IOException e) {
                return null;
            }

        }

    }

    /**
     * Schreibt einen zweidimensionalen char-Array in eine .txt-Datei.
     * @param path Dateipfad der zu speichernden Datei
     * @param charr zu speichender char-Array
     */
    public static void writeCharArray(String path, char[][] charr) {
        File f = new File(path);
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("" + charr.length);
            bw.newLine();
            bw.write(""+charr[0].length);
            bw.newLine();
            for (int i = 0; i < charr.length; i++) {
                for (int j = 0; j < charr[i].length; j++) {
                    bw.write("" + charr[i][j]);
                    bw.newLine();
                }
            }
            bw.close();
            fw.close();
        } catch(IOException e) {

        }

    }

    public static void main(String[] args) {

    }
}
