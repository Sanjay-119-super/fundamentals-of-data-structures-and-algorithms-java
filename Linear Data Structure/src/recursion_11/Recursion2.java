package recursion_11;

/**
 * This class contains recursive methods
 * to calculate sum of natural numbers,
 * sum of even/odd numbers, factorial, and
 * sum of digits of a number.
 */
public class Recursion2 {

    /**
     * Calculate sum of first n natural numbers.
     * Example: n=5 → 5+4+3+2+1 = 15
     * @param n limit number
     * @return sum of numbers from 1 to n
     */
    public int calculateN(int n){
        int sum = 0;
        if (n == 1){                 // Base case: if n=1 → return 1
            return 1;
        }
        sum = n + calculateN(n-1);   // Recursive call with smaller value
        return sum;
    }

    /**
     * Calculate sum of all even numbers up to n.
     * Example: n=5 → 2+4 = 6
     * @param n limit number
     * @return sum of even numbers from 1 to n
     */
    public int evenSum(int n){
        if (n < 1){                 // Base case: stop when n < 1
            return 0;
        }
        if (n % 2 == 0)             // If n is even → add it
            return n + evenSum(n-1);
        else                        // If odd → skip it
            return evenSum(n-1);
    }

    /**
     * Calculate sum of all odd numbers up to n.
     * Example: n=5 → 1+3+5 = 9
     * @param n limit number
     * @return sum of odd numbers from 1 to n
     */
    public int oddSum(int n){
        if (n < 0){                  // Base case: when n < 0 → stop
            return 0;
        }
        if (n % 2 != 0)              // If n is odd → add it
            return n + oddSum(n-1);
        else                         // If even → skip
            return oddSum(n-1);
    }

    /**
     * Calculate factorial of a number n.
     * Example: fact(5) = 5*4*3*2*1 = 120
     * @param n number
     * @return factorial of n
     */
    public int fact(int n){
        if (n == 1 || n == 0){      // Base case: fact(0)=1, fact(1)=1
            return 1;
        }
        return n * fact(n-1);       // Recursive call
    }

    /**
     * Calculate sum of digits of a number.
     * Example: sumOfDigit(123) = 1+2+3 = 6
     * @param n number
     * @return sum of digits
     */
    public int sumOfDigit(int n){
        if (n == 0){                // Base case: stop when number is 0
            return 0;
        }
        return (n % 10) + sumOfDigit(n / 10); // Last digit + recursive call
    }
}

/**
 * Test class to run and check all recursive methods.
 */
class RecursionTest2{
    public static void main(String[] args) {
        Recursion2 recursion2 = new Recursion2();

        System.out.println(recursion2.calculateN(5)); // Sum of 1..5

        System.out.println("------------");
        System.out.println(recursion2.evenSum(5));    // Sum of even numbers ≤ 5

        System.out.println("------------");
        System.out.println(recursion2.oddSum(5));     // Sum of odd numbers ≤ 5

        System.out.println("------------");
        System.out.println(recursion2.fact(5));       // Factorial of 5

        System.out.println("------------");
        System.out.println(recursion2.sumOfDigit(123)); // Sum of digits
    }
}
