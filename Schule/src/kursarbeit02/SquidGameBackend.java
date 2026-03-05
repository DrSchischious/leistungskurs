package kursarbeit02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SquidGameBackend {
    private static boolean checkState(char[][] arr) {
        if (arr == null) {
            return false;
        }
        if (arr.length != 15) {
            return false;
        }
        if (arr[0].length != 5) {
            return false;
        }
        int count = 0;
        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr[x].length; y++) {
                if (arr[x][y] == 'P') {
                    count++;
                    if (count > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean isGlass(char[][] spielfeld, int x, int y, int seed) {
        if (checkState(spielfeld)) {
            if (spielfeld[x][y] == 'P') {
                //Check for Data
                if (checkGlass(x,y,seed) == 'x') {
                    return true;
                } else {
                    return false;
                }
            } else {
                throw new FieldNotValidException("There is no player on that position.");
            }
        } else {
            throw new FieldNotValidException("The Field is not of correct size or there is no player/more than one player.");
        }
    }

    private static char checkGlass(int x, int y, int seed) {
        try {
            File f = new File("io\\xo.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            int i = 0;
            while (i < seed+y) {
                br.readLine();
                i++;
            }
            return br.readLine().charAt(x);

        } catch (IOException e){
            return ' ';
        }


    }
}
