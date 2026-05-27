package blatt30.aufgabe02;

public class BinBaum {

    private Node root;

    public BinBaum() {
        this.root = null;
    }

    public BinBaum(Node root) {
        this.root = root;
    }

    public Node search(int key) {
        Node n = root;
        while (n != null) {
            if (n.getValue() == key) {
                //found
                return n;
            } else if (key < n.getValue()) {
                //left
                if (n.getLeft() == null) {
                    return null;
                } else {
                    n = n.getLeft();
                }
            } else {
                //right
                if (n.getRight() == null) {
                    return null;
                } else {
                    n = n.getRight();
                }
            }
        }
        return null;
    }

    public void add(int value) {
        Node next = new Node(value);
        if (this.root == null) {
            this.root = next;
        } else {
            Node n = this.root;
            while (n != null) {
                if (value < n.getValue()) {
                    //Left
                    if (n.getLeft() == null) {
                        n.setLeft(next);
                    } else {
                        n = n.getLeft();
                    }
                } else {
                    //Right
                    if (n.getRight() == null) {
                        n.setRight(next);
                    } else {
                        n = n.getRight();
                    }
                }
            }
        }
    }

    public Node remove(int value) {
        if (this.root == null) {
            return null;
        } else {
            //Find the parent node
            if (this.root.getValue() == value) {
                //Exterminate Root

            } else {
                Node n = this.root;
                while (n != null) {
                    if (value == n.getLeft().getValue()) {
                        //Is it Left(?)
                        Node parent = n;
                        Node remove = n.getLeft();

                        //Number of Children
                        if (remove.countChildren() == 0) {
                            //Easy
                            parent.setLeft(null);
                            return remove;
                        } else if (remove.countChildren() == 1) {
                            //Not so easy
                            //Get correct Child
                            if (remove.getLeft() != null) {
                                parent.setLeft(remove.getLeft());
                            } else {
                                parent.setLeft(remove.getRight());
                            }
                            return remove;
                        } else {
                            //Not so Not so easy
                            //Find In-Order-Neighbour

                        }

                    } else if (value == n.getRight().getValue()) {
                        //Is it Right(?)
                        Node parent = n;
                        Node remove = n.getRight();

                    } else {
                        //Get into the correct direction
                        if (value < n.getValue()) {
                            n = n.getLeft();
                        } else {
                            n = n.getRight();
                        }
                    }
                }
                return null;
            }

        }
        return null;
    }
}
