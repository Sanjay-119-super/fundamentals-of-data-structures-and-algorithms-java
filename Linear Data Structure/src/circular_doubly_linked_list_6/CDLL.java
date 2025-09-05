package circular_doubly_linked_list_6;

public class CDLL {
    public  class Node{
        private Node prev;
        private int data;
        private Node next;

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
    private Node start;
    public CDLL(){
        start=null;
    }

    // Q.1
    public boolean isEmpty(){
        return start==null;
    }
    //Q.2
    public void insertAtStart(int data){
        Node node = new Node();
        node.setData(data);

        //case-1 empty list
        if (isEmpty()){
            start=node;
            node.setPrev(node);
            node.setNext(node);
        }
        //case-2 one or more node
        else{
            Node last = start.getPrev(); // for better understanding
            node.setNext(start);
            start.setPrev(node);
            node.setPrev(last);
            last.setNext(node);
            start=node;
        }
    }

    //Q.3
    public void insertAtEnd(int data){
        Node node = new Node();
        node.setData(data);

        //case-1 empty
        if (isEmpty()){
            node.setNext(node);
            node.setPrev(node);
            start=node;
        }
        //case-2 more node
        else {
            Node last = start.getPrev();
            node.setNext(start);
            node.setPrev(last);
            start.setPrev(node);
            last.setNext(node);
        }
    }
    //Q4
    public Node search(int data){
        if (isEmpty()) return null;
        else {
            Node temp=start;
            do {
                if (temp.getData() == data) {
                    return temp;
                }
                temp = temp.getNext();
            }while (temp !=start);
        }
        return null;
    }
    //Q5
    public void insertAfter(Node temp , int data){
        if (temp !=null){
            Node node = new Node();
            node.setData(data);

            node.setNext(temp.getNext());
            node.setPrev(temp);


            temp.getNext().setPrev(node);
            temp.setNext(node);

        }
    }
    //Q6
    public void deleteFirst(){
        if (!isEmpty()){

            //case-1 single node
            if (start.getNext()==start){
                start=null;
            }
            //case-2 more node
            else {
                Node last=start.getPrev();
                start=start.getNext();
                start.setPrev(last);
                last.setNext(start);
            }

        }
    }
    //Q7
    public void deleteLast(){
        //case-single node
        if (start.getNext()==start){
            start=null;
        }
        //case-2 more node
        else {
            Node last = start.getPrev(); //ise delete krna hai
            Node secondLast= last.getPrev();

            secondLast.setNext(start);
            start.setPrev(secondLast);

        }
    }
    public void deleteByItem(int data){
        Node search = search(data);
        if (search !=null){
            //case -1 single node
            if (start.getNext()==start && start==search){
                start=null;
                //case-2 start node
            } else if (start==search) {
                deleteFirst();
            }
            //case--3 last node
            else if(start.getPrev()==search){
                deleteLast();
            }
            //case-4 middle node
            else {
                search.getPrev().setNext(search.getNext());
                search.getNext().setPrev(search.getPrev());

            }
        }
    }
    public void display() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        Node temp = start;
        do {
            System.out.print(temp.getData() + " ");
            temp = temp.getNext();
        } while (temp != start);
        System.out.println();
    }
}

class CDLLTest {
    public static void main(String[] args) {
        CDLL list = new CDLL();

        System.out.println("=== Insert at Start ===");
        list.insertAtStart(10); // single node
        list.display(); // 10
        list.insertAtStart(20);
        list.insertAtStart(30);
        list.display(); // 30 20 10

        System.out.println("=== Insert at End ===");
        list.insertAtEnd(5);
        list.display(); // 30 20 10 5

        System.out.println("=== Insert After Node ===");
        CDLL.Node node20 = list.search(20);
        list.insertAfter(node20, 25);
        list.display(); // 30 20 25 10 5

        System.out.println("=== Delete First Node ===");
        list.deleteFirst();
        list.display(); // 20 25 10 5

        System.out.println("=== Delete Last Node ===");
        list.deleteLast();
        list.display(); // 20 25 10

        System.out.println("=== Delete Middle Node (25) ===");
        list.deleteByItem(25);
        list.display(); // 20 10

        System.out.println("=== Delete Start Node (20) ===");
        list.deleteByItem(20);
        list.display(); // 10

        System.out.println("=== Delete Last Node (10) ===");
        list.deleteByItem(10);
        list.display(); // List is empty
    }
}
