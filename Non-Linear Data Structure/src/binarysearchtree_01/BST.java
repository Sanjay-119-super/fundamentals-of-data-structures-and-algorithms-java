package binarysearchtree_01;

/**
 * BST (Binary Search Tree) implementation.
 *
 * Rules:
 * 1. Har node ke max 2 child hote hain (left & right).
 * 2. Left subtree ke sabhi elements < root.
 * 3. Right subtree ke sabhi elements > root.
 *
 * Supported operations: insert, search, delete, traversals.
 */
public class BST {

    /**
     * Node class: ek single BST node ka structure.
     * Ye left child, right child aur ek data value store karta hai.
     */
    class Node {
        private Node left;
        private int data;
        private Node right;

        public Node getLeft() { return left; }
        public void setLeft(Node left) { this.left = left; }
        public int getData() { return data; }
        public void setData(int data) { this.data = data; }
        public Node getRight() { return right; }
        public void setRight(Node right) { this.right = right; }
    }

    private Node root; // BST ka root node

    /** Constructor: initially root null hota hai */
    public BST() {
        root = null;
    }

    /** @return true agar tree empty hai, warna false */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Insert ek data value ko BST me.
     * @param data insert karne wali value
     *
     * Base cases:
     * 1. Agar root null hai → naya node root ban jaega.
     * 2. Agar data < current root → left subtree me insert.
     * 3. Agar data > current root → right subtree me insert.
     * 4. Agar data already present → ignore (duplicates allowed nahi).
     */
    public void insert(int data) {
        root = insertRecursionHelper(root, data);
    }

    private Node insertRecursionHelper(Node currentRoot, int data) {
        if (currentRoot == null) {
            Node node = new Node();
            node.setData(data);
            return node;
        }

        if (data < currentRoot.data) {
            currentRoot.left = insertRecursionHelper(currentRoot.left, data);
        } else if (data > currentRoot.data) {
            currentRoot.right = insertRecursionHelper(currentRoot.right, data);
        }
        return currentRoot; // agar equal hai to kuch nahi karna
    }

    /**
     * Search ek data value ko BST me.
     * @param data search karne wali value
     * @return true agar mila, warna false
     *
     * Base cases:
     * 1. Agar current node null hai → element exist nahi karta.
     * 2. Agar current node ka data == search value → found.
     * 3. Agar search value < node.data → left subtree me search karo.
     * 4. Agar search value > node.data → right subtree me search karo.
     */
    public boolean search(int data) {
        return searchRecursionHelper(root, data);
    }

    private boolean searchRecursionHelper(Node rootCurrent, int data) {
        if (rootCurrent == null) { // base case: node exist hi nahi karta
            return false;
        }
        if (rootCurrent.data == data) {
            return true;
        }
        if (data < rootCurrent.data) {
            return searchRecursionHelper(rootCurrent.left, data);
        } else {
            return searchRecursionHelper(rootCurrent.right, data);
        }
    }

    /** Preorder traversal: Root → Left → Right */
    public void preorderTraversal() {
        preorderTraversalRecursionHelper(root);
        System.out.println();
    }

    private void preorderTraversalRecursionHelper(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderTraversalRecursionHelper(node.left);
            preorderTraversalRecursionHelper(node.right);
        }
    }

    /** Postorder traversal: Left → Right → Root */
    public void postorderTraversal() {
        postorderTraversalRecursionHelper(root);
        System.out.println();
    }

    private void postorderTraversalRecursionHelper(Node node) {
        if (node != null) {
            postorderTraversalRecursionHelper(node.left);
            postorderTraversalRecursionHelper(node.right);
            System.out.print(node.data + " ");
        }
    }

    /** Inorder traversal: Left → Root → Right (sorted order aata hai) */
    public void inOrderTraversal() {
        inOrderTraversalRecursionHelper(root);
        System.out.println();
    }

    private void inOrderTraversalRecursionHelper(Node node) {
        if (node != null) {
            inOrderTraversalRecursionHelper(node.left);
            System.out.print(node.data + " ");
            inOrderTraversalRecursionHelper(node.right);
        }
    }

    /**
     * Delete ek node ko BST se.
     * @param data delete karne wali value
     *
     * Base cases:
     * 1. Agar currentNode null hai → element exist hi nahi karta.
     * 2. Agar data < currentNode.data → left subtree me search.
     * 3. Agar data > currentNode.data → right subtree me search.
     * 4. Agar currentNode.data == data:
     *    - Case 1: Node ke 0 children (leaf) → simply delete.
     *    - Case 2: Node ke 1 child → us child ko upar replace kar do.
     *    - Case 3: Node ke 2 children → right subtree ka min value (inorder successor) upar lao, aur us node ko delete karo.
     */
    public void delete(int data) {
        root = deleteRecursionHelper(root, data);
    }

    private Node deleteRecursionHelper(Node currentNode, int data) {
        if (currentNode == null) {
            return null;
        }
        if (data < currentNode.data) {
            currentNode.left = deleteRecursionHelper(currentNode.left, data);
        } else if (data > currentNode.data) {
            currentNode.right = deleteRecursionHelper(currentNode.right, data);
        } else {
            // Found node to delete
            if (currentNode.left == null) { // left child missing
                return currentNode.right;
            } else if (currentNode.right == null) { // right child missing
                return currentNode.left;
            }
            // 2 child case
            currentNode.data = findMinValue(currentNode.right);
            currentNode.right = deleteRecursionHelper(currentNode.right, currentNode.data);
        }
        return currentNode;
    }

    /** Helper: right subtree ka sabse chhota element nikalta hai */
    private int findMinValue(Node node) {
        int minValue = node.data;
        while (node.left != null) {
            node = node.left;
            minValue = node.data;
        }
        return minValue;
    }
}


 class BSTTest {
    public static void main(String[] args) {
        BST bst = new BST();

        // Insert test
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        System.out.println("InOrder Traversal (sorted):");
        bst.inOrderTraversal(); // 20 30 40 50 60 70 80

        System.out.println("PreOrder Traversal:");
        bst.preorderTraversal(); // 50 30 20 40 70 60 80

        System.out.println("PostOrder Traversal:");
        bst.postorderTraversal(); // 20 40 30 60 80 70 50

        // Search test
        System.out.println("Search 40: " + bst.search(40)); // true
        System.out.println("Search 100: " + bst.search(100)); // false

        // Delete test
        System.out.println("Delete 20 (leaf):");
        bst.delete(20);
        bst.inOrderTraversal(); // 30 40 50 60 70 80

        System.out.println("Delete 30 (1 child):");
        bst.delete(30);
        bst.inOrderTraversal(); // 40 50 60 70 80

        System.out.println("Delete 50 (2 children):");
        bst.delete(50);
        bst.inOrderTraversal(); // 40 60 70 80
    }
}
