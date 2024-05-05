import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> {
    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node root;
    private int size;

    /**
     * Constructs an empty BST.
     */
    public BST() {
        root = null;
        size = 0;
    }

    /**
     * Returns the number of nodes in the BST.
     * @return The size of the BST.
     */
    public int size() {
        return size;
    }

    /**
     * Inserts or updates the value associated with a given key.
     * @param key The key to insert in the tree.
     * @param val The value to associate with the key.
     */
    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node node, K key, V val) {
        if (node == null) { // Base case. If current node is null, create a new node with the key-value pair.
            size++;
            node = new Node(key, val);
        }

        int cmp = key.compareTo(node.key); // Compare the key with the current node's key to decide direction.

        if (cmp < 0) // If key is less, go to the left subtree.
            node.left = put(node.left, key, val);

        else if (cmp > 0) // If key is greater, go to the right subtree.
            node.right = put(node.right, key, val);

        else // If key is equal, update the current node's value.
            node.val = val;

        return node;
    }

    /**
     * Retrieves the value for a given key.
     * @param key The key whose value is to be retrieved.
     * @return The value associated with the key, or null if the key is not found.
     */
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        while (node != null) {
            int cmp = key.compareTo(node.key); // Navigate the tree based on comparison of keys.

            if (cmp < 0) // Move left if the search key is less than the current node's key.
                node = node.left;

            else if (cmp > 0) // Move right if the search key is greater.
                node = node.right;

            else  // Return the value if found.
                return node.val;
        }

        return null; // Return null if the key is not found.
    }

    /**
     * Removes the node with the specified key.
     * @param key The key of the node to be removed.
     */
    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) return null; // Base case: Key not found.

        int cmp = key.compareTo(node.key); // Decide the direction of the search for deletion.

        if (cmp < 0) // Key is less, go left.
            node.left = delete(node.left, key);

        else if (cmp > 0) // Key is more, go right.
            node.right = delete(node.right, key);

        else {
            // Key found, proceed to delete this node.
            if (node.right == null)
                return node.left;

            if (node.left == null)
                return node.right;

            // Node has two children, replace with the smallest in the right subtree.
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }

        return node;
    }

    private Node min(Node node) {
        while (node.left != null) // Find the minimum key node by always going left.
            node = node.left;

        return node;
    }

    private Node deleteMin(Node node) {
        // Recursively go left to find and remove the minimum node.
        if (node.left == null) // Base case.
            return node.right;

        node.left = deleteMin(node.left); // Recursive case.

        return node;
    }

    /**
     *
     * @return
     */
    public Iterable<Node> iterator() {
        ArrayList<Node> nodes = new ArrayList<>();
        inOrderTraversal(root, nodes);
        return nodes;
    }

    private void inOrderTraversal(Node x, ArrayList<Node> nodes) {
        if (x == null) return;
        inOrderTraversal(x.left, nodes);
        nodes.add(x);
        inOrderTraversal(x.right, nodes);
    }

    private void inOrder(Node x, ArrayList<K> keys) {
        if (x != null) {
            inOrder(x.left, keys);
            keys.add(x.key);
            inOrder(x.right, keys);
        }
    }
}

