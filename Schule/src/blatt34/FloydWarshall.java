package blatt34;

import blatt32.aufgabe03.Graph;

import java.util.ArrayList;

public class FloydWarshall {

    public static int[][][] floydWarshall(Graph g) {
        int[][] adj = g.getAdjacencyMatrix();
        int[][][] wege = new int[g.size()][g.size()][2];
        for (int i = 0; i < g.size(); i++) {
            for (int j = 0; j < g.size(); j++) {
                if (adj[i][j] <= 0) {
                    wege[i][j][0] = Integer.MAX_VALUE;
                    wege[i][j][1] = -1;
                } else if (i == j) {
                    wege[i][j][0] = 0;
                    wege[i][j][1] = i;
                }else {
                    wege[i][j][0] =adj[i][j];
                    wege[i][j][1] = i;
                }

            }
        }

        ArrayList<Integer> L = new ArrayList<Integer>();
        for (int i = 0; i < g.size(); i++) {
            L.add(i);
        }

        while (!L.isEmpty()) {
            Integer y = L.remove(0);

            for (int x = 0; x < g.size(); x++) {
                for (int z = 0; z < g.size(); z++) {
                    if (wege[x][y][0] + wege[y][z][0] < wege[x][z][0]) {
                        //System.out.println("Found better way from " + x + " to " + z + " via " + y);
                        wege[x][z][0] = wege[x][y][0] + wege[y][z][0];
                        wege[x][z][1] = wege[y][z][1];
                    }
                }
            }
        }

        return wege;
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.importGraph("graph08");

        System.out.println(g.size());

        int[][][] wege = floydWarshall(g);

        //TODO: Ausgabe

    }
}
