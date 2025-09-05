package singly_linked_list_3;

/**
 * @author Sanjay Kumar
 * @version 1.0
 * * @summary
 * Yeh SinglyLinkedList class ka ek complete manual implementation hai.
 * Isko ek TRAIN 🚂 ki tarah samjho:
 * - Har Dibba (Coach) ek 'Node' hai.
 * - Poori train ek 'LinkedList' hai.
 * - Engine 'start' (ya 'head') hai, jisse poori train judi hui hai.
 * Hum is train ke saath alag-alag operations karenge jaise naya dibba jodna, hatana, etc.
 *
 * [Interview Corner 🎙️ - Important Technical Terms]
 * - Data Structure: Linked List ek 'Linear Data Structure' hai. Iska matlab data ek sequence mein store hota hai.
 * Lekin yeh Array ki tarah 'Contiguous' (ek saath judi hui memory mein) nahi hota. Iske nodes memory mein kahin bhi ho sakte hain.
 * - Dynamic Data Structure: Iska size run-time par (program chalte waqt) badh ya ghat sakta hai. Isliye ise 'Dynamic' kehte hain.
 * - Edge Cases: Interviews mein hamesha 'Edge Cases' ka dhyaan rakha jaata hai. Jaise: List khaali ho (empty list),
 * list mein sirf ek hi node ho (single-node list), ya operation pehle ya aakhiri node par ho raha ho.
 * - Space Complexity: Ek Linked List N elements ke liye O(N) space leti hai, kyunki har node extra space leta hai.
 */
public class SinglyLinkedList {

    /**
     * Yeh 'start' (jise 'head' bhi kehte hain) hamari train ka engine hai.
     * Agar 'start' null hai, iska matlab station par koi train hi nahi hai (list khaali hai).
     * Hum isse private rakhte hain taaki koi bahar se aakar engine ko direct na badal de (Encapsulation).
     *
     * [Interview Corner 🎙️]
     * - Head Pointer: 'start' ko 'Head Pointer' bhi kehte hain. Yeh poori list ka entry point hai.
     * Agar head pointer kho gaya, toh poori list memory mein lost ho jaati hai (is situation ko 'Memory Leak' kehte hain agar Garbage Collector na ho).
     */
    private Node start;

    /**
     * @summary
     * Yeh hamari LinkedList ka constructor hai.
     * Jab bhi koi nayi List banegi (new SinglyLinkedList()), toh shuru mein woh hamesha khaali hogi.
     * Isliye hum 'start' ko null set kar dete hain.
     */
    public SinglyLinkedList() {
        this.start = null;
    }

    //------------------ Inner Node Class ------------------//

    /**
     * @summary
     * Yeh 'Node' class hamari train ka ek dibba (coach) hai.
     * Yeh ek inner class hai kyunki ek Node ka wajood LinkedList ke bina kuch bhi nahi hai.
     * * Har Node ke paas do cheezein hoti hain:
     * 1. item (data): Dibbe ke andar baithe hue passengers.
     * 2. next (pointer): Woh hook jisse yeh dibba agle dibbe se juda hua hai.
     *
     * [Interview Corner 🎙️]
     * - Self-Referential Structure: Node ek 'Self-Referential Structure' hai. Iska matlab hai ki is class ka ek object (Node)
     * apne hi type ke dusre object (agle Node) ka reference/address rakhta hai.
     * - Pointer / Reference: 'next' variable ek pointer ya reference hai jo agle Node ko point karta hai. Aakhiri node ka 'next' hamesha NULL hota hai.
     */
    public class Node {
        private int item;
        private Node next;

        public Node(int data) {
            this.item = data;
            this.next = null;
        }

        public int getItem() { return this.item; }
        public void setItem(int item) { this.item = item; }
        public Node getNext() { return this.next; }
        public void setNext(Node next) { this.next = next; }
    }

    //------------------ Basic Operations ------------------//

    public boolean isEmpty() {
        return this.start == null;
    }

