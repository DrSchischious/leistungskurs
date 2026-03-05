package kursarbeit02;

import java.util.Random;

public class SquidGame {

    private static int seed;

    public static void initGame() {
        //Random
        seed = (int)(Math.random()*48);
    }

    public static boolean isGlass(char[][] spielfeld, int x, int y) {
        return SquidGameBackend.isGlass(spielfeld,x,y,seed);
    }

    public static void initStage() {

    }

    public static void round() {

    }

    public static int game() {
        return 0;
    }

    public static void main(String[] args) {
        initGame();
        game();
    }

}
