public abstract class NodeQuery
{
    public static Node parentOf(Node node)
    {
        return node.parent;
    }

    public static Node grandParentOf(Node node)
    {
        Node parent = parentOf(node);
        return parentOf(parent);
    }

    public static Node uncleOf(Node node)
    {
        Node parent = parentOf(node);
        Node grandParent = grandParentOf(node);

        if(grandParent.left == parent) return grandParent.right;
        if(grandParent.right == parent) return grandParent.left;

        return null;
    }
}