    /**
     * @summary
     * Yeh poori list ko console par print karta hai.
     * Taki hum dekh sakein ki hamare operations sahi se kaam kar rahe hain ya nahi.
     *
     * [Interview Corner 🎙️]
     * - Traversal: List ke har node par ek-ek karke jaana 'Traversal' kehlata hai.
     * Traversal ki Time Complexity hamesha O(N) hoti hai, jahan N list ke nodes ka number hai.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Train khaali hai! (List is empty)");
            return;
        }
        System.out.print("Train Current Status: [ENGINE/START] -> ");
        Node temp = this.start;
        while (temp != null) {
            System.out.print(temp.getItem() + " -> ");
            temp = temp.getNext();
        }
        System.out.println("NULL (Train yahan khatam)");
    }

    //------------------ Insertion Methods ------------------//

    /**
     * @summary
     * List ke ekdum shuru mein ek naya node (dibba) jodta hai.
     * @param data Naye node mein jo value daalni hai.
     *
     * [Interview Corner 🎙️]
     * - Time Complexity: O(1) ya 'Constant Time'. Is operation mein lagne wala time list ki size par depend nahi karta.
     * Yeh Linked List ka sabse bada advantage hai Array ke upar, jismein start mein insertion O(N) hota hai (shifting ke kaaran).
     */
    public void insertAtStart(int data) {
        Node newNode = new Node(data);
        newNode.setNext(this.start);
        this.start = newNode;
    }

