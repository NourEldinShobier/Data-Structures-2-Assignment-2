

abstract class NodeQuery
{
    static Node parentOf(Node node)
    {
        return node.parent;
    }

    static Node grandParentOf(Node node)
    {
        if (node.parent != null && parentOf(node.parent) != null) {
            return parentOf(node.parent);
        }

        return null;
    }

    static Node siblingOf(Node node){

        if (node.parent == null) return null;
        if (node.parent.left == node) return node.parent.right;

        return node.parent.left;
    }

    static boolean hasRedChild(Node node)
    {
        return (node.left != null && node.left.color == NodeColor.RED) ||
            (node.right != null && node.right.color == NodeColor.RED);
    }
}