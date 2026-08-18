package blatt32.aufgabe03;

public class Main {

    public static void test01() {
        Graph g = new Graph();

        g.addVertex();
        g.addVertex();
        g.addVertex();
        g.addVertex();
        g.addVertex();
        g.addVertex();

        System.out.println(g.size());

        g.addEdge(0,2,7);
        g.addEdge(0,4,6);
        g.addEdge(0,1,8);
        g.addEdge(0,5,1);

        g.addEdge(1,3,11);
        g.addEdge(2,3,4);

        g.addEdge(4,5,4);

        //g.exportHTML();
        g.exportGraph("graph01");

        g.addVertex();
        System.out.println(g.size());

        g.importGraph("graph01");
        System.out.println(g.size());
    }

    public static void test02() {
        Graph g = new Graph();

        g.addVertex();
        g.addVertex();
        g.addVertex();
        g.addVertex();
        g.addVertex();
        g.addVertex();

        System.out.println(g.size());

        g.addEdge(0,2,7);
        g.addEdge(0,4,6);
        g.addEdge(0,1,8);
        g.addEdge(0,5,1);

        g.addEdge(1,3,11);
        g.addEdge(2,3,4);

        g.addEdge(4,5,4);



    }
    public static void main(String[] args) {

    }
}
