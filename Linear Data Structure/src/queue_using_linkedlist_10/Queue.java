package queue_using_linkedlist_10;

/**
 * Queue implementation using Linked List.
 * Follows FIFO principle (First In First Out).
 *
 * - Insert happens at the rear.
 * - Delete happens from the front.
 */
public class Queue {
    public class Node {
        private int item;   // data of the node
        private Node next;  // pointer to next node
    }

    private Node front; // points to the first node
    private Node rear;  // points to the last node


    /**
     * Constructor: creates an empty queue
     */
    public Queue() {
        rear = null;
        front = null;
    }

    /**
     * Inserts an element at the rear of the queue.
     * @param data element to be inserted
     */
    public void insertAtRear(int data) {
        Node node = new Node();
        node.item = data;
        node.next = null;

        // Case 1: Empty queue
        if (rear == null) {
            rear = node;
            front = node;
        }
        // Case 2: Queue already has elements
        else {
            rear.next = node; // link new node after rear
            rear = node;      // move rear pointer
        }
    }

    /**
     * Returns the front element of the queue.
     * @return front element
     * @throws QueueLinedListExceptionHandler if queue is empty
     */
    public int getFront() throws QueueLinedListExceptionHandler {
        if (isEmptyQueue())
            throw new QueueLinedListExceptionHandler(QueueLinedListExceptionHandler.QUEUE_UNDERFLOW);
        return front.item;
    }

    /**
     * Returns the rear element of the queue.
     * @return rear element
     * @throws RuntimeException if queue is empty
     */
    public int getRear() {
        if (rear == null)
            throw new RuntimeException("Empty queue");
        return rear.item;
    }

    /**
     * Deletes the front element (FIFO).
     * @throws QueueLinedListExceptionHandler if queue is empty
     */
    public void delete() throws QueueLinedListExceptionHandler {
        if (isEmptyQueue())
            throw new QueueLinedListExceptionHandler(QueueLinedListExceptionHandler.QUEUE_UNDERFLOW);

        // Case 1: Only one node
        if (front == rear) {
            front = null;
            rear = null;
        }
        // Case 2: More than one node
        else {
            front = front.next; // move front ahead
        }
    }

    /**
     * Counts number of elements in the queue.
     * Traverses from front to rear.
     * @return count of nodes
     */
    public int count() {
        int c = 0;
        Node t = front;
        while (t != null) {
            c++;
            t = t.next;
        }
        return c;
    }

    /**
     * Checks if the queue is empty.
     * @return true if empty, else false
     */
    public boolean isEmptyQueue() {
        return front == null;
    }
}

/**
 * Custom Exception class for Queue operations
 */
class QueueLinedListExceptionHandler extends Exception {
    public static final String QUEUE_UNDERFLOW = "Queue is empty";

    public QueueLinedListExceptionHandler(String sms) {
        super(sms);
    }
}


 class TestQueue {
    public static void main(String[] args) {
        try {
            Queue q = new Queue();

            // Insert elements
            q.insertAtRear(10);
            q.insertAtRear(20);
            q.insertAtRear(30);

            System.out.println("Front: " + q.getFront()); // 10
            System.out.println("Rear: " + q.getRear());   // 30
            System.out.println("Count: " + q.count());    // 3

            // Delete elements
            q.delete(); // removes 10
            System.out.println("Front after 1 delete: " + q.getFront()); // 20
            System.out.println("Count: " + q.count()); // 2

            q.delete(); // removes 20
            System.out.println("Front after 2 deletes: " + q.getFront()); // 30
            System.out.println("Count: " + q.count()); // 1

            q.delete(); // removes 30
            System.out.println("Is queue empty? " + q.isEmptyQueue()); // true

            // Underflow test
            System.out.println("Trying to delete from empty queue...");
            q.delete(); // should throw exception

        } catch (QueueLinedListExceptionHandler e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
