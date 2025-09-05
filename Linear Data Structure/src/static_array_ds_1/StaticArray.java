package static_array_ds_1;

/**
 * Custom Array class to perform basic array operations manually.
 * Useful for learning how arrays work internally in Java.
 * Includes critical points and edge case handling.
 */
class Array {

    /**
     * Tracks the index of the last inserted element.
     * Starts at -1 when array is empty.
     * 🚨 Critical: Always update carefully to avoid overflow.
     */
    private int lastIndex;

    /**
     * Actual array where data is stored.
     * 📌 Fixed size, created during object initialization.
     */
    private int[] ptr;

    /**
     * Constructor to initialize array with given size.
     * @param size Total capacity of the array.
     * 📌 Important: Array size is fixed and cannot grow.
     */
    public Array(int size){
        ptr = new int[size];
        lastIndex = -1;
    }

    /**
     * Adds data to the end of the array.
     * @param data Value to be added.
     * @throws RuntimeException if array is full.
     * 🚨 Critical: Uses ++lastIndex, so must check isFull() first.
     */
    public void appendDataToEnd(int data){
        if (isFull())
            throw new RuntimeException("Array is full");
        ptr[++lastIndex] = data;
    }

    /**
     * Inserts data at a specific position.
     * Shifts elements to the right to make space.
     * @param newData Value to insert.
     * @param position Index where data should be inserted.
     * @throws RuntimeException if array is full or index is invalid.
     * 🚨 EDSE: Inserting at lastIndex+1 is valid, beyond that is not.
     * 📌 Important: Shifting is expensive for large arrays.
     */
    public void insertDataToStart(int newData, int position){
        if (isFull())
            throw new RuntimeException("Array is full");
        if (position < 0 || position > lastIndex + 1)
            throw new RuntimeException("invalid index");

        for(int i = lastIndex; i >= position; i--){
            ptr[i + 1] = ptr[i];
        }

        ptr[position] = newData;
        lastIndex++;
    }

    /**
     * Edits value at a specific index.
     * @param newData New value to set.
     * @param index Index to update.
     * @throws RuntimeException if index is invalid.
     * 📌 Important: Only updates existing data, no shifting.
     */
    public void editData(int newData ,int index){
        if(index < 0 || index > lastIndex)
            throw new RuntimeException("invalid index");
        ptr[index] = newData;
    }

    /**
     * Deletes value at a specific index.
     * Shifts elements to the left to fill the gap.
     * @param index Index to delete.
     * @throws RuntimeException if index is invalid.
     * 🚨 Critical Fix: Must shift all elements, not just one.
     */
    public void deleteData(int index){
        if(index < 0 || index > lastIndex)
            throw new RuntimeException("invalid index");

        for(int i = index; i < lastIndex; i++){
            ptr[i] = ptr[i + 1];
        }

        ptr[lastIndex--] = 0;
    }

    /**
     * Returns total number of elements in the array.
     * @return Number of elements.
     * 📌 Important: Starts from 0, so add +1 to lastIndex.
     */
    public int getTotalElement(){
        return lastIndex + 1;
    }

    /**
     * Checks if array is empty.
     * @return true if empty, false otherwise.
     * 📌 Helpful before performing delete or read operations.
     */
    public boolean isEmpty(){
        return lastIndex == -1;
    }

    /**
     * Checks if array is full.
     * @return true if full, false otherwise.
     * 🚨 Critical: Prevents overflow before insert or append.
     */
    public boolean isFull(){
        return lastIndex == ptr.length - 1;
    }

    /**
     * Returns value at a given index.
     * @param index Index to read.
     * @return Value at index.
     * @throws RuntimeException if index is invalid.
     * 📌 Important: Always validate index before access.
     */
    public int getValueGivenIndex(int index){
        if (index < 0 || index > lastIndex)
            throw new RuntimeException("invalid index");
        return ptr[index];
    }

