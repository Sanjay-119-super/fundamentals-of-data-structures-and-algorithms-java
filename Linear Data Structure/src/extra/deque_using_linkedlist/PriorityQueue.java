package extra.deque_using_linkedlist;

/**
 * Priority Queue implementation using Linked List.
 *
 * 👉 In a priority queue, each element has:
 *    - data (item)
 *    - priority (higher number = higher priority here)
 *
 * Rules:
 * - Insertion: element is placed in the list according to its priority.
 * - Deletion: always removes the element with the highest priority (front).
 * - Peek: check the highest priority element without removing it.
 */
public class PriorityQueue {

    /**
     * Node class for Linked List.
     * Stores:
     *  - item (data)
     *  - priority
     *  - next (pointer to next node)
     */
    public class Node {
        private Node next;
        private int item;
        private int priority;
    }

    private Node start; // pointer to first node (highest priority node)

    /**
     * Constructor: creates an empty priority queue
     */
    public PriorityQueue() {
        start = null;
    }

    /**
     * Inserts a new element according to its priority.
     *
     * Behind the scenes:
     *  - Case 1: If queue is empty OR new priority > current start priority,
     *            new node becomes the new start.
     *  - Case 2: Traverse the list until you find a node
     *            whose next node has smaller priority than new node.
     *            Insert new node there.
     *
     * Complexity: O(n) in worst case (need to traverse entire list).
     *
     * @param data element to insert
     * @param priority priority of element
     */
    public void insertAccordingToPriority(int data, int priority) {
        Node node = new Node();
        node.item = data;
        node.priority = priority;

        // Case 1: insert at front
        if (start == null || priority > start.priority) {
            node.next = start;
            start = node;
            return;
        }

        // Case 2: find correct position
        Node temp = start;
        while (temp.next != null && temp.next.priority >= priority) {
            temp = temp.next;
        }
        node.next = temp.next;
        temp.next = node;
    }

    /**
     * Deletes the highest priority element (front of the queue).
     * @return item of deleted node
     * @throws RuntimeException if queue is empty
     */
    public int deleteHighestPriority() {
        if (isEmpty())
            throw new RuntimeException("Priority Queue is empty");

        int item = start.item;
        start = start.next; // move start to next node
        return item;
    }

    /**
     * Returns the item of highest priority element
     * without removing it.
     * @return item of front node
     * @throws RuntimeException if queue is empty
     */
    public int peekHighest() {
        if (isEmpty())
            throw new RuntimeException("Priority Queue is empty");
        return start.item;
    }

    /**
     * Returns the highest priority value itself
     * (not the element, but the priority number).
     * @return priority of front node
     * @throws RuntimeException if queue is empty
     */
    public int getHighestPriorityElement() {
        if (isEmpty())
            throw new RuntimeException("Priority Queue is empty");
        return start.priority;
    }

    /**
     * Checks if priority queue is empty
     * @return true if empty, else false
     */
    public boolean isEmpty() {
        return start == null;
    }

    /**
     * Displays the priority queue
     * Format: [ (item priority=X) (item priority=Y) ... ]
     */
    public void display() {
        Node current = start;
        System.out.print("[ ");
        while (current != null) {
            System.out.print("(" + current.item + " priority=" + current.priority + ") ");
            current = current.next;
        }
        System.out.println("]");
    }
}

/**
 * Test class for PriorityQueue
 * Demonstrates insertion, display, peek, delete etc.
 */
class TestPriorityQueue {
    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();

        // Insert elements with priorities
        priorityQueue.insertAccordingToPriority(10, 1);
        priorityQueue.insertAccordingToPriority(20, 2);
        priorityQueue.insertAccordingToPriority(30, 3);

        // Display queue
        priorityQueue.display(); // [(30 priority=3) (20 priority=2) (10 priority=1)]

        // Peek highest priority
        System.out.println(priorityQueue.peekHighest()); // 30

        // Get highest priority value
        System.out.println(priorityQueue.getHighestPriorityElement()); // 3

        // Display again (unchanged after peek)
        priorityQueue.display();
    }
}
