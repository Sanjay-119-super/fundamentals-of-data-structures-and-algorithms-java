package recursion_11;

/**
 * This class demonstrates different recursive methods
 * to print natural numbers, even numbers, and odd numbers
 * in normal and reverse order.
 *
 * Recursion works on the principle of:
 * → Breaking the problem into smaller sub-problems
 * → Each recursive call reduces the size of input (n-1)
 * → Base condition stops the recursion
 */
public class Recursion1 {

    /**
     * Print first n natural numbers (1 to n).
     *
     * Internal working:
     * → Method keeps calling itself with (n-1) until n becomes 0.
     * → Once base case is reached, recursion starts returning back.
     * → While returning, numbers are printed in increasing order.
     *
     * Example for n=5:
     * Calls: (5→4→3→2→1)
     * Prints: 1 2 3 4 5
     */
    public void nNaturalNumber(int n){
        if (n >= 1) {
            nNaturalNumber(n-1);   // recursive call (smaller problem)
            System.out.print(" " + n); // print while stack unwinds
        }
    }

    /**
     * Print first n natural numbers in reverse order (n to 1).
     *
     * Internal working:
     * → Print the current number first.
     * → Then call recursion with (n-1).
     * → This ensures numbers are printed in decreasing order.
     *
     * Example for n=5:
     * Prints: 5 4 3 2 1
     */
    public void nNaturalNumberReverse(int n){
        if (n >= 1) {
            System.out.println(n + " "); // print while going deeper
            nNaturalNumberReverse(n-1); // recursive call
        }
    }

    /**
     * Print all even numbers from 1 to n.
     *
     * Internal working:
     * → Call recursion with (n-1) first (reach down to 1).
     * → While returning, check if number is even, then print.
     *
     * Example for n=5:
     * Calls: (5→4→3→2→1)
     * Prints: 2 4
     */
    public void nNaturalNumberEven(int n){
        if (n < 1) {
            return; // base case
        }
        nNaturalNumberEven(n-1); // recursion down
        if (n % 2 == 0)
            System.out.println(n + " ");
    }

    /**
     * Print all even numbers from n down to 1 (reverse).
     *
     * Internal working:
     * → First check and print if number is even.
     * → Then call recursion with (n-1).
     * → This ensures reverse order printing.
     *
     * Example for n=5:
     * Prints: 4 2
     */
    public void nNaturalNumberEvenReverse(int n){
        if (n < 1) {
            return; // base case
        }
        if (n % 2 == 0)
            System.out.println(n + " "); // print first
        nNaturalNumberEvenReverse(n-1);  // recursion down
    }

    /**
     * Print all odd numbers from 1 to n.
     *
     * Internal working:
     * → Go deep with recursion until 0.
     * → While returning, check if number is odd.
     * → Print if odd.
     *
     * Example for n=5:
     * Calls: (5→4→3→2→1→0)
     * Prints: 1 3 5
     */
    public void nNaturalNumberOdd(int n){
        if (n < 0) {
            return; // base case
        }
        nNaturalNumberOdd(n-1); // recursion down
        if (n % 2 != 0)
            System.out.println(n + " ");
    }

    /**
     * Print all odd numbers from n down to 1 (reverse).
     *
     * Internal working:
     * → If number is odd, print it first.
     * → Then call recursion with (n-1).
     * → This ensures decreasing order printing.
     *
     * Example for n=5:
     * Prints: 5 3 1
     */
    public void nNaturalNumberOddReverse(int n){
        if (n < 0) {
            return; // base case
        }
        if (n % 2 != 0)
            System.out.println(n + " ");
        nNaturalNumberOddReverse(n-1); // recursion down
    }
}

/**
 * Test class to run all recursive methods
 * and observe outputs.
 */
class TestRecursion1 {
    public static void main(String[] args) {
        Recursion1 recursion1 = new Recursion1();

        System.out.println("Natural Numbers (1 to n):");
        recursion1.nNaturalNumber(5);

        System.out.println("\n\nEven Numbers (1 to n):");
        recursion1.nNaturalNumberEven(5);

        System.out.println("\n-------------------");
        System.out.println("Even Numbers (n to 1):");
        recursion1.nNaturalNumberEvenReverse(5);

        System.out.println("\n-------------------");
        System.out.println("Odd Numbers (1 to n):");
        recursion1.nNaturalNumberOdd(5);

        System.out.println("\n-------------------");
        System.out.println("Odd Numbers (n to 1):");
        recursion1.nNaturalNumberOddReverse(5);
    }
}
