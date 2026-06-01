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
            //System.out.println("root -> " + value);
        } else {
            Node n = this.root;
            while (true) {
                if (value < n.getValue()) {
                    //Left
                    if (n.getLeft() == null) {
                        //System.out.println(value + " <- " + n.getValue());
                        n.setLeft(next);
                        break;
                    } else {
                        n = n.getLeft();
                    }
                } else {
                    //Right
                    if (n.getRight() == null) {
                        //System.out.println(n.getValue() + " -> " + value);
                        n.setRight(next);
                        break;
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
                Node remove = this.root;
                //Number of Children
                if (remove.countChildren() == 0) {
                    //Easy
                    this.root = null;
                    return remove;
                } else if (remove.countChildren() == 1) {
                    //Not so easy
                    //Get correct Child
                    if (remove.getRight() != null) {
                        this.root = remove.getRight();
                    } else {
                        this.root = remove.getLeft();
                    }
                    return remove;
                } else {
                    //Not so Not so easy
                    //Find In-Order-Neighbour
                    Node[] inOrder = remove.findNextInOrderNeighbour();

                    //inOrder[0] gesuchter Knoten
                    Node next = inOrder[0];

                    remove(inOrder[0].getValue());

                    this.root = next;
                    System.out.println("New Root: " + next.getValue());

                    if (remove.getLeft() != next) {
                        next.setLeft(remove.getLeft());
                        System.out.println(next.getLeft().getValue() + " <- " + next.getValue());
                    }
                    if (remove.getRight() != next) {
                        next.setRight(remove.getRight());
                        System.out.println(next.getValue() + " -> " + next.getRight().getValue());

                    }

                    return remove;
                }
            } else {
                //Es ist nicht der Root.
                Node n = this.root;

                while (n != null) {
                    if (n.getLeft() != null) {
                        if (value == n.getLeft().getValue()) {
                            //Is it Left(?)
                            Node parent = n;
                            Node remove = n.getLeft();

                            //Number of Children
                            if (remove.countChildren() == 0) {
                                //Easy
                                System.out.println("No children!");
                                System.out.println("null <- " + parent.getValue());
                                parent.setLeft(null);
                                return remove;
                            } else if (remove.countChildren() == 1) {
                                //Not so easy
                                //Get correct Child
                                System.out.println("One Child!");
                                if (remove.getLeft() != null) {
                                    parent.setLeft(remove.getLeft());
                                    System.out.println(parent.getLeft().getValue() + " <- " + parent.getValue());
                                } else {
                                    parent.setLeft(remove.getRight());
                                    System.out.println(parent.getLeft().getValue() + " <- " + parent.getValue());
                                }
                                return remove;
                            } else {
                                //Not so Not so easy
                                //Find In-Order-Neighbour
                                System.out.println("Two Children (left)!");
                                Node[] inOrder = remove.findNextInOrderNeighbour();
                                /*
                                System.out.println("Remove: " + remove.getValue());

                                System.out.println("InOrder[0]: " + inOrder[0].getValue());
                                System.out.println("InOrder[1]: " + inOrder[1].getValue());
                                */

                                //inOrder[0] gesuchter Knoten, ersetzt den zu entfernenden Knoten
                                //Falls dieser jedoch selbst noch Kinder hat, so sollen diese an
                                //dessen Vater übergeben werden, allerdings NACH dem Tausch!


                                Node next = inOrder[0];
                                remove(inOrder[0].getValue());

                                parent.setLeft(next);
                                System.out.println(next.getValue() + " <- " + parent.getValue());

                                if (remove.getLeft() != next) {
                                    next.setLeft(remove.getLeft());
                                    System.out.println(next.getLeft().getValue() + " <- " + next.getValue());
                                }
                                if (remove.getRight() != next) {
                                    next.setRight(remove.getRight());
                                    System.out.println(next.getValue() + " -> " + next.getRight().getValue());

                                }

                                return remove;
                            }

                        }
                    }
                    if (n.getRight()!= null) {
                        if (value == n.getRight().getValue()) {

                            //Is it Right(?)
                            Node parent = n;
                            Node remove = n.getRight();
                            //Number of Children
                            if (remove.countChildren() == 0) {
                                System.out.println("No children!");
                                System.out.println(parent.getValue() + " -> null");
                                //Easy
                                parent.setRight(null);
                                return remove;
                            } else if (remove.countChildren() == 1) {
                                //Not so easy
                                //Get correct Child
                                System.out.println("One Child!");
                                if (remove.getRight() != null) {
                                    System.out.println(parent.getValue() + " -> " + parent.getRight().getValue());
                                    parent.setRight(remove.getRight());
                                } else {
                                    System.out.println(parent.getValue() + " -> " + parent.getRight().getValue());
                                    parent.setRight(remove.getLeft());
                                }
                                return remove;
                            } else {
                                //Not so Not so easy
                                //Find In-Order-Neighbour

                                System.out.println("Two Children (right)!");

                                Node[] inOrder = remove.findNextInOrderNeighbour();
                                /*
                                System.out.println("Remove: " + remove.getValue());

                                System.out.println("InOrder[0]: " + inOrder[0].getValue());
                                System.out.println("InOrder[1]: " + inOrder[1].getValue());
                                */

                                //inOrder[0] gesuchter Knoten
                                Node next = inOrder[0];
                                remove(inOrder[0].getValue());

                                parent.setRight(next);
                                System.out.println(parent.getValue() + " -> " + next.getValue());

                                if (remove.getLeft() != next) {
                                    next.setLeft(remove.getLeft());
                                    System.out.println(next.getLeft().getValue() + " <- " + next.getValue());

                                }
                                if (remove.getRight() != next) {
                                    next.setRight(remove.getRight());
                                    System.out.println(next.getValue() + " -> " + next.getRight().getValue());

                                }
                                return remove;
                            }
                        }
                    }

                    //Get into the correct direction
                    if (value < n.getValue()) {
                        n = n.getLeft();
                    } else {
                        n = n.getRight();
                    }

                }
                return null;
            }
        }
    }


    public int depth() {
        return this.depth(this.root);
    }

    public int depth(Node n) {

        if (n == null) {
            return 0;
        } else {
            //System.out.println("Depth: " + n.getValue());
            int left = this.depth(n.getLeft());
            int right = this.depth(n.getRight());
            if (left > right) {
                return left + 1;
            } else {
                return right + 1;
            }
        }

    }

    public int count() {
        return count(this.root);
    }

    public int count(Node n) {

        if (n == null) {
            return 0;
        }
        //System.out.println("Count: " + n.getValue());
        int left = this.count(n.getLeft());
        int right = this.count(n.getRight());
        return left + right + 1;
    }

    public void preOrderPrint() {
        preOrderPrint(this.root);
    }

    private void preOrderPrint(Node n) {
        if (n != null) {
            System.out.println(n.getValue());
            preOrderPrint(n.getLeft());
            preOrderPrint(n.getRight());
        }
    }

    public void inOrderPrint() {
        inOrderPrint(this.root);
    }

    private void inOrderPrint(Node n) {
        if (n != null) {
            inOrderPrint(n.getLeft());
            System.out.println(n.getValue());
            inOrderPrint(n.getRight());
        }
    }

    public void postOrderPrint() {
        postOrderPrint(this.root);
    }

    private void postOrderPrint(Node n) {
        if (n != null) {
            postOrderPrint(n.getLeft());
            postOrderPrint(n.getRight());
            System.out.println(n.getValue());
        }
    }

    public void printTree() {
        String t = "\t\t\t";
        System.out.println(t+t+t+t+t+this.root+t+t+t+t+t);
        System.out.println(t+t+t+this.root.getLeft()+t+t+t+t+this.root.getRight()+t+t);
        System.out.println(t+t+this.root.getLeft().getLeft()+t+t+this.root.getLeft().getRight()+t+t+this.root.getRight().getLeft()+t+t+this.root.getRight().getRight()+t+t);

    }

}