package extra.deque_using_linkedlist;

public class PriorityQueue {
    public class Node{
       private Node next;
       private int item;
       private int priority;
    }
    private Node start;

    public PriorityQueue(){
        start=null;
    }

    public void insertAccordingToPriority(int data, int priority){
        Node node = new Node();
        node.item=data;
        node.priority=priority;

        if (start == null || priority > start.priority) {
            node.next = start;
            start=node;
            return;
        }

        Node temp =start;
        while (temp.next != null && temp.next.priority >= priority){
            temp=temp.next;
        }
        node.next=temp.next;
        temp.next=node;

    }
    public int deleteHighestPriority(){
        if (isEmpty())
            throw new RuntimeException("Priority Queue is empty");
        int item = start.item;
        start = start.next;
        return item;
    }

    public int peekHighest(){
        if (isEmpty())
            throw new RuntimeException("Priority Queue is empty");
        return start.item;
    }

    public int getHighestPriorityElement(){
        if (isEmpty())
            throw new RuntimeException("Priority Queue is empty");
        return start.priority;
    }

    public boolean isEmpty(){
        return start==null;
    }

    public void display(){
        Node current = start;
        System.out.print("[ ");
        while (current != null){
            System.out.print("(" + current.item + "priority=" + current.priority + ") ");
            current=current.next;
        }
        System.out.println("]");
    }
}

class TestPriorityQueue{
    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.insertAccordingToPriority(10,1);
        priorityQueue.insertAccordingToPriority(20,2);
        priorityQueue.insertAccordingToPriority(30,3);

        priorityQueue.display();
        System.out.println(priorityQueue.peekHighest());
        priorityQueue.getHighestPriorityElement();
        priorityQueue.display();

    }
}