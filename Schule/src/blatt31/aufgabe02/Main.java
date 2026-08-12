package blatt31.aufgabe02;



public class Main {
    public static void main(String[] args) {
        AVLBaum b = new AVLBaum();

        b.add(44);
        b.add(18);
        b.add(62);
        b.add(92);


        b.updateBalance();

       // b.inOrderPrintBalance();
        b.checkBalance(b.search(92));

        b.add(105);

        b.updateBalance();
        b.inOrderPrintBalance();

        //System.out.println(b.getParent(b.search(105)));


        b.checkBalance(b.search(105));

        b.updateBalance();
        b.inOrderPrintBalance();

        //Solve this Case, Big Boi
        b.add(1);
        b.add(2);
        b.add(-2);
        b.add(-3);
        b.add(-1);
        b.add(0);
        //TODO: LR with more to the Tree - Solve by giving part of the tree to the other side

    }


}
