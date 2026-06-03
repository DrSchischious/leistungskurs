package blatt31.aufgabe02;

public class Node {
    private Node left;
    private Node right;

    private int value;

    private int balance;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.balance = 0;
    }

    public int getValue() {
        return value;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int countChildren() {
        int count = 0;
        if (left != null) {
            count++;
        }
        if (right != null) {
            count++;
        }
        return count;
    }

    public Node[] findNextInOrderNeighbour() {
        //Two Nodes
        Node[] neighbours = new Node[2];
        //The Left-Tree furthest right
        Node n = this.right;
        if (n.left == null) {
            neighbours[0] = n;
            neighbours[1] = this;
            return neighbours;
        }
        while (n.left.left != null) {
            n = n.left;
        }
        //Neighbour
        neighbours[0] = n.left;
        //His parent
        neighbours[1] = n;


        return neighbours;
    }

    public Node[] findPreviousInOrderNeighbour() {
        //Two Nodes
        Node[] neighbours = new Node[2];
        //The Left-Tree furthest right
        Node n = this.right;
        while (n.left.left != null) {
            n = n.left;
        }
        //Neighbour
        neighbours[0] = n.left;
        //His parent
        neighbours[1] = n;


        return neighbours;
    }

    public String toString() {
        if (this == null) {
            return "n";
        } else {
            return ""+this.value;
        }
    }


}
