
class Node
{
    NodeColor color = NodeColor.RED;
    String value;
    Node parent;
    Node right;
    Node left;

    boolean isNil;

    Node(String value)
    {
        this.value = value;
        this.right = this.left = this.parent = null;
    }

    Node setNil(){
        value = "";
        isNil = true;
        color = NodeColor.BLACK;

        return this;
    }
}
