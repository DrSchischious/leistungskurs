package blatt32.aufgabe03;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Graph {

    private int[][] adjazenzmatrix;

    public Graph() {
        this.adjazenzmatrix = new int[0][0];
    }

    public Graph(int size) {
        this.adjazenzmatrix = new int[size][size];
    }

    public void addVertex() {
        int[][] adj = new int[this.adjazenzmatrix.length + 1][this.adjazenzmatrix.length + 1];

        for (int i = 0; i < this.adjazenzmatrix.length; i++) {
            for (int j = 0; j < this.adjazenzmatrix.length; j++) {
                adj[i][j] = this.adjazenzmatrix[i][j];
            }
        }

        this.adjazenzmatrix = adj;
    }

    public void addEdge(int from, int to, int weight, boolean isDirected) {
        if (from == to || from < 0 || to < 0 || to >= adjazenzmatrix.length || from >= adjazenzmatrix.length || weight < 0) {
            throw new IllegalArgumentException();
        }

        if (isDirected) {
            this.adjazenzmatrix[from][to] = weight;
        } else {
            this.adjazenzmatrix[from][to] = weight;
            this.adjazenzmatrix[to][from] = weight;
        }
    }

    public void addEdge(int from, int to, int weight) {
        this.addEdge(from, to, weight, false);
    }

    public void addEdge(int from, int to) {
        this.addEdge(from, to, 1, false);
    }

    public void addEdge(int from, int to, boolean isDirected) {
        this.addEdge(from, to, 1, isDirected);
    }

    public int size() {
        return this.adjazenzmatrix.length;
    }

    public boolean isWeighted() {
        for (int i = 0; i < this.adjazenzmatrix.length; i++) {
            for (int j = 0; j < this.adjazenzmatrix.length; j++) {
                if (this.adjazenzmatrix[i][j] > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isDirected() {
        for (int i = 0; i < this.adjazenzmatrix.length; i++) {
            for (int j = i + 1; j < this.adjazenzmatrix.length; j++) {
                if (adjazenzmatrix[i][j] != adjazenzmatrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] getAdjacencyMatrix() {
        int[][] adj = new int[adjazenzmatrix.length][adjazenzmatrix.length];
        for (int i = 0; i < adjazenzmatrix.length; i++) {
            for (int j = 0; j < adjazenzmatrix.length; j++) {
                adj[i][j] = adjazenzmatrix[i][j];
            }
        }

        return adj;
    }

    public int[] getNeighbours(int node) {
        if (node < 0 || node >= adjazenzmatrix.length) {
            throw new IllegalArgumentException();
        }

        int count = 0;
        for (int i = 0; i < adjazenzmatrix.length; i++) {
            if (adjazenzmatrix[node][i] > 0) {
                count++;
            }
        }

        int[] neighbours = new int[count];
        int z = 0;
        for (int i = 0; i < adjazenzmatrix.length; i++) {
            if (adjazenzmatrix[node][i] > 0) {
                neighbours[z] = i;
                z++;
            }
        }

        return neighbours;
    }

    public void exportHTML() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateOnly = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat timeOnly = new SimpleDateFormat("HH-mm-ss");
        Date d = cal.getTime();
        String t = dateOnly.format(d);
        String ts = t + "-" + timeOnly.format(d);

        File f = new File(ts + "_table.html");
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("<!DOCTYPE html>");
            bw.newLine();
            bw.write("<html>");
            bw.newLine();
            bw.write("<head>");
            bw.newLine();
            bw.write("<meta charset=\"utf-8\"><title>Adjazenzmatrix</title>");
            bw.newLine();
            bw.write("</head>");
            bw.newLine();
            bw.write("<body>");
            bw.newLine();
            bw.write("<h1>Adjazenzmatrix</h1>");
            bw.newLine();
            bw.write("<table border=\"1\">");
            bw.newLine();

            bw.write("<tr>");
            bw.newLine();
            bw.write("<td></td>");
            bw.newLine();
            for (int i = 0; i < adjazenzmatrix.length; i++) {
                bw.write("<td>" + (char) ((int) 'A' + i) + "</td>");
                bw.newLine();
            }
            bw.write("</tr>");
            bw.newLine();

            for (int i = 0; i < adjazenzmatrix.length; i++) {
                bw.write("<tr>");
                bw.newLine();
                bw.write("<td>" + (char) ((int) 'A' + i) + "</td>");
                bw.newLine();
                for (int j = 0; j < adjazenzmatrix.length; j++) {
                    bw.write("<td>" + adjazenzmatrix[i][j] + "</td>");
                    bw.newLine();
                }
                bw.write("</tr>");
                bw.newLine();
            }
            bw.write("</table>");
            bw.newLine();


            bw.write("</body>");
            bw.newLine();
            bw.write("</html>");

            bw.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void exportGraph(String filename) {
        File f = new File(filename + ".txt");
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(""+size());
            bw.newLine();
            for (int i = 0; i < adjazenzmatrix.length; i++) {
                for (int j = 0; j < adjazenzmatrix.length; j++) {
                    bw.write(""+adjazenzmatrix[i][j]);
                    bw.newLine();
                }
            }

            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void importGraph(String filename) {
        File f = new File(filename + ".txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            int size = 0;
            if (br.ready()) {
                size = Integer.parseInt(br.readLine());
            }

            int[][] adj = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (br.ready()) {
                        adj[i][j] = Integer.parseInt(br.readLine());
                    } else {
                        throw new RuntimeException();
                    }
                }
            }

            br.close();

            this.adjazenzmatrix = adj;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}