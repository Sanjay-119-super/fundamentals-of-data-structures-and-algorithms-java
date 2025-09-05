package singly_linked_list_3;

public class SSL {
    private Node start;

    public SSL(){
        start=null;
    }

    public  class Node{
        private int item;
        private Node next;


        public int getItem() {
            return item;
        }

        public void setItem(int item) {
            this.item = item;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public boolean isEmpty(){
       return start==null;
    }
    public void insertAtStart(int data){

        Node newNode = new Node(); // new node create to add at start
        newNode.setItem(data); // set data in new node
        newNode.setNext(start); // now new node points to old leader node
        start=newNode; // now start points to new node that was add at start



    }

    public  void insertAtEnd(int data){
        Node newNode = new Node(); // new original node
        Node currentStart = start; // temp node for loop cose save original node

        newNode.setItem(data); // set data
         // Eage Case-1 no nodes
        if(isEmpty()){
            start=newNode;
            return;
        }
        // Eage Case-2 more nodes
        else {
            while (currentStart.getNext()!=null){ // loop run till null found means last node
                currentStart= currentStart.getNext(); // move loop one by one to next node
            }
            currentStart.setNext(newNode); // link new node at end

        }

    }

    public Node searchNodeByValue(int value){
        Node temp = start; // for data
        if (isEmpty()) // if list khali ho
            return null; // list hai hi nhi
        else {
            while (temp != null){ // chlo last tk
                if (temp.getItem() == value) // match data to current node then return
                    return temp;
                temp=temp.getNext(); // if no match move to next node
            }

        }
        return null; // nhi mila to
    }

    public void insertAtGivenNode(int data , Node givenNode){
        Node newNode = new Node();
        newNode.setItem(data);
        if (givenNode == null){
            throw new RuntimeException("Given node can not be null");
        }

        newNode.setNext(givenNode.getNext());
        givenNode.setNext(newNode);
    }

    public void deleteFirst(){
        if(isEmpty()){
            System.out.println("List is empty already");
            return;
        }
        // move to next node the first node take GC
        start=start.getNext();
    }

    public void deleteLast(){
        // case-1 empty list
        if (isEmpty()) {
            System.out.println("Empty list already");
            return;
        }
        // case-2 only one node special case
        if (start.getNext()==null){
            start=null;
            return;
        }
        // case-3 more then one node
        Node temp=start;
        while (temp.getNext().getNext()!=null){

            temp=temp.getNext();
        }
        temp.setNext(null);

    }
    public void deleteNode(int data) {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        // Case 1: First node is the target
        if (start.getItem() == data) {
            start = start.getNext();
            return;
        }

        // Case 2: Node somewhere in middle or end
        Node prev = start;
        Node curr = start.getNext();

        while (curr != null) {
            if (curr.getItem() == data) {
                prev.setNext(curr.getNext()); // skip current node
                return;
            }
            prev = curr;
            curr = curr.getNext();
        }

        System.out.println("Node with value " + data + " not found");
    }


    public void display() {
        Node temp = start;
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        while (temp != null) {
            System.out.print(temp.getItem() + " -> ");
            temp = temp.getNext();
        }
        System.out.println("null");
    }


}

class SLLTest{
    public static void main(String[] args) {
        SSL list = new SSL();

        System.out.println("Insert at start:");
        list.insertAtStart(10);
        list.insertAtStart(5);
        list.display(); // 5 -> 10 -> null

        System.out.println("Insert at end:");
        list.insertAtEnd(20);
        list.insertAtEnd(25);
        list.display(); // 5 -> 10 -> 20 -> 25 -> null

        System.out.println("Insert at given node:");
        SSL.Node node10 = list.searchNodeByValue(10);
        list.insertAtGivenNode(15, node10);
        list.display(); // 5 -> 10 -> 15 -> 20 -> 25 -> null

        System.out.println("Delete first:");
        list.deleteFirst();
        list.display(); // 10 -> 15 -> 20 -> 25 -> null

        System.out.println("Delete last:");
        list.deleteLast();
        list.display(); // 10 -> 15 -> 20 -> null

        System.out.println("Delete node (value = 15):");
        list.deleteNode(15);
        list.display(); // 10 -> 20 -> null

        System.out.println("Search node (value = 20):");
        SSL.Node searchNode = list.searchNodeByValue(20);
        System.out.println(searchNode != null ? "Found: " + searchNode.getItem() : "Not found"); // Found: 20

        System.out.println("Delete remaining nodes:");
        list.deleteNode(10);
        list.deleteNode(20);
        list.display(); // List is empty
    }

}
