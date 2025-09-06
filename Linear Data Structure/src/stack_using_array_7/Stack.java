package stack_using_array_7;

/**
 * A simple implementation of Stack using arrays.
 * Supports push, pop, peek, isEmpty, isFull operations.
 *
 * Stack follows LIFO (Last In First Out) principle.
 */
public class Stack {
    private int top;
    private int[] ptr;

    /**
     * Creates a new stack with given size.
     *
     * @param size the capacity of the stack
     * @throws StackExceptionHandle if size <= 0
     */
    public Stack(int size) throws StackExceptionHandle {
        if (size <= 0)
            throw new StackExceptionHandle(StackExceptionHandle.STACK_INVALID);
        ptr = new int[size];
        top = -1;
    }

    /**
     * Pushes an element onto the stack.
     *
     * @param data element to push
     * @throws StackExceptionHandle if stack is full
     */
    public void push(int data) throws StackExceptionHandle {
        if (isFull())
            throw new StackExceptionHandle(StackExceptionHandle.STACK_OVERFLOW);
        top++;
        ptr[top] = data;
    }

    /**
     * Returns the top element without removing it.
     *
     * @return top element of stack
     * @throws StackExceptionHandle if stack is empty
     */
    public int peek() throws StackExceptionHandle {
        if (isEmpty())
            throw new StackExceptionHandle(StackExceptionHandle.STACK_UDERFLOW);
        return ptr[top];
    }

    /**
     * Removes and returns the top element of the stack.
     *
     * @return top element of stack
     * @throws StackExceptionHandle if stack is empty
     */
    public int pop() throws StackExceptionHandle {
        if (isEmpty())
            throw new StackExceptionHandle(StackExceptionHandle.STACK_UDERFLOW);
        int peek = ptr[top];
        top--;
        return peek;
    }

    /**
     * Prints all stack elements from bottom to top.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
        } else {
            System.out.print("Stack elements: ");
            for (int i = 0; i <= top; i++) {
                System.out.print(ptr[i] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Checks if stack is full.
     *
     * @return true if stack is full, else false
     */
    public boolean isFull() {
        return ptr.length - 1 == top;
    }

    /**
     * Checks if stack is empty.
     *
     * @return true if stack is empty, else false
     */
    public boolean isEmpty() {
        return top == -1;
    }
}

/**
 * Custom exception handler for Stack operations.
 */
class StackExceptionHandle extends Exception {
    public static final String STACK_OVERFLOW = "Stack is full";
    public static final String STACK_UDERFLOW = "Stack is empty";
    public static final String STACK_INVALID = "Stack size is invalid";

    public StackExceptionHandle(String sms) {
        super(sms);
    }
}

/**
 * Test class for Stack implementation.
 */
class TestStack {
    public static void main(String[] args) {
        try {
            Stack st = new Stack(5);

            st.push(10);
            st.push(20);
            st.push(30);
            st.push(40);

            st.display();  // 10 20 30 40

            System.out.println("Peek: " + st.peek()); // 40
            System.out.println("Pop: " + st.pop());   // 40
            System.out.println("Pop: " + st.pop());   // 30

            st.display();  // 10 20

            st.push(50);
            st.push(60);
            st.push(70);   // Overflow if capacity exceeded

            st.display();  // 10 20 50 60 70

            // Underflow test
            while (!st.isEmpty()) {
                System.out.println("Popped: " + st.pop());
            }
            st.pop(); // Underflow exception

        } catch (StackExceptionHandle e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
