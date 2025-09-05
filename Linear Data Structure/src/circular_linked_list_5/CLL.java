package Circular_Linked_List_5;

public class CLL {

    public static class Node {
        private int item;
        private Node next;

        public int getItem() { return item; }
        public void setItem(int item) { this.item = item; }
        public Node getNext() { return next; }
        public void setNext(Node next) { this.next = next; }
    }

    private Node last;

    public CLL() { last = null; }

    public boolean isEmpty() { return last == null; }

    public void printList() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        Node temp = last.getNext();
        System.out.print("List: ");
        do {
            System.out.print(temp.getItem() + " -> ");
            temp = temp.getNext();
        } while (temp != last.getNext());
        System.out.println("(head: " + last.getNext().getItem() + ")");
    }

    public void insertAtBeginning(int data) {
        Node newNode = new Node();
        newNode.setItem(data);

        if (isEmpty()) {
            last = newNode;
            newNode.setNext(last);
        } else {
            newNode.setNext(last.getNext());
            last.setNext(newNode);
        }
    }

    public void insertAtEnd(int data) {
        Node newNode = new Node();
        newNode.setItem(data);

        if (isEmpty()) {
            last = newNode;
            newNode.setNext(last);
        } else {
            newNode.setNext(last.getNext());
            last.setNext(newNode);
            last = newNode;
        }
    }

    public Node search(int data) {
        if (isEmpty()) return null;

        Node temp = last.getNext();
        do {
            if (temp.getItem() == data) return temp;
            temp = temp.getNext();
        } while (temp != last.getNext());

        return null;
    }

    public void insertAfter(Node givenNode, int data) {
        if (givenNode == null) throw new IllegalArgumentException("Given node cannot be null.");

        Node newNode = new Node();
        newNode.setItem(data);

        newNode.setNext(givenNode.getNext());
        givenNode.setNext(newNode);

        if (givenNode == last) last = newNode;
    }

    public void deleteFirst() {
        if (isEmpty()) throw new ListIsEmptyException("Cannot delete from an empty list.");

        if (last.getNext() == last) {
            last = null;
        } else {
            last.setNext(last.getNext().getNext());
        }
    }

    public void deleteLast() {
        if (isEmpty()) throw new ListIsEmptyException("Cannot delete from an empty list.");

        if (last.getNext() == last) {
            last = null;
        } else {
            Node temp = last.getNext();
            while (temp.getNext() != last) {
                temp = temp.getNext();
            }
            temp.setNext(last.getNext());
            last = temp;
        }
    }

    public void deleteNodeByValue(int data) {
        if (isEmpty()) throw new ListIsEmptyException("Cannot delete from an empty list.");

        Node current = last.getNext();
        Node previous = last;
        boolean found = false;

        do {
            if (current.getItem() == data) {
                found = true;
                if (current == last.getNext()) { // first node
                    deleteFirst();
                } else if (current == last) { // last node
                    deleteLast();
                } else { // middle node
                    previous.setNext(current.getNext());
                }
                break;
            }
            previous = current;
            current = current.getNext();
        } while (current != last.getNext());

        if (!found) throw new NodeNotFoundException("Value " + data + " not found in the list.");
    }

    // --- Custom Exceptions ---
    public static class ListIsEmptyException extends RuntimeException {
        public ListIsEmptyException(String msg) { super(msg); }
    }

    public static class NodeNotFoundException extends RuntimeException {
        public NodeNotFoundException(String msg) { super(msg); }
    }
}

 class CLLTes {
    public static void main(String[] args) {
        CLL list = new CLL();

        // --- Populating the list ---
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        list.printList();

        // --- Testing deletion and exceptions ---
        try {
            System.out.println("\nDeleting value 20...");
            list.deleteNodeByValue(20);
            list.printList();

            System.out.println("\nDeleting last node...");
            list.deleteLast();
            list.printList();

            System.out.println("\nAttempting to delete non-existent value 99...");
            list.deleteNodeByValue(99); // Will throw exception

        } catch (CLL.NodeNotFoundException e) {
            System.err.println("Error caught: " + e.getMessage());
        }

        try {
            System.out.println("\nDeleting all remaining nodes...");
            list.deleteFirst(); // Deletes 10
            list.printList();

            list.deleteFirst(); // List becomes empty
            list.printList();

            System.out.println("\nAttempting to delete from an empty list...");
            list.deleteFirst(); // Will throw exception

        } catch (CLL.ListIsEmptyException e) {
            System.err.println("Error caught: " + e.getMessage());
        }

        System.out.println("\nProgram finished.");
    }
}
