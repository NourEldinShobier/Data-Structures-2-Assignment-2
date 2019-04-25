import enums.NodeColor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class DRBTree
{
    Node root;
    int size = 0;
    private Node tempParent;
    private Node newNode;


    void add(String value)
    {
        size++;
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

        if (value.compareToIgnoreCase(current.value) < 0 ) {
            tempParent = current;
            current.left = add(current.left, value);
        }
        else if (value.compareToIgnoreCase(current.value) > 0) {
            tempParent = current;
            current.right = add(current.right, value);
        }
        else {
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

    boolean search(String value)
    {
        return search(value, root);
    }

    private boolean search(String value, Node current)
    {
        if (current == null) return false;
        if (value.equals(current.value)) return true;

        return value.compareToIgnoreCase(current.value)<0
                ? search(value, current.left)
                : search(value, current.right);
    }

    void delete(String data)
    {
        size--;
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

    void loadVocabs(){
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\wadiebishoy\\Desktop\\English_Dictionary.txt"));
            while (scanner.hasNextLine()) add(scanner.nextLine().trim());
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    int findHeight(){
        return findHeight(root);
    }

    private int findHeight(Node aNode) {
        if (aNode == null) return -1;

        int lefth = findHeight(aNode.left);
        int righth = findHeight(aNode.right);

        if (lefth > righth) {
            return lefth + 1;
        } else {
            return righth + 1;
        }
    }
}
