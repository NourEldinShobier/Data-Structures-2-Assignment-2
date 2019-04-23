import enums.NodeColor;

class Node
{
    NodeColor color = NodeColor.RED;
    String value;
    Node parent;
    Node right;
    Node left;

    Node(String value)
    {
        this.value = value;
        this.right = this.left = this.parent = null;
    }
}
