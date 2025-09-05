package doubly_linked_list_4;

/**
 * Doubly Linked List (DLL) implementation.
 * Har node ke paas ek `pre` (previous node ka reference), ek `item` (data), aur ek `next` (next node ka reference) hota hai.
 */
public class DDL {

    /**
     * Node class DLL ke liye.
     * Ye ek element represent karta hai list me, with references to prev aur next nodes.
     */
    public class Node {
        private Node pre;
        private int item;
        private Node next;

        // --- Getters & Setters ---
        public Node getPre() { return pre; }
        public void setPre(Node pre) { this.pre = pre; }
        public int getItem() { return item; }
        public void setItem(int item) { this.item = item; }
        public Node getNext() { return next; }
        public void setNext(Node next) { this.next = next; }
    }

    private Node start; // DLL ka head pointer

    /** Constructor: ek empty DLL create karta hai */
    public DDL() {
        start = null;
    }

    /**
     * Q.1 -> Check if Linked List is Empty
     * @return true agar DLL empty hai, warna false
     */
    public boolean isEmpty() {
        return start == null;
    }

    /**
     * Q.2 -> Insert a new Node at Start
     * Core logic:
     *  - Agar DLL empty hai → directly newNode ko start bana do.
     *  - Agar DLL non-empty hai → newNode ka next = start, aur start ka prev = newNode.
     * Edge case: Empty list.
     */
    public void insertAtStart(int data) {
        Node newNode = new Node();
        newNode.setPre(null);
        newNode.setItem(data);

        if (isEmpty()) {
            newNode.setNext(null);
            start = newNode;
        } else {
            newNode.setNext(start);
            start.setPre(newNode);
            start = newNode;
        }
    }

    /**
     * Q.3 -> Insert a new Node at End
     * Core logic:
     *  - Agar DLL empty hai → newNode ko head bana do.
     *  - Agar DLL non-empty hai → last node tak traverse karo aur uske next = newNode set karo.
     * Edge case: Empty list.
     */
    public void insertAtEnd(int data) {
        Node newNode = new Node();
        newNode.setItem(data);
        newNode.setNext(null);

        if (isEmpty()) {
            newNode.setPre(null);
            start = newNode;
        } else {
            Node temp = start;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
            newNode.setPre(temp);
        }
    }

    /**
     * Q.4 -> Search a Node by given data
     * Core logic:
     *  - Start se end tak traverse karo aur first matching data return karo.
     * Edge case: Agar DLL empty hai → null return hoga.
     * @return Node jisme data mila, warna null
     */
    public Node search(int data) {
        Node temp = start;

        if (!isEmpty()) {
            while (temp != null) {
                if (temp.getItem() == data) {
                    return temp;
                }
                temp = temp.getNext();
            }
        }
        return null;
    }

    /**
     * Q.5 -> Insert a new node AFTER a given node
     * Core logic:
     *  - newNode ka prev = givenNode
     *  - newNode ka next = givenNode ka next
     *  - givenNode ka next = newNode
     *  - agar givenNode ka next null nahi tha, uska prev update karna hoga
     * Edge case: Agar givenNode null hai → error.
     */
    public void insertAfter(int data, Node givenNode) {
        if (givenNode == null) {
            System.out.println("Error: The given node cannot be null.");
            return;
        }

        Node newNode = new Node();
        newNode.setItem(data);
        newNode.setNext(givenNode.getNext());
        newNode.setPre(givenNode);
        givenNode.setNext(newNode);

        if (newNode.getNext() != null) {
            newNode.getNext().setPre(newNode);
        }
    }

    /**
     * Q.7 -> Delete first node
     * Core logic:
     *  - Agar list empty hai → kuch mat karo.
     *  - Agar list me ek hi node hai → start ko null karo.
     *  - Warna → start ko next node par shift karo aur uska prev null kar do.
     */
    public void deleteFirst() {
        if (isEmpty()) {
            return;
        }
        start = start.getNext();
        if (start != null) {
            start.setPre(null);
        }
    }

    /**
     * Q.8 -> Delete last node
     * Core logic:
     *  - Agar list empty hai → kuch mat karo.
     *  - Agar ek hi node hai → start = null.
     *  - Warna → last node tak jao aur uske prev ka next null kar do.
     */
    public void deleteLast() {
        if (isEmpty()) {
            return;
        }
        if (start.getNext() == null) {
            start = null;
        } else {
            Node temp = start;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.getPre().setNext(null);
        }
    }

    /**
     * Q.9 -> Delete a node by data
     * Core logic:
     *  - Pehle node search karo.
     *  - Agar node head hai → deleteFirst() call karo.
     *  - Agar node last hai → deleteLast() call karo.
     *  - Agar beech me hai → prev aur next ko direct link kar do.
     * Edge cases:
     *  - Agar data not found → kuch mat karo.
     *  - Agar single node list hai → handle via deleteFirst.
     */
    public void deleteNode(int data) {
        Node node = search(data);

        if (node != null) {
            if (start == node) {
                deleteFirst();
            } else if (node.getNext() == null) {
                deleteLast();
            } else {
                node.getPre().setNext(node.getNext());
                node.getNext().setPre(node.getPre());
            }
        }
    }
    /* Utility Method -> DLL ke elements ko print karna */
    public void display() {
        if (isEmpty()) {
            System.out.println("List is empty!");
            return;
        }

        Node temp = start;  // start node se traversal start
        while (temp != null) {
            System.out.print(temp.getItem() + " ");
            temp = temp.getNext();
        }
        System.out.println();
    }

}

class DLLTest{
    public static void main(String[] args) {
        DDL dll = new DDL();

        System.out.println("--- Step 1: Empty DLL ---");
        dll.display(); // List is empty!

        System.out.println("--- Step 2: Insert at Start ---");
        dll.insertAtStart(10);
        dll.insertAtStart(20);
        dll.insertAtStart(30);
        dll.display(); // 30 20 10

        System.out.println("--- Step 3: Insert at End ---");
        dll.insertAtEnd(5);
        dll.insertAtEnd(1);
        dll.display(); // 30 20 10 5 1

        System.out.println("--- Step 4: Search Node (10) ---");
        DDL.Node searchNode = dll.search(10);
        System.out.println(searchNode != null ? "Found: " + searchNode.getItem() : "Not found"); // Found: 10

        System.out.println("--- Step 5: Insert After Node (10 -> 15) ---");
        dll.insertAfter(15, searchNode);
        dll.display(); // 30 20 10 15 5 1

        System.out.println("--- Step 6: Delete First ---");
        dll.deleteFirst();
        dll.display(); // 20 10 15 5 1

        System.out.println("--- Step 7: Delete Last ---");
        dll.deleteLast();
        dll.display(); // 20 10 15 5

        System.out.println("--- Step 8: Delete Node (15) ---");
        dll.deleteNode(15);
        dll.display(); // 20 10 5

        System.out.println("--- Step 9: Delete Non-existent Node (99) ---");
        dll.deleteNode(99);
        dll.display(); // 20 10 5 (no change)

        System.out.println("--- Step 10: Delete remaining nodes ---");
        dll.deleteNode(20);
        dll.deleteNode(10);
        dll.deleteNode(5);
        dll.display(); // List is empty!
    }

}