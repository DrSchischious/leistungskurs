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
        if (visited == null && vorgaenger == null) {
            visited = new boolean[g.size()];
            vorgaenger = new int[g.size()];
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

    public static int maxFluss(Graph g, String path) {
        int max = 10000;


        for (int c = 0; c < path.length()-1; c++) {
            char from = path.charAt(c);
            char to = path.charAt(c+1);
            int weight = g.getAdjacencyMatrix()[from-48][to-48];
            if (weight < max) {
                max = weight;
            }
        }
        return max;
    }

    public static void addFluss(int[][] fluss, String path, int maxFluss) {
        for (int c = 0; c < path.length()-1; c++) {
            char from = path.charAt(c);
            char to = path.charAt(c+1);
            fluss[from-48][to-48] += maxFluss;
        }
    }

    public static void removeFluss(Graph residual, String path, int maxFluss) {
        for (int c = 0; c < path.length()-1; c++) {
            char from = path.charAt(c);
            char to = path.charAt(c+1);

            residual.reduceEdge(from-48,to-48,maxFluss);
            residual.improveEdge(to-48,from-48,maxFluss);
        }
    }



    public static void fordFulkerson(Graph g) {
        SchGraphs sg = new SchGraphs(true);
        //Step 1: Initialisierung & Nullfluss
        int[][] fluss = new int[g.size()][g.size()];
        Graph resi = new Graph(g);

        sg.step(g.getAdjacencyMatrix(),fluss);

        //Step 2: Solange es Pfad in Restgraph gibt
        String pfad = "";
        pfad = pfadsuche(resi,0,g.size()-1,null,null);
        while (!pfad.equals("")) {
            //Step 2.1: Bestimme maximaler Fluss
            int maxFluss = maxFluss(g,pfad);
            //Step 2.2: Füge Fluss hinzu
            addFluss(fluss,pfad,maxFluss);
            //Step 2.3: Entferne Fluss von Restgraph
            //Step 2.4: Füge umgekehrte Kanten hinzu
            removeFluss(resi, pfad, maxFluss);


            sg.step(g.getAdjacencyMatrix(),fluss);

            pfad = pfadsuche(resi,0,g.size()-1,null,null);
        }

        sg.start();



    }

    public static void main(String[] args) {
        Graph g = new Graph(7);
        g.addEdge(0,1,6,true);
        g.addEdge(0,2,3,true);
        g.addEdge(1,3,4,true);
        g.addEdge(2,3,4,true);

        g.addEdge(3,4,10,true);
        g.addEdge(3,5,6,true);

        g.addEdge(3,6,2,true);
        g.addEdge(4,6,2,true);
        g.addEdge(5,6,2,true);


        fordFulkerson(g);
    }
}
