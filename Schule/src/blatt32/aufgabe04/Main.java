package blatt32.aufgabe04;

import blatt32.aufgabe03.Graph;
import schgraphs.*;

public class Main {

    public static void graph1() {


        Graph g = new Graph(5);



        g.addEdge(0,1);

        g.addEdge(0,2);
        g.addEdge(0,3);
        g.addEdge(0,4);



        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(1,4);

        g.addEdge(2,3);
        g.addEdge(2,4);

        g.addEdge(3,4);



        SchGraphs sg = new SchGraphs();
        sg.step(g.getAdjacencyMatrix());
        sg.colorNode(3,'c');
        sg.colorNode(4,'r');
        sg.step();
        sg.colorEdge(3,4,'r',false);
        sg.step();
        sg.start();

    }

    public static void graph2() {
        Graph g = new Graph(4);
        g.addEdge(2,3,true);
        g.addEdge(0,3,true);

        SchGraphs sg = new SchGraphs();
        sg.step(g.getAdjacencyMatrix());
        sg.start();
    }

    public static void graph3() {
        Graph g = new Graph(4);

        g.addEdge(1,3,4);
        g.addEdge(2,3,8);
        g.addEdge(0,3,11);

        SchGraphs sg = new SchGraphs();
        sg.step(g.getAdjacencyMatrix());
        sg.start();
    }

    public static void graph4() {
        Graph g = new Graph(3);

        g.addEdge(0,1,2,true);
        g.addEdge(0,2,4,true);
        g.addEdge(1,0,7,true);
        g.addEdge(1,2,5,true);
        g.addEdge(2,0,11,true);
        g.addEdge(2,1,19,true);


        SchGraphs sg = new SchGraphs();
        sg.step(g.getAdjacencyMatrix());
        sg.start();
    }

    public static void main(String[] args) {
        graph4();
    }
}
