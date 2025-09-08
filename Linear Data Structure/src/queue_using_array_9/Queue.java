package queue_using_array_9;

/**
 * A simple Queue implementation using a fixed-size array.
 *
 * Queue follows the FIFO (First In, First Out) principle:
 * - The element inserted first will be deleted first.
 *
 * It supports enqueue (insert), dequeue (deleteFront),
 * viewing front/rear elements, checking size,
 * and checking if queue is full or empty.
 */
public class Queue {
    private int rear;   // points to the last inserted element
    private int front;  // points to the first element
    int[] ptr;          // array used to store elements

    /**
     * Constructs a queue with given size.
     *
     * @param size capacity of the queue
     * @throws QueueExceptionHandler if size < 1
     */
    public Queue(int size) throws QueueExceptionHandler {
        if(size < 1)
            throw new QueueExceptionHandler(QueueExceptionHandler.INVALID_SIZE);
        ptr = new int[size];
        front = -1;
        rear = -1;
    }

    /**
     * Inserts (enqueues) an element at the rear of the queue.
     *
     *
     * Behind the scenes:
     * - If queue is empty (front = -1, rear = -1),
     *   both front and rear are set to 0 and the element is placed.
     * - Otherwise, rear is incremented and the new element is stored.
     *
     *
     * @param data element to insert
     * @throws QueueExceptionHandler if the queue is already full
     */
    public void insert(int data) throws QueueExceptionHandler {
        if (isFull())
            throw new QueueExceptionHandler(QueueExceptionHandler.QUEUE_OVERFLOW);

        //case-1 if empty
        if (isEmpty()) {
            rear = 0;
            front = 0;
            //if already have element
        } else {
            rear++;
        }
        ptr[rear] = data;
    }

    /**
     * Returns the element at the rear (last inserted element).
     *
     * @return element at rear
     * @throws QueueExceptionHandler if the queue is empty
     */
    public int viewRear() throws QueueExceptionHandler {
        if (isEmpty())
            throw new QueueExceptionHandler(QueueExceptionHandler.QUEUE_UNDERFLOW);
        return ptr[rear];
    }

    /**
     * Returns the element at the front (next element to be deleted).
     *
     * @return element at front
     * @throws QueueExceptionHandler if the queue is empty
     */
    public int viewFront() throws QueueExceptionHandler {
        if (isEmpty())
            throw new QueueExceptionHandler(QueueExceptionHandler.QUEUE_UNDERFLOW);
        return ptr[front];
    }

    /**
     * Deletes (dequeues) the element from the front of the queue.
     *
     *
     * Behind the scenes:
     * - The element at 'front' is logically removed.
     * - Then front is incremented.
     * - If after incrementing, front > rear → queue becomes empty,
     *   so both front and rear are reset to -1.
     *
     *
     * @throws QueueExceptionHandler if the queue is empty
     */
    public void deleteFront() throws QueueExceptionHandler {
        if (isEmpty())
            throw new QueueExceptionHandler(QueueExceptionHandler.QUEUE_UNDERFLOW);
        front++;
        if (front > rear) {
            rear = -1;
            front = -1;
        }
    }

    /**
     * Counts the number of elements currently in the queue.
     *
     *
     * Behind the scenes:
     * - Formula = (rear - front + 1)
     * - Example: if front=1, rear=3 → count = (3-1+1)=3
     *
     *
     * @return number of elements in queue
     * @throws QueueExceptionHandler if the queue is empty
     */
    public int countElements() throws QueueExceptionHandler {
        if (isEmpty())
            throw new QueueExceptionHandler(QueueExceptionHandler.QUEUE_UNDERFLOW);
        return rear - front + 1;
    }

    /**
     * Checks if the queue is full.
     *
     * @return true if rear is at the last index, else false
     */
    public boolean isFull() {
        return rear == ptr.length - 1;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if both front and rear are -1
     */
    public boolean isEmpty() {
        return rear == -1 && front == -1;
    }
}

/**
 * Custom Exception class for Queue operations.
 * Defines error messages for invalid size, overflow, and underflow.
 */
class QueueExceptionHandler extends Exception {
    public static final String INVALID_SIZE = "Invalid size, please enter only positive number";
    public static final String QUEUE_OVERFLOW = "Queue is full";
    public static final String QUEUE_UNDERFLOW = "Queue is empty";

    /**
     * Constructs an exception with given message.
     *
     * @param sms the message to display
     */
    public QueueExceptionHandler(String sms) {
        super(sms);
    }
}



 class TestQueue {
    public static void main(String[] args) {
        try {
            // Queue of size 5
            Queue q = new Queue(5);

            // 1. Insert elements
            q.insert(10);
            q.insert(20);
            q.insert(30);
            q.insert(40);

            System.out.println("Front element: " + q.viewFront()); // 10
            System.out.println("Rear element: " + q.viewRear());   // 40
            System.out.println("Count: " + q.countElements());     // 4

            // 2. Delete some elements
            q.deleteFront(); // removes 10
            System.out.println("After deleting one element, front: " + q.viewFront()); // 20
            System.out.println("Count: " + q.countElements()); // 3

            q.deleteFront(); // removes 20
            System.out.println("After deleting another, front: " + q.viewFront()); // 30
            System.out.println("Count: " + q.countElements()); // 2

            // 3. Insert more elements until full
            q.insert(50);
            q.insert(60);
            System.out.println("Rear element after insertions: " + q.viewRear()); // 60
            System.out.println("Count: " + q.countElements()); // 4

            // 4. Overflow test
            System.out.println("Trying to insert beyond capacity...");
            q.insert(70); // should throw exception

        } catch (QueueExceptionHandler e) {
            System.out.println("Exception: " + e.getMessage());
        }

        try {
            Queue q2 = new Queue(3);

            // 5. Underflow test
            System.out.println("\nUnderflow test:");
            q2.deleteFront(); // should throw exception

        } catch (QueueExceptionHandler e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
