package blatt34;

import blatt32.aufgabe03.Graph;

import java.util.ArrayList;

public class Dijkstra {

    public static int getShortest(ArrayList<Integer> L, int[][] wege) {
        int min = Integer.MAX_VALUE;
        int node = -1;
        for (int i = 0; i < L.size(); i++) {
            int look = L.get(i);
            if (wege[look][0] < min) {
                node = look;
                min = wege[look][0];
            }
        }
        return node;
    }

    public static int[][] dijkstra(Graph g, int start) {
        if (start < 0 || start >= g.size() || g == null) {
            throw new IllegalArgumentException();
        }

        //0.
        int[][] wege = new int[g.size()][2];
        boolean[] visited = new boolean[g.size()];

        for (int i = 0; i < g.size(); i++) {
            wege[i][0] = Integer.MAX_VALUE; //Unendlich
            wege[i][1] = -1; //Noch kein Vorgänger
            visited[i] = false;
        }

        //Initialisiere Startknoten
        wege[start][0] = 0;
        wege[start][1] = start;

        ArrayList<Integer> L = new ArrayList<Integer>();
        L.add(start);

        while (!L.isEmpty()) {
            Integer x = getShortest(L, wege);

            L.remove(x);
            visited[x] = true;

            int[] neighbours = g.getNeighbours(x);
            for (int j = 0; j < neighbours.length; j++) {
                Integer y = neighbours[j];
                if (visited[y] == false) {
                    L.add(y);
                }
                int alt = wege[y][0];
                int neu = wege[x][0] + g.getAdjacencyMatrix()[x][y];
                if (neu < alt) {
                    wege[y][0] = neu;
                    wege[y][1] = x;
                }
            }
        }

        return wege;
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.importGraph("graph08");

        System.out.println(g.size());

        int[][] wege = dijkstra(g, 0);

        for (int i = 0; i < wege.length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        System.out.println("Distanz zu:");
        for (int i = 0; i < wege.length; i++) {
            System.out.print(wege[i][0] + "\t");
        }
        System.out.println();
        System.out.println("Vorgaenger zu:");
        for (int i = 0; i < wege.length; i++) {
            System.out.print(wege[i][1] + "\t");
        }
        System.out.println();


    }
}
