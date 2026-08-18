package blatt33;

import blatt32.aufgabe03.Graph;
import schgraphs.SchGraphs;

import java.util.ArrayList;

public class MST {

    public static Edge getSmallestEdge(ArrayList<Edge> edges) {
        Edge e = edges.get(0);
        for (Edge edge : edges) {
            if (e.compareTo(edge) > 0) {
                e = edge;
            }
        }
        return e;
    }

    public static void swapEdges(ArrayList<Edge> edges, Edge e1, Edge e2) {
        int index1 = edges.indexOf(e1);
        int index2 = edges.indexOf(e2);
        edges.set(index1, e2);
        edges.set(index2, e1);
    }

    public static void sortEdges(ArrayList<Edge> edges) {
        //BubbleSort
        for (int i = 0; i < edges.size() - 1; i++) {
            for (int j = 0; j < edges.size()-1; j++) {
                if (edges.get(j).compareTo(edges.get(j+1)) > 0) {
                    swapEdges(edges, edges.get(j), edges.get(j+1));
                }
            }
        }
    }

    public static ArrayList<Edge> edgeNeighbours(Graph g, int node) {
        ArrayList<Edge> edges = new ArrayList<>();
        int[] neighbours = g.getNeighbours(node);
        for (int i = 0; i < neighbours.length; i++) {
            edges.add(new Edge(node, neighbours[i], g.getAdjacencyMatrix()[node][neighbours[i]]));
        }
        return edges;
    }

    public static Graph primMST(Graph g, int startNode) {
        SchGraphs sg = new SchGraphs();

        Graph mst = new Graph(g.size());
        sg.step(mst.getAdjacencyMatrix());

        ArrayList<Integer> L = new ArrayList<Integer>();
        ArrayList<Integer> already = new ArrayList<Integer>();


        for (int i = 0; i < g.size(); i++) {
            L.add(i);
        }

        already.add(L.remove(startNode));


        while (!L.isEmpty()) {


            //Get Neighbours aller bisher hinzugefügten(?)
            ArrayList<Edge> possibleEdges = new ArrayList<>();
            for (Integer n : already) {
                possibleEdges.addAll(edgeNeighbours(g, n));
            }
            //Sortiere alle Kanten aus, die bereits hinzugefügte Knoten verbinden:

            ArrayList<Edge> removeEdges = new ArrayList<>();
            for (Edge e : possibleEdges) {
                if (already.contains(e.to)) {
                    removeEdges.add(e);

                }
            }
            possibleEdges.removeAll(removeEdges);

            //Nun noch sortieren:
            //sortEdges(possibleEdges);

            //Take smallest Edge.
            Edge e = getSmallestEdge(possibleEdges);
            mst.addEdge(e.from,e.to, e.weight);

            sg.step(mst.getAdjacencyMatrix());

            already.add(e.to);
            L.remove((Integer)(e.to));
        }

        sg.start();
        return mst;
    }

    public static Graph kruskalMST(Graph g) {
        SchGraphs sg = new SchGraphs();

        Graph mst = new Graph(g.size());
        sg.step(mst.getAdjacencyMatrix());

        ArrayList<Edge> possibleEdges = new ArrayList<>();
        //Get all the Edges! Throw away double-Edges

        for (int i = 0; i < g.size(); i++) {
            for (int j = i + 1; j < g.size(); j++) {
                if (g.getAdjacencyMatrix()[i][j] > 0) {
                    possibleEdges.add(new Edge(i,j,g.getAdjacencyMatrix()[i][j]));
                }
            }
        }

        sortEdges(possibleEdges);

        //Gesortet!

        for (Edge e : possibleEdges) {
            //Erreichbarkeits von FROM to TO
            boolean[] erreichbarkeit = GraphSuche.erreichbarkeit(mst,e.from);
            if (erreichbarkeit[e.to] == false) {
                //Nicht erreichbar! -> Verbinden
                mst.addEdge(e.from, e.to, e.weight);
                sg.step(mst.getAdjacencyMatrix());
            }
        }

        sg.start();
        return mst;
    }

    public static void main(String[] args) {
        Graph g = new Graph();

        g.importGraph("graph08");

        //System.out.println(isPath(g, "ABCD"));
        //System.out.println(isPath(g, "ABCDI"));

        //primMST(g, 0);
        kruskalMST(g);
    }
}
