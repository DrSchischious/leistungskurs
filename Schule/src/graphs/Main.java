package graphs;

import schgraphs.SchGraphs;

public class Main {

    /**
     * Graph
     * 10 Knoten
     */
    public static void test1() {
        SchGraphs sg = new SchGraphs(true);


        int[][] graph;

        Graph g = new Graph(true);
        g.addVertex(); //s
        g.addVertex(); //A
        g.addVertex(); //B
        g.addVertex(); //C
        g.addVertex(); //D
        g.addVertex(); //t

        sg.step(g.getAdjacencyMatrix(), g.getEmptyFlow());

        g.addEdge(0,1,10);
        g.addEdge(0,2,8);
        g.addEdge(1,2,5);
        g.addEdge(1,3,5);
        g.addEdge(2,3,4);
        g.addEdge(2,4,2);
        g.addEdge(3,4,2);
        g.addEdge(3,5,8);
        g.addEdge(4,5,8);

        sg.step(g.getAdjacencyMatrix(), g.getEmptyFlow());

        int[][] flow = g.getEmptyFlow();
        flow[0][1] = 10;

        sg.step(g.getAdjacencyMatrix(), flow);

        sg.start();
    }

    /**
     * Flussproblem
     */
    public static void test2() {
        SchGraphs sg = new SchGraphs(10,7);


        int[][] graph;

        Graph g = new Graph(false);
        g.addVertex();
        sg.step(g.getAdjacencyMatrix());
        g.addVertex();
        sg.step(g.getAdjacencyMatrix());
        g.addVertex();
        sg.colorNode(0, 'c');
        sg.step(g.getAdjacencyMatrix());
        g.addVertex();
        sg.step(g.getAdjacencyMatrix());
        g.addVertex();
        sg.colorNode(2,'y');
        sg.step(g.getAdjacencyMatrix());
        g.addVertex();
        sg.colorNode(4,'r');
        sg.step(g.getAdjacencyMatrix());
        g.addVertex();
        sg.step(g.getAdjacencyMatrix());
        g.addVertex();
        sg.colorNode(5,'g');
        sg.step(g.getAdjacencyMatrix());
        g.addVertex();
        sg.step(g.getAdjacencyMatrix());
        g.addVertex();
        sg.step(g.getAdjacencyMatrix());

        //A
        g.addEdge(0,1,2);
        sg.step(g.getAdjacencyMatrix());
        //g.addEdge(1,0,4);
        g.addEdge(0,2,5);
        sg.colorEdge(0,2,'c', false);
        sg.step(g.getAdjacencyMatrix());
        g.addEdge(0,4,7);
        sg.step(g.getAdjacencyMatrix());
        sg.colorEdge(0,4,'y',false);
        sg.step();

        //B
        g.addEdge(1,2,5);
        g.addEdge(1,4,12);

        //C
        g.addEdge(2,3,8);
        g.addEdge(2,7,18);
        g.addEdge(2,5,1);

        //D
        g.addEdge(3,8,3);
        g.addEdge(3,9,9);

        //E
        g.addEdge(4,6,16);
        g.addEdge(4,5,10);
        //F
        g.addEdge(5,9,22);

        //G
        g.addEdge(6,9,4);

        //H
        g.addEdge(7,8,4);
        g.addEdge(7,9,4);


        sg.start();
    }

    /**
     * Fluss
     * 5 Knoten
     * (Hin und Zurück)
     */
    public static void test3() {
        SchGraphs sg = new SchGraphs(true);


        int[][] graph;

        Graph g = new Graph(true);
        g.addVertex(); //s
        sg.step(g.getAdjacencyMatrix(), g.getEmptyFlow());
        g.addVertex(); //A
        sg.step(g.getAdjacencyMatrix(), g.getEmptyFlow());
        g.addVertex(); //B
        sg.step(g.getAdjacencyMatrix(), g.getEmptyFlow());
        g.addVertex(); //C
        sg.step(g.getAdjacencyMatrix(), g.getEmptyFlow());
        g.addVertex(); //D
        sg.step(g.getAdjacencyMatrix(), g.getEmptyFlow());
        g.addVertex(); //E
        sg.step(g.getAdjacencyMatrix(), g.getEmptyFlow());
        g.addVertex(); //t

        sg.step(g.getAdjacencyMatrix(), g.getEmptyFlow());

        g.addEdge(0,1,10);
        g.addEdge(0,2,8);
        g.addEdge(1,2,5);
        g.addEdge(1,3,5);
        g.addEdge(2,3,4);
        g.addEdge(2,4,2);
        g.addEdge(3,4,2);
        g.addEdge(3,6,8);
        g.addEdge(4,6,8);
        g.addEdge(4,5,4);
        g.addEdge(5,4,14);
        g.addEdge(5,6,50);
        sg.step(g.getAdjacencyMatrix(), g.getEmptyFlow());

        int[][] flow = g.getEmptyFlow();
        flow[0][1] = 10;
        flow[5][4] = 12;

        sg.colorEdge(5,4,'r',true);

        sg.step(g.getAdjacencyMatrix(), flow);

        sg.step();
        sg.step();
        sg.step();
        sg.step();
        sg.step();


        sg.start();
    }

    public static void test4() {
        SchGraphs sg = new SchGraphs();
        Graph g = new Graph(false);
        g.addVertex();
        g.addVertex();
        g.addVertex();
        g.addVertex();
        g.addVertex();

    }

    public static void main(String[] args) {
        //test2();
        test3();
    }
}