package dynamic_array_ds_2;

/**
 * DynamicArray ek custom array implementation hai jo 
 * size automatically increase (double) aur decrease (half) karta hai
 * jab zaroorat hoti hai. 
 *
 * @author Sanjay
 * Chapter-3
 *
 */
public class DynamicArray {
    private int lastIndex; // last filled element ka index
    private int[] ptr;     // actual array pointer

    /**
     * Default constructor.
     * Array ki size 1 se start hoti hai.
     */
    public DynamicArray(){
        ptr = new int[1];
        lastIndex = -1;
    }

    /**
     * Constructor with initial size.
     * Agar user ne 0 ya negative diya toh size forcefully 1 ho jaayega.
     *
     * @param size Initial capacity
     */
    public DynamicArray(int size){
        if (size <= 0) size = 1;
        ptr = new int[size];
        lastIndex = -1;
    }

    /**
     * Capacity double kar deta hai jab array full ho jaaye.
     * Saare purane elements copy ho jaate hai naye array me.
     */
    public void doubleArray(){
        int[] temp = new int[ptr.length * 2];
        for (int i = 0; i <= lastIndex; i++) {
            temp[i] = ptr[i];
        }
        ptr = temp;
    }

    /**
     * Capacity ko aadha kar deta hai jab elements 25% se kam ho.
     * Lekin elements ko preserve karke copy kiya jaata hai.
     */
    public void halfArray(){
        int n = ptr.length / 2;
        if (n <= lastIndex) return; // shrink possible nahi
        int[] temp = new int[n];
        for (int i = 0; i <= lastIndex; i++) {
            temp[i] = ptr[i];
        }
        ptr = temp;
    }

    /**
     * End me ek naya element add karta hai.
     * Agar array full ho toh pehle double hota hai.
     *
     * @param data jo element add karna hai
     */
    public void appendAt(int data){
        if (isFull()) doubleArray();
        ptr[++lastIndex] = data;
    }

    /**
     * Specific index par ek naya element insert karta hai.
     * Purane elements ko right shift karke jagah banayi jaati hai.
     *
     * @param index jis position pe insert karna hai
     * @param newData jo element insert karna hai
     * @throws DynamicExceptionHandler agar index invalid hai
     */
    public void insertAt(int index, int newData) throws DynamicExceptionHandler {
        if (isFull()) doubleArray();
        if (index < 0 || index > lastIndex + 1)
            throw new DynamicExceptionHandler(DynamicExceptionHandler.INVALID_INDEX);

        for (int i = lastIndex; i >= index; i--) {
            ptr[i + 1] = ptr[i];
        }
        ptr[index] = newData;
        lastIndex++;
    }

    /**
     * Kisi element ko edit karta hai given index par.
     *
     * @param index jis position ka data change karna hai
     * @param newData jo naya value daalna hai
     * @throws DynamicExceptionHandler agar array empty hai ya index galat hai
     */
    public void editData(int index, int newData) throws DynamicExceptionHandler {
        if (isEmpty())
            throw new DynamicExceptionHandler(DynamicExceptionHandler.ARRAY_UNDERFLOW);
        if (index < 0 || index > lastIndex)
            throw new DynamicExceptionHandler(DynamicExceptionHandler.INVALID_INDEX);
        ptr[index] = newData;
    }

    /**
     * Kisi index ka element delete karta hai aur baaki ko left shift karta hai.
     * Agar elements kam ho gaye toh capacity shrink bhi ho jaati hai.
     *
     * @param index jis position ka element delete karna hai
     * @throws DynamicExceptionHandler agar array empty hai ya index galat hai
     */
    public void deleteElement(int index) throws DynamicExceptionHandler {
        if (isEmpty())
            throw new DynamicExceptionHandler(DynamicExceptionHandler.ARRAY_UNDERFLOW);
        if (index < 0 || index > lastIndex)
            throw new DynamicExceptionHandler(DynamicExceptionHandler.INVALID_INDEX);

        for (int i = index; i < lastIndex; i++) {
            ptr[i] = ptr[i + 1];
        }
        lastIndex--;

        // Correct way to check if element count is less than 25% of capacity
        if (ptr.length > 1 && (lastIndex + 1) <= ptr.length / 4) {
            halfArray();
        }
    }

    /**
     * Total kitne elements currently array me hai.
     *
     * @return element count
     */
    public int getTotalElements(){
        return lastIndex + 1;
    }