    /**
     * @summary
     * List ke ekdum aakhir mein ek naya node (dibba) jodta hai.
     * @param data Naye node mein jo value daalni hai.
     *
     * [Interview Corner 🎙️]
     * - Time Complexity: O(N) ya 'Linear Time'. Is operation mein lagne wala time list ki size ke directly proportional hai,
     * kyunki humein aakhiri node tak traverse karna padta hai.
     * - Optimization: Isko optimize karne ke liye, hum ek extra 'tail' pointer maintain kar sakte hain jo hamesha last node ko point kare.
     * Agar 'tail' pointer ho, toh end mein insertion O(1) ho jaata hai.
     */
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            this.start = newNode;
            return;
        }
        Node temp = this.start;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(newNode);
    }

    /**
     * @summary
     * Ek diye gaye node ke aage ek naya node insert karta hai.
     * [Interview Corner 🎙️]
     * - Time Complexity: O(1). Kyunki humein pehle se hi woh node de diya gaya hai jiske aage insertion karna hai,
     * humein list ko traverse karne ki zaroorat nahi padti.
     */
    public void insertAfterNode(Node givenNode, int data) {
        if (givenNode == null) {
            System.out.println("Diya gaya node null hai. Insertion possible nahi.");
            return;
        }
        Node newNode = new Node(data);
        newNode.setNext(givenNode.getNext());
        givenNode.setNext(newNode);
    }

    //------------------ Searching Method ------------------//

    /**
     * @summary
     * Di hui value ko poori list mein search karta hai.
     * @param value Woh value jise hum dhoondh rahe hain.
     * @return Agar value mil jaati hai, toh woh poora Node return karta hai. Agar nahi milti, toh null return karta hai.
     *
     * [Interview Corner 🎙️]
     * - Time Complexity: O(N) in worst case (jab value aakhir mein ho ya ho hi na). O(1) in best case (jab value start mein hi mil jaaye).
     * - Loop Condition: Interviewer poochh sakta hai 'while(temp != null)' kyun use kiya, 'while(temp.getNext() != null)' kyun nahi.
     * Jawab: Kyunki 'temp.getNext() != null' waali condition aakhiri node ko check karne se pehle hi ruk jaayegi.
     */
    public Node searchNodeByValue(int value) {
        Node temp = this.start;
        while (temp != null) {
            if (temp.getItem() == value) {
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }

    //------------------ Deletion Methods ------------------//

    /**
     * @summary
     * List ka pehla node (engine) delete karta hai.
     * [Interview Corner 🎙️]
     * - Time Complexity: O(1) ya 'Constant Time'. Start se deletion hamesha bohot fast hota hai.
     * - Garbage Collection: Java mein, jab ek node unreachable ho jaata hai (koi pointer use point nahi karta),
     * toh 'Garbage Collector' (GC) use automatically memory se हटा deta hai. C/C++ mein yeh manually karna padta hai ('free()' se).
     */
    public void deleteFirst() {
        if (isEmpty()) {
            System.out.println("List pehle se khaali hai.");
            return;
        }
        this.start = this.start.getNext();
    }

    /**
     * @summary
     * List ka aakhiri node (dibba) delete karta hai.
     * [Interview Corner 🎙️]
     * - Time Complexity: O(N) ya 'Linear Time'. Kyunki aakhiri node ko delete karne ke liye humein
     * hamesha second-last node tak traverse karna padta hai.
     * - Singly vs Doubly Linked List: Yeh ek classic problem hai jo Singly Linked List ki kami dikhati hai.
     * Ek 'Doubly Linked List' mein har node ke paas 'next' aur 'previous' dono pointer hote hain.
     * Wahan hum tail pointer se seedhe second-last node par ja sakte hain, aur deletion O(1) ho jaata hai (agar tail pointer maintain ho).
     */
    public void deleteLast() {
        if (isEmpty()) {
            System.out.println("List pehle se khaali hai.");
            return;
        }
        if (this.start.getNext() == null) {
            this.start = null;
            return;
        }
        Node secondLast = this.start;
        while (secondLast.getNext().getNext() != null) {
            secondLast = secondLast.getNext();
        }
        secondLast.setNext(null);
    }

    /**
     * @summary
     * Di hui value ka node delete karta hai (sirf pehla occurrence).
     * @param data Woh value jiska node delete karna hai.
     *
     * [Interview Corner 🎙️]
     * - Time Complexity: O(N) in worst case, kyunki humein poori list search karni pad sakti hai.
     * - Your Approach vs Two-Pointer Approach: Aapka approach correct hai, lekin yeh list ko do baar traverse kar sakta hai (ek baar search, ek baar delete).
     * Standard interview solution 'Two-Pointer Approach' hai. Usmein ek 'previous' aur ek 'current' pointer ek saath aage badhte hain,
     * aur kaam ek hi traversal mein ho jaata hai, jo zyada efficient hai.
     */
    public void deleteByValue(int data) {
        if (isEmpty()) {
            System.out.println("List khaali hai, kuch delete nahi kar sakte.");
            return;
        }
        Node nodeToDelete = searchNodeByValue(data);
        if (nodeToDelete == null) {
            System.out.println("Value " + data + " list mein nahi mili.");
            return;
        }
        if (this.start == nodeToDelete) {
            deleteFirst();
            return;
        }
        Node temp = this.start;
        while (temp.getNext() != nodeToDelete) {
            temp = temp.getNext();
        }
        temp.setNext(nodeToDelete.getNext());
    }

    //------------------ Main Method for Testing ------------------//

    public static void main(String[] args) {
        System.out.println("Chaliye Singly Linked List (Train) ka live demo dekhte hain!");
        SinglyLinkedList train = new SinglyLinkedList();

        System.out.println("\n--- Step 1: Nayi Train Bani Hai ---");
        train.display();

        System.out.println("\n--- Step 2: Engine ke paas Dibbe Jodte Hain (insertAtStart) ---");
        train.insertAtStart(10);
        train.insertAtStart(20);
        train.insertAtStart(30);
        train.display();

        System.out.println("\n--- Step 3: Aakhir mein Dibbe Jodte Hain (insertAtEnd) ---");
        train.insertAtEnd(5);
        train.insertAtEnd(1);
        train.display();

        System.out.println("\n--- Step 4: Dibba No. 10 Dhoondhte Hain (searchNodeByValue) ---");
        SinglyLinkedList.Node foundNode = train.searchNodeByValue(10);
        if (foundNode != null) {
            System.out.println("Dibba mil gaya! Uska number hai: " + foundNode.getItem());
        }

        System.out.println("\n--- Step 5: Dibba No. 10 ke aage ek naya Dibba (15) Jodte Hain (insertAfterNode) ---");
        train.insertAfterNode(foundNode, 15);
        train.display();

        System.out.println("\n--- Step 6: Engine (Pehla Dibba) Hatate Hain (deleteFirst) ---");
        train.deleteFirst();
        train.display();

        System.out.println("\n--- Step 7: Aakhiri Dibba Hatate Hain (deleteLast) ---");
        train.deleteLast();
        train.display();

        System.out.println("\n--- Step 8: Dibba No. 15 ko Hatate Hain (deleteByValue) ---");
        train.deleteByValue(15);
        train.display();

        System.out.println("\n--- Step 9: Dibba No. 99 ko Hatane ki Koshish (jo hai hi nahi) ---");
        train.deleteByValue(99);
        train.display();

        System.out.println("\n--- Step 10: Ek-ek karke saare dibbe hatate hain ---");
        train.deleteByValue(20);
        train.display();
        train.deleteLast();
        train.display();
        train.deleteFirst();
        train.display();
    }
}