    /**
     * Returns total capacity of the array.
     * @return Maximum number of elements that can be stored.
     * 📌 Fixed at creation time, cannot be changed later.
     */
    public int capacity(){
        return ptr.length;
    }
}

/**
 * 🚀 ArrayTest class to test all methods of the custom Array class.
 * Each test includes expected behavior and edge case handling.
 */
public class StaticArray {
    public static void main(String[] args) {
        // 🧱 Create array of size 5
        Array arr = new Array(5);

        /**
         * 🔍 Test: Check if array is empty initially
         * Expected: true
         */
        System.out.println("Is Empty: " + arr.isEmpty()); // true

        /**
         * ➕ Test: Append data to end
         * Expected: Array contains [10, 20, 30]
         */
        arr.appendDataToEnd(10);
        arr.appendDataToEnd(20);
        arr.appendDataToEnd(30);
        System.out.println("Total Elements after append: " + arr.getTotalElement()); // 3

        /**
         * 🔎 Test: Get value at index
         * Expected: 20 at index 1
         */
        System.out.println("Value at index 1: " + arr.getValueGivenIndex(1)); // 20

        /**
         * 🧩 Test: Insert data at specific position
         * Expected: 99 inserted at index 1, shifting others
         */
        arr.insertDataToStart(99, 1);
        System.out.println("Value at index 1 after insert: " + arr.getValueGivenIndex(1)); // 99
        System.out.println("Value at index 2 after shift: " + arr.getValueGivenIndex(2)); // 20

        /**
         * ✏️ Test: Edit data at index
         * Expected: 77 replaces value at index 2
         */
        arr.editData(77, 2);
        System.out.println("Value at index 2 after edit: " + arr.getValueGivenIndex(2)); // 77

        /**
         * 🗑️ Test: Delete data at index
         * Expected: 99 removed, others shifted left
         */
        arr.deleteData(1);
        System.out.println("Value at index 1 after delete: " + arr.getValueGivenIndex(1)); // 77
        System.out.println("Total Elements after delete: " + arr.getTotalElement()); // 3

        /**
         * 📦 Test: Check if array is full
         * Expected: true after filling all slots
         */
        arr.appendDataToEnd(40);
        arr.appendDataToEnd(50);
        System.out.println("Is Full: " + arr.isFull()); // true

        /**
         * 📊 Test: Check array capacity
         * Expected: 5
         */
        System.out.println("Array Capacity: " + arr.capacity()); // 5

        /**
         * 🚫 Edge Case: Append when array is full
         * Expected: RuntimeException with message "Array is full"
         */
        try {
            arr.appendDataToEnd(60);
        } catch (RuntimeException e) {
            System.out.println("Append when full: " + e.getMessage());
        }

        /**
         * 🚧 Edge Case: Insert at invalid position
         * Expected: RuntimeException with message "invalid index"
         */
        try {
            arr.insertDataToStart(100, 6);
        } catch (RuntimeException e) {
            System.out.println("Insert at invalid index: " + e.getMessage());
        }

        /**
         * 🛠️ Edge Case: Edit at invalid index
         * Expected: RuntimeException with message "invalid index"
         */
        try {
            arr.editData(111, 10);
        } catch (RuntimeException e) {
            System.out.println("Edit at invalid index: " + e.getMessage());
        }

        /**
         * 🧨 Edge Case: Delete at invalid index
         * Expected: RuntimeException with message "invalid index"
         */
        try {
            arr.deleteData(-1);
        } catch (RuntimeException e) {
            System.out.println("Delete at invalid index: " + e.getMessage());
        }

        /**
         * 🕳️ Edge Case: Get value at invalid index
         * Expected: RuntimeException with message "invalid index"
         */
        try {
            System.out.println(arr.getValueGivenIndex(10));
        } catch (RuntimeException e) {
            System.out.println("Get value at invalid index: " + e.getMessage());
        }
    }
}
