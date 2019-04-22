import enums.NodeColor;
import enums.RotationDirection;

public abstract class Tree
{
    static Node root = null;

    static Node insert(String data, Node node)
    {
        /* If the tree is empty or leaf */
        if (node == null) {
            node = new Node(data, null, null, null);
            node.color = NodeColor.RED;

            return node;
        }


        if (Integer.parseInt(data) < Integer.parseInt(node.data))
            node.left = insert(data, node.left);
        else if (Integer.parseInt(data) > Integer.parseInt(node.data))
            node.right = insert(data, node.right);


        return node;
    }

    static void search(String data, Node node)
    {

    }

    static void delete(String data, Node node)
    {

    }

    static boolean checkViolation(Node node)
    {
        if (node == root && (node.color == NodeColor.RED)) return true;
        if (node.color == node.parent.color) return true;

        return false;
    }

    static Node recolor(Node node)
    {
        return null;
    }

    static Node straightRotation(Node node, RotationDirection rotationDirection)
    {
        return null;
    }

    static Node zedRotation(Node node, RotationDirection rotationDirection)
    {
        return null;
    }
}
