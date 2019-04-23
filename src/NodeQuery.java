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
}
