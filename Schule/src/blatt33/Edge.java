package blatt33;

public class Edge implements Comparable<Edge> {
    int from;
    int to;
    int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }


    @Override
    public int compareTo(Edge o) {
        if (o.weight > this.weight) {
            return -1;
        } else if (o.weight == this.weight) {
            return 0;
        } else {
            return 1;
        }
    }
}
