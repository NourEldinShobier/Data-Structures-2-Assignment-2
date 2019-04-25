import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class DRBTree
{
    Node root;
    int size = 0;
    private Node tempParent;
    private Node newNode;
    private boolean isDupilcated;

    void initRoot()
    {
        root = new Node("").setNil();
    }

    void add(String value)
    {
        isDupilcated = false;
        tempParent = newNode = null;
        root = add(root, value);
        if (!isDupilcated) {
            size++;
            addFixup(newNode);
        }
    }

    private Node add(Node current, String value)
    {
        if (current.isNil) {
            Node node = new Node(value);

            node.parent = tempParent;

            //-----------------------
            node.right = new Node("").setNil();
            node.left = new Node("").setNil();

            node.left.parent = node;
            node.right.parent = node;
            //-----------------------

            newNode = node;

            return node;
        }

        if (value.compareToIgnoreCase(current.value) < 0) {
            tempParent = current;
            current.left = add(current.left, value);
        }
        else if (value.compareToIgnoreCase(current.value) > 0) {
            tempParent = current;
            current.right = add(current.right, value);
        }
        else {
            isDupilcated = true;
            System.out.println("ERROR: This word already in the dictionary");
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

                if (uncle.color == NodeColor.RED) {
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

                if (uncle.color == NodeColor.RED) {
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

    Node search(String value)
    {
        return search(value, root);
    }

    private Node search(String value, Node current)
    {
        if (current.isNil) return null;
        if (value.equals(current.value)) return current;

        return value.compareToIgnoreCase(current.value) < 0
                ? search(value, current.left)
                : search(value, current.right);
    }

    // Edited <<<<
    void Size_Decrement()
    {
        size--;
    }

    void delete(String word)
    {
        if (root.isNil) return;

        Node node = search(word);

        if (node == null) return;

        delete(node);
    }

    private void delete(Node z)
    {
        Node y = z;
        NodeColor yOriginalColor = y.color;

        Node x;

        if (z.left.isNil) {
            x = z.right;
            transplant(z, z.right);
        }

        else if (z.right.isNil) {
            x = z.left;
            transplant(z, z.left);
        }

        else {
            y = treeMinimum(z.right);
            yOriginalColor = y.color;

            x = y.right;

            if (y.parent == z) x.parent = y;
            else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplant(z, y);

            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (yOriginalColor == NodeColor.BLACK) {
            deleteFixup(x);
        }
    }

    private Node treeMinimum(Node x)
    {
        Node n = x;
        while (!n.left.isNil) n = n.left;
        return n;
    }

    private void transplant(Node u, Node v)
    {
        if (u.parent == null) {
            root = v;
        }

        else if (u == u.parent.left) {
            u.parent.left = v;
        }
        else u.parent.right = v;

        v.parent = u.parent;
    }

    private void deleteFixup(Node x)
    {
        Node w;

        while (x != root && x.color == NodeColor.BLACK) {
            if (x == x.parent.left) {
                w = x.parent.right;

                if (w.color == NodeColor.RED) {
                    w.color = NodeColor.BLACK;
                    x.parent.color = NodeColor.RED;

                    rotateLeft(x.parent);

                    w = x.parent.right;
                }

                if (w.left.color == NodeColor.BLACK && w.right.color == NodeColor.BLACK) {
                    w.color = NodeColor.RED;
                    x = x.parent;
                }

                else if (w.right.color == NodeColor.BLACK) {
                    w.left.color = NodeColor.BLACK;
                    w.color = NodeColor.RED;

                    rotateRight(w);

                    w = x.parent.right;
                    w.color = x.parent.color;

                    x.parent.color = NodeColor.BLACK;
                    w.right.color = NodeColor.BLACK;

                    rotateLeft(x.parent);

                    x = root;
                }
            }
            else {

                w = x.parent.left;

                if (w.color == NodeColor.RED) {
                    w.color = NodeColor.BLACK;
                    x.parent.color = NodeColor.RED;

                    rotateRight(x.parent);

                    w = x.parent.left;
                }

                if (w.right.color == NodeColor.BLACK && w.left.color == NodeColor.BLACK) {
                    w.color = NodeColor.RED;
                    x = x.parent;
                }

                else if (w.left.color == NodeColor.BLACK) {
                    w.right.color = NodeColor.BLACK;
                    w.color = NodeColor.RED;

                    rotateLeft(w);

                    w = x.parent.left;
                    w.color = x.parent.color;

                    x.parent.color = NodeColor.BLACK;
                    w.left.color = NodeColor.BLACK;

                    rotateRight(x.parent);

                    x = root;
                }
            }
        }

        x.color = NodeColor.BLACK;
    }

    private void rotateLeft(Node x)
    {
        Node y = x.right;
        x.right = y.left;

        if (!y.left.isNil) y.left.parent = x;

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

        if (!y.right.isNil) y.right.parent = x;

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
        if (node.isNil) return;

        System.out.println(node.value + ", " + node.color);

        printPreorder(node.left);
        printPreorder(node.right);
    }

    void printInorder(Node node)
    {
        if (node.isNil) return;

        printInorder(node.left);
        System.out.println(node.value + ", " + node.color);
        printInorder(node.right);
    }

    void loadVocabs()
    {
        try {
            Scanner scanner = new Scanner(new File("C:\\DS\\vocabs.txt"));
            while (scanner.hasNextLine()) add(scanner.nextLine().trim());
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    int findHeight()
    {
        return findHeight(root);
    }

    private int findHeight(Node aNode)
    {
        if (aNode.isNil) return -1;

        int lefth = findHeight(aNode.left);
        int righth = findHeight(aNode.right);

        if (lefth > righth) {
            return lefth + 1;
        }
        else {
            return righth + 1;
        }
    }
}