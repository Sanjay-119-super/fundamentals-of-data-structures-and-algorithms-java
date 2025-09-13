package extra.deque_using_linkedlist;

/**
 * Deque (Double Ended Queue) implementation using Doubly Linked List.
 *
 * 👉 In a Deque, we can insert and delete elements from both ends:
 *    - Front
 *    - Rear
 *
 * Why Doubly Linked List?
 * - Because every node has both "next" and "prev" pointers,
 *   so moving in both directions becomes easy.
 */
public class Deque {

    /**
     * Inner Node class represents each element in the deque.
     * Each node stores:
     *  - data (item)
     *  - pointer to next node
     *  - pointer to previous node
     */
    public class Node {
        private int item;   // actual data
        private Node next;  // link to next node
        private Node prev;  // link to previous node
    }

    private Node front; // pointer to first node (front of deque)
    private Node rear;  // pointer to last node (rear of deque)

    /**
     * Constructor: initializes an empty deque
     */
    public Deque() {
        front = null;
        rear = null;
    }

    /**
     * Insert a new element at the FRONT of deque.
     *
     * Behind the scenes:
     *  - If deque is empty → front and rear will point to new node
     *  - Else → link new node before current front
     *            update front pointer
     */
    public void insertAtFront(int data) {
        Node node = new Node();
        node.item = data;

        if (isEmpty()) {
            front = rear = node;
        } else {
            node.next = front;  // connect new node to old front
            front.prev = node;  // connect old front back to new node
            front = node;       // update front pointer
        }
    }

    /**
     * Insert a new element at the REAR of deque.
     *
     * Behind the scenes:
     *  - If deque is empty → front and rear point to same node
     *  - Else → attach new node after current rear
     *           update rear pointer
     */
    public void insertAtRear(int data) {
        Node node = new Node();
        node.item = data;

        if (isEmpty()) {
            rear = front = node;
        } else {
            rear.next = node;   // connect current rear to new node
            node.prev = rear;   // connect new node back to old rear
            rear = node;        // update rear pointer
        }
    }

    /**
     * Delete an element from the FRONT of deque.
     *
     * Behind the scenes:
     *  - If only one element → reset front and rear to null
     *  - Else → move front pointer to next node
     *           disconnect previous link
     */
    public int deleteAtFront() {
        if (isEmpty())
            throw new RuntimeException("Deque is empty");

        int item = front.item;

        if (front == rear) { // single element case
            front = rear = null;
        } else {
            front = front.next; // move front ahead
            front.prev = null;  // break old link
        }
        return item;
    }

    /**
     * Delete an element from the REAR of deque.
     *
     * Behind the scenes:
     *  - If only one element → reset front and rear
     *  - Else → move rear pointer to previous node
     *           disconnect next link
     */
    public int deleteAtRear() {
        if (isEmpty())
            throw new RuntimeException("Deque is empty");

        int item = rear.item;

        if (front == rear) { // single element
            front = rear = null;
        } else {
            rear = rear.prev;   // move rear back
            rear.next = null;   // break old link
        }
        return item;
    }

    /**
     * Returns the front element of deque.
     */
    public int getFront() {
        if (isEmpty())
            throw new RuntimeException("Deque is empty");
        return front.item;
    }

    /**
     * Returns the rear element of deque.
     */
    public int getRear() {
        if (isEmpty())
            throw new RuntimeException("Deque is empty");
        return rear.item;
    }

    /**
     * Checks if deque is empty
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Displays deque elements from front to rear.
     * Example:
     *   front-> 5 10 20 <-Rear
     */
    public void displayDeque() {
        Node current = front;
        System.out.print("front->");
        while (current != null) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println("<-Rear");
    }
}

/**
 * Test class for Deque
 * Demonstrates insertions, deletions and display
 */
class TestDeque {
    public static void main(String[] args) {
        Deque deque = new Deque();

        deque.insertAtRear(10);   // rear insertion
        deque.insertAtFront(5);   // front insertion
        deque.insertAtRear(20);   // rear insertion

        deque.displayDeque();     // front->5 10 20 <-Rear

        System.out.println(deque.getFront()); // 5
        System.out.println(deque.getRear());  // 20

        deque.deleteAtFront();    // removes 5
        deque.deleteAtRear();     // removes 20

        deque.displayDeque();     // front->10 <-Rear
    }
}
