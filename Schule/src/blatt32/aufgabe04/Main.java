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

    public static void graph5() {
        Graph g = new Graph(9);

        g.addEdge(0,1,3);
        g.addEdge(0,2,6);
        g.addEdge(1,2,2);
        g.addEdge(2,3,7);
        g.addEdge(2,4,4);
        g.addEdge(3,4,5);
        g.addEdge(3,5,3);
        g.addEdge(4,7,8);
        g.addEdge(7,6,9);
        g.addEdge(7,8,10);


        SchGraphs sg = new SchGraphs();
        sg.step(g.getAdjacencyMatrix());
        sg.start();
    }



    public static void graph6() {
        Graph g = new Graph(8);

        g.addEdge(0,1,true);
        g.addEdge(0,6,true);

        g.addEdge(1,0,true);
        g.addEdge(1,2,true);

        g.addEdge(2,3,true);

        g.addEdge(3,1,true);
        g.addEdge(3,0,true);

        g.addEdge(4,0,true);

        g.addEdge(5,4,true);

        g.addEdge(6,5,true);

        g.addEdge(5,6,true);

        g.addEdge(6,7,true);


        SchGraphs sg = new SchGraphs();
        sg.step(g.getAdjacencyMatrix());
        sg.start();
    }

    public static void graph7() {
        Graph g = new Graph(14);

        g.addEdge(0,1,true);
        g.addEdge(0,2,true);
        g.addEdge(0,3,true);

        g.addEdge(1,0,true);
        g.addEdge(1,4,true);

        g.addEdge(3,6,true);

        g.addEdge(4,6,true);
        g.addEdge(4,5,true);

        g.addEdge(5,8,true);
        g.addEdge(5,9,true);

        g.addEdge(6,4,true);
        g.addEdge(6,7,true);

        g.addEdge(7,6,true);

        g.addEdge(8,7,true);

        g.addEdge(9,12,true);

        g.addEdge(10,9,true);
        g.addEdge(10,5,true);
        g.addEdge(10,8,true);
        g.addEdge(10,11,true);
        g.addEdge(10,13,true);

        g.addEdge(11,5,true);

        g.addEdge(11,13,true);

        g.addEdge(12,9,true);

        g.addEdge(12,11,true);





        SchGraphs sg = new SchGraphs(12,10);
        sg.step(g.getAdjacencyMatrix());
        sg.start();
    }

    public static void graph8() {
        Graph g = new Graph(12);
        g.addEdge(0,7,2);
        g.addEdge(0,8,5);

        g.addEdge(1,3,2);
        g.addEdge(1,9,4);
        g.addEdge(1,10,4);

        g.addEdge(2,9,1);
        g.addEdge(2,5,2);

        g.addEdge(3,11,1);

        g.addEdge(4,11,9);
        g.addEdge(4,6,4);
        g.addEdge(4,8,2);
        g.addEdge(4,9,4);

        g.addEdge(5,9,3);

        g.addEdge(6,8,4);
        g.addEdge(6,7,2);

        g.addEdge(8,11,11);

        g.addEdge(9,10,5);

        g.exportGraph("graph08");
    }





    public static void main(String[] args) {
        graph8();
    }
}
