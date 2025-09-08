package stack_using_linkedlist_8;

/**
 * A Stack implementation using a singly linked list.
 *
 *
 * This class provides basic stack operations such as push, pop, peek,
 * isEmpty, reverse, and display.
 *
 *
 * Stack follows the LIFO (Last-In First-Out) principle.
 */
public class Stack {

    /**
     * Inner class representing a node in the linked list.
     * Each node stores an integer item and a reference to the next node.
     */
    private class Node {
        int item;
        Node next;

        /**
         * Constructs a new Node with given data.
         *
         * @param item the value to store in this node
         */
        Node(int item) {
            this.item = item;
            this.next = null;
        }
    }

    // Points to the top element of the stack
    private Node top;

    /**
     * Constructs an empty stack.
     */
    public Stack() {
        top = null;
    }

    /**
     * Pushes (inserts) a new element onto the top of the stack.
     *
     * @param data the element to push
     */
    public void push(int data) {
        Node node = new Node(data);
        node.next = top;
        top = node;
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the top element of the stack
     * @throws RuntimeException if the stack is empty
     */
    public int peek() {
        if (isEmpty())
            throw new RuntimeException("Stack is empty");
        return top.item;
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element that was removed
     * @throws RuntimeException if the stack is empty
     */
    public int pop() {
        if (isEmpty())
            throw new RuntimeException("Stack is empty");
        int item = top.item;
        top = top.next; // move top to the next node
        return item;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Reverses the current stack.
     * This operation creates a temporary stack and pushes elements
     * from the original stack into it.
     */
    public void reverse() {
        Stack temp = new Stack();
        while (!isEmpty()) {
            temp.push(pop());
        }
        this.top = temp.top;
    }

    /**
     * Displays the elements of the stack from top to bottom.
     * If the stack is empty, prints a message.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        Node curr = top;
        System.out.print("Stack: ");
        while (curr != null) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}

/**
 * Test class for Stack using linked list.
 */
class TestStackLL {
    public static void main(String[] args) {
        Stack st = new Stack();

        st.push(10);
        st.push(20);
        st.push(30);
        st.push(40);

        st.display();  // Output: 40 30 20 10

        System.out.println("Peek: " + st.peek()); // Output: 40
        System.out.println("Pop: " + st.pop());   // Output: 40
        st.display();  // Output: 30 20 10

        st.reverse();
        st.display();  // Output: 10 20 30
    }
}