    /**
     * Kisi index ka value return karta hai.
     *
     * @param index jis element ka data chahiye
     * @return value at index
     * @throws DynamicExceptionHandler agar array empty ya index galat
     */
    public int getValue(int index) throws DynamicExceptionHandler{
        if (isEmpty())
            throw new DynamicExceptionHandler(DynamicExceptionHandler.ARRAY_UNDERFLOW);
        if (index < 0 || index > lastIndex)
            throw new DynamicExceptionHandler(DynamicExceptionHandler.INVALID_INDEX);
        return ptr[index];
    }

    /**
     * Current array capacity return karta hai (matlab kitna space hai).
     */
    public int getCapacity(){
        return ptr.length;
    }

    /**
     * Check karta hai ki array full hai ya nahi.
     */
    public boolean isFull(){
        return lastIndex + 1 == ptr.length;
    }

    /**
     * Check karta hai ki array empty hai ya nahi.
     */
    public boolean isEmpty(){
        return lastIndex == -1;
    }
}
 class DynamicArrayDemo {
    public static void main(String[] args) {
        try {
            // 1. Constructor test
            System.out.println("==== Constructor Test ====");
            DynamicArray arr1 = new DynamicArray();
            DynamicArray arr2 = new DynamicArray(5);
            System.out.println("arr1 capacity: " + arr1.getCapacity()); // 1
            System.out.println("arr2 capacity: " + arr2.getCapacity()); // 5

            // 2. appendAt()
            System.out.println("\n==== appendAt() Test ====");
            DynamicArray arr = new DynamicArray(2);
            arr.appendAt(10);
            arr.appendAt(20);
            arr.appendAt(30); // capacity double hogi
            arr.appendAt(40);
            System.out.println("Elements: " + arr.getTotalElements()); // 4
            System.out.println("Capacity: " + arr.getCapacity()); // >=4

            // 3. insertAt()
            System.out.println("\n==== insertAt() Test ====");
            arr.insertAt(2, 99);
            for (int i = 0; i < arr.getTotalElements(); i++) {
                System.out.print(arr.getValue(i) + " ");
            }
            System.out.println();

            // 4. editData()
            System.out.println("\n==== editData() Test ====");
            arr.editData(2, 55);
            System.out.println("After editing index 2: " + arr.getValue(2)); // 55

            // 5. deleteElement()
            System.out.println("\n==== deleteElement() Test ====");
            arr.deleteElement(2);
            for (int i = 0; i < arr.getTotalElements(); i++) {
                System.out.print(arr.getValue(i) + " ");
            }
            System.out.println();

            // 6. getValue()
            System.out.println("\n==== getValue() Test ====");
            System.out.println("Index 0: " + arr.getValue(0));
            System.out.println("Index 1: " + arr.getValue(1));

            // 7. getTotalElements()
            System.out.println("\n==== getTotalElements() Test ====");
            System.out.println("Total: " + arr.getTotalElements());

            // 8. getCapacity()
            System.out.println("\n==== getCapacity() Test ====");
            System.out.println("Capacity: " + arr.getCapacity());

            // 9. isFull()
            System.out.println("\n==== isFull() Test ====");
            System.out.println("Is Full? " + arr.isFull());

            // 10. isEmpty()
            System.out.println("\n==== isEmpty() Test ====");
            DynamicArray emptyArr = new DynamicArray();
            System.out.println("Is Empty? " + emptyArr.isEmpty());

            // 11. doubleArray() + halfArray()
            System.out.println("\n==== doubleArray() + halfArray() Test ====");
            DynamicArray resizeArr = new DynamicArray(2);
            resizeArr.appendAt(1);
            resizeArr.appendAt(2);
            resizeArr.appendAt(3); // double
            System.out.println("Capacity after double: " + resizeArr.getCapacity());

            resizeArr.deleteElement(2);
            resizeArr.deleteElement(1); // should shrink
            System.out.println("Capacity after half: " + resizeArr.getCapacity());

        } catch (DynamicExceptionHandler e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}

/**
 * Custom exception handling for DynamicArray.
 */
class DynamicExceptionHandler extends Exception {
    public static final String INVALID_INDEX = "INVALID INDEX";
    public static final String ARRAY_UNDERFLOW = "ARRAY IS EMPTY";

    public DynamicExceptionHandler(String sms){
        super(sms);
    }
}