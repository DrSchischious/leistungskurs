package graphs;

public class Graph {
    private int[][] adjacencyMatrix;
    private int vertices;
    private boolean directed;

    public Graph(boolean directed) {
        adjacencyMatrix = new int[0][0];
        this.directed = directed;
    }

    public void addVertex() {
        vertices++;
        adjacencyMatrix = expandAdjacencyMatrix();
        adjacencyMatrix[vertices-1][vertices-1] = 1;
    }

    public int[][] getAdjacencyMatrix() {
        return copyAdjacencyMatrix();
    }

    public int[][] getEmptyFlow() {
        int[][] emptyFlow = new int[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                emptyFlow[i][j] = 0;
            }
        }
        return emptyFlow;
    }
    public void addEdge(int source, int destination) {
        if (source >= 0 && source < vertices && destination >= 0 && destination < vertices) {
            this.adjacencyMatrix[source][destination] = 1;
            if (directed == false) {
                this.adjacencyMatrix[destination][source] = 1;
            }
        }
    }

    public void addEdge(int source, int destination, int weight) {
        if (source >= 0 && source < vertices && destination >= 0 && destination < vertices) {
            this.adjacencyMatrix[source][destination] = weight;
            if (directed == false) {
                this.adjacencyMatrix[destination][source] = weight;
            }
        }
    }



    public int[][] copyAdjacencyMatrix() {
        int[][] copyMatrix = new int[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                copyMatrix[i][j] = adjacencyMatrix[i][j];
            }
        }
        return copyMatrix;
    }

    public int[][] expandAdjacencyMatrix() {
        int[][] copyMatrix = new int[adjacencyMatrix.length+1][adjacencyMatrix.length+1];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                copyMatrix[i][j] = adjacencyMatrix[i][j];
            }
        }
        return copyMatrix;
    }
}
