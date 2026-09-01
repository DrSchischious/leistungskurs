package blatt35.aufgabe02;

import blatt28.aufgabe02.Stapel;
import blatt32.aufgabe03.Graph;
import schgraphs.SchGraphs;

public class FordFulkerson {

    /**
     * Abwandlung von Tiefensuche
     *
     */
    public static String pfadsuche(Graph g, int startNode, int zielNode, boolean[] visited, int[] vorgaenger) {
        if (visited == null) {
            visited = new boolean[g.size()];
            visited[startNode] = true;
            vorgaenger[startNode] = startNode;
        }
        int[] neighbours = g.getNeighbours(startNode);
        //Alle Nachbaren
        //Setze deren Vorgänger als startNode
        for (int i = 0; i < neighbours.length; i++) {
            if (!visited[neighbours[i]]) {
                visited[neighbours[i]] = true;
                vorgaenger[neighbours[i]] = startNode;
                pfadsuche(g, neighbours[i], zielNode, visited, vorgaenger);
            }
        }



        //Get Path
        if (visited[zielNode] == true) {
            int node = zielNode;
            String path = "" + zielNode;

            while (true) {
                if (node == vorgaenger[node]) {
                    break;
                }
                node = vorgaenger[node];
                path = node + path;
            }
            return path;
        } else {
            return "";
        }
    }

    public static void fordFulkerson(Graph g) {
        //Step 1: Initialisierung & Nullfluss

        //Step 2: Solange es Pfad in Restgraph gibt

        //Step 2.1: Bestimme maximaler Fluss

        //Step 2.2: Füge Fluss hinzu

        //Step 2.3: Entferne Fluss von Restgraph

        //Step 2.4: Füge umgekehrte Kanten hinzu



    }

    public static void main(String[] args) {

    }
}
