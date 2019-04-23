public class Main
{

    public static void main(String[] args)
    {
        RedBlackTree tree = new RedBlackTree();

        tree.add("4");
        tree.add("3");
        tree.add("2");
        tree.add("1");
        tree.add("0");

        tree.printPreorder(tree.root);
    }
}
