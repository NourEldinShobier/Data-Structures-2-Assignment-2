import enums.NodeColor;

class RedBlackTree
{
    Node root;
    private Node tempParent;
    private Node newNode;


    void add(String value)
    {
        tempParent = newNode = null;
        root = add(root, value);
        addFixup(newNode);
    }

    private Node add(Node current, String value)
    {
        if (current == null) {
            Node node = new Node(value);

            node.parent = tempParent;
            newNode = node;

            return node;
        }

        if (Integer.parseInt(value) < Integer.parseInt(current.value)) {
            tempParent = current;
            current.left = add(current.left, value);
        }
        else if (Integer.parseInt(value) > Integer.parseInt(current.value)) {
            tempParent = current;
            current.right = add(current.right, value);
        }
        else {
            // value already exists
            return current;
        }

        return current;
    }

    private void addFixup(Node node)
    {
        while (NodeQuery.parentOf(node) != null &&
                NodeQuery.grandParentOf(node) != null &&
                NodeQuery.parentOf(node).color == NodeColor.RED) {

            if (node.parent == NodeQuery.grandParentOf(node).left) {

                Node uncle = NodeQuery.grandParentOf(node).right;
                NodeColor uncleColor = uncle == null ? NodeColor.BLACK : uncle.color;

                if (uncleColor == NodeColor.RED) {
                    NodeQuery.parentOf(node).color = NodeColor.BLACK;
                    uncle.color = NodeColor.BLACK;

                    node = NodeQuery.grandParentOf(node);
                    node.color = NodeColor.RED;
                }
                else {

                    if (node == NodeQuery.parentOf(node).right) {
                        node = NodeQuery.parentOf(node);
                        rotateLeft(node);
                    }

                    NodeQuery.parentOf(node).color = NodeColor.BLACK;
                    NodeQuery.grandParentOf(node).color = NodeColor.RED;
                    rotateRight(NodeQuery.grandParentOf(node));
                }
            }
            else if (node.parent == NodeQuery.grandParentOf(node).right) {

                Node uncle = NodeQuery.grandParentOf(node).left;
                NodeColor uncleColor = uncle == null ? NodeColor.BLACK : uncle.color;

                if (uncleColor == NodeColor.RED) {
                    NodeQuery.parentOf(node).color = NodeColor.BLACK;
                    uncle.color = NodeColor.BLACK;

                    node = NodeQuery.grandParentOf(node);
                    node.color = NodeColor.RED;
                }
                else {

                    if (node == NodeQuery.parentOf(node).left) {
                        node = NodeQuery.parentOf(node);
                        rotateRight(node);
                    }

                    NodeQuery.parentOf(node).color = NodeColor.BLACK;
                    NodeQuery.grandParentOf(node).color = NodeColor.RED;
                    rotateLeft(NodeQuery.grandParentOf(node));
                }
            }
        }

        root.color = NodeColor.BLACK;
    }

    void search(String data)
    {

    }

    private void search(String data, Node node)
    {

    }

    void delete(String data)
    {

    }

    private void delete(String data, Node node)
    {

    }

    private void rotateLeft(Node x)
    {
        Node y = x.right;
        x.right = y.left;

        if (y.left != null) y.left.parent = x;

        y.parent = x.parent;

        if (x.parent == null) root = y;
        else {
            if (x == x.parent.left) x.parent.left = y;
            else x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private void rotateRight(Node x)
    {
        Node y = x.left;
        x.left = y.right;

        if (y.right != null) y.right.parent = x;

        y.parent = x.parent;

        if (x.parent == null) root = y;

        else {
            if (x == x.parent.left) x.parent.left = y;
            else x.parent.right = y;
        }

        y.right = x;
        x.parent = y;
    }

    void printPreorder(Node node)
    {
        if (node == null) return;

        System.out.println(node.value + ", " + node.color);

        printPreorder(node.left);
        printPreorder(node.right);
    }

    void printInorder(Node node)
    {
        if (node == null) return;

        printInorder(node.left);
        System.out.println(node.value + ", " + node.color);
        printInorder(node.right);
    }
}
