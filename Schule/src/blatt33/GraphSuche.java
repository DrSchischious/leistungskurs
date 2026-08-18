package blatt33;

import blatt28.aufgabe02.Stapel;
import blatt28.aufgabe02.Warteschlange;
import blatt32.aufgabe03.Graph;
import schgraphs.SchGraphs;

import java.util.Stack;

public class GraphSuche {

    public static boolean isPath(Graph g, String path) {
        if (path == "") {
            return false;
        } else {
            int start = path.charAt(0) - 65;
            for (int i = 1; i < path.length(); i++) {
                int node = path.charAt(i) - 65;
                if (g.getAdjacencyMatrix()[start][node] <= 0) {
                    return false;
                }
                start = node;
            }
        }
        return true;
    }

    public static int pathLength(Graph g, String path) {
        int sum = 0;
        if (isPath(g, path) == false) {
            return -1;
        } else {
            int start = path.charAt(0) - 65;
            for (int i = 1; i < path.length(); i++) {
                int node = path.charAt(i) - 65;
                sum += g.getAdjacencyMatrix()[start][node];
                start = node;
            }
        }
        return sum;
    }

    public static boolean zyklensuche(Graph g, int startNode) {
        boolean hatZyklus = false;
        SchGraphs sg = new SchGraphs();
        sg.step(g.getAdjacencyMatrix());





        Stapel<Integer> stack = new Stapel<Integer>();
        boolean[] visited = new boolean[g.size()];
        int[] from = new int[g.size()];

        stack.push(startNode);

        visited[startNode] = true;

        int node = -1;
        while (!stack.empty()) {
            if (node != -1) {
                node = stack.pop();
                sg.colorEdge(from[node],node,'g',false);
            } else {
                node = stack.pop();
            }


            sg.colorNode(node, 'g');
            sg.step();

            int[] neighbours = g.getNeighbours(node);

            for (int i = 0; i < neighbours.length; i++) {
                if (visited[neighbours[i]] == false) {
                    stack.push(neighbours[i]);
                    visited[neighbours[i]] = true;
                    from[neighbours[i]] = node;
                } else {
                    hatZyklus = true;
                }
            }
        }
        sg.start();
        return hatZyklus;
    }

    public static boolean[] erreichbarkeit(Graph g, int startNode) {
        SchGraphs sg = new SchGraphs();
        sg.step(g.getAdjacencyMatrix());




        boolean start = true;
        Warteschlange<Integer> queue = new Warteschlange<>();
        boolean[] visited = new boolean[g.size()];


        int[] from = new int[g.size()];

        queue.add(startNode);

        visited[startNode] = true;

        int node = -1;
        while (!queue.empty()) {
            if (start == true) {
                start = false;
                node = queue.poll();
            } else {
                node = queue.poll();
                sg.colorEdge(from[node],node,'g',false);

            }


            sg.colorNode(node, 'g');
            sg.step();

            int[] neighbours = g.getNeighbours(node);

            for (int i = 0; i < neighbours.length; i++) {
                if (visited[neighbours[i]] == false) {
                    queue.add(neighbours[i]);
                    visited[neighbours[i]] = true;
                    from[neighbours[i]] = node;
                } else {

                }
            }
        }
        //sg.start();
        //Prüfe, ob alle erreicht wurden
        return visited;
    }

    public static void main(String[] args) {
        Graph g = new Graph();

        g.importGraph("graph05");

        //System.out.println(isPath(g, "ABCD"));
        //System.out.println(isPath(g, "ABCDI"));

        System.out.println(erreichbarkeit(g, 0));
    }
}
