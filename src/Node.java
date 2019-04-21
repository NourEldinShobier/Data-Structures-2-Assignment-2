import enums.NodeColor;

class Node
{
    public NodeColor color = NodeColor.RED;
    public String data = "";
    public Node parent;
    public Node right;
    public Node left;

    public Node(String data, Node parent, Node right, Node left)
    {
        this.data = data;
        this.parent = parent;
        this.right = right;
        this.left = left;
    }
}
