package blatt30.aufgabe02;

public class Node {
    private Node left;
    private Node right;

    private int value;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public int getValue() {
        return value;
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

    public Node[] findInOrderNeighbour() {
        //Two Nodes

        //The Right-Tree furthest left

        //The Left-Tree furthest right

        return null;
    }


}
