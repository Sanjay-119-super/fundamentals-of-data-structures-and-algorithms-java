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
    /**
     * Prints all possible subarrays of the given array.
     *
     * A subarray is a continuous portion of the array.
     * For each starting index, this method prints all
     * subarrays that begin at that index.
     *
     * Example:
     * For arr = {1, 2, 3}
     * Output:
     * [1] [1 2] [1 2 3]
     * [2] [2 3]
     * [3]
     *
     * @param arr the input integer array
     */
    public void printAllSubarray(int[] arr){

        // Iterate over all starting indices of subarrays
        for(int start = 0; start < arr.length; start++){

            // Iterate over all possible ending indices from 'start'
            for(int end = start; end < arr.length; end++){

                // Print the subarray elements from start to end
                System.out.print("[ ");
                for (int j = start; j <= end; j++){
                    System.out.print(arr[j] + " ");
                }
                System.out.print("] ");
            }

            // Move to next line after printing all subarrays for this 'start'
            System.out.println();
        }
    }

    public int largestSumContiguousSubarray(int[] arr){
        int maxi = Integer.MIN_VALUE;

        for(int i =0; i<arr.length; i++){
            for(int j=i;j<arr.length; j++){
                int sum =0;
                for(int k=i; k<=j; k++){
                    sum+=arr[k];
                }
                if (sum>maxi)
                        maxi=sum;
            }
        }
        return maxi;
    }

    // TC=n2
    public int largestSumContiguousSubarrayOptimize(int[] arr){
        int maxi = Integer.MIN_VALUE;
        for(int start =0; start<arr.length; start++){
            int sum=0;
            for(int end=start; end<arr.length; end++){
                sum+=arr[end];
            }
            if (sum>maxi)
                    maxi=sum;
        }
      return maxi;
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
       // ob.printAllSubarray(arr);
       // int i = ob.largestSumContiguousSubarray(arr);
        int i = ob.largestSumContiguousSubarrayOptimize(arr);
        System.out.println("Max Subarray Sum: " + i);
    }
}
