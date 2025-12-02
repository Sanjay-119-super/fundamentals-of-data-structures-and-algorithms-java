package myhustledsa;

import java.util.Scanner;

/**
 * Utility class to check whether an array
 * can be divided into two subarrays with equal sum.
 */
class Demo{

    /**
     * Checks if the given array can be split into
     * two subarrays such that:
     *
     *     sum(left subarray) == sum(right subarray)
     *
     * @param arr input integer array
     * @return 1 if equal-sum division exists, else 0
     */
    public int divideArrayInSubarray(int[] arr){

        int n = arr.length;
        int totalSum = 0;

        // 1. Calculate total sum of the array
        for(int i = 0; i < n; i++){
            totalSum += arr[i];
        }

        int prefix = 0;

        // 2. Traverse and calculate prefix sum
        //    Compare prefix sum with remaining right sum
        for(int i = 0; i < n; i++){

            prefix += arr[i];               // prefix sum including current element
            int rightSum = totalSum - prefix; // right sum = total - prefix

            if (rightSum == prefix){
                return 1;   // equal-sum partition found
            }
        }

        return 0; // no valid partition found
    }
}

public class PrefixConcept {

    /**
     * Main method: takes user input and checks
     * if array can be divided into two equal-sum parts.
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the size of array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter the elements in array: ");
        for (int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        // Calling the method
        Demo ob = new Demo();
        System.out.println(ob.divideArrayInSubarray(arr));
    }
}
