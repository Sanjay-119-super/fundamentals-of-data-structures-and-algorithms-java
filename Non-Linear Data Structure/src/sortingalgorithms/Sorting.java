package sortingalgorithms;

/**
 * Sorting Interface
 * ⇝ Har sorting algorithm is interface ko implement karega.
 * ⇝ Ek hi method hai: sort(), jo array ko sorted form me convert karega.
 */
public interface Sorting {
    /**
     * @param arr Array of integers jisko sort karna hai
     */
    void sort(int[] arr);
}

/**
 * ✳️ Bubble Sort Algorithm
 * ⇝ Idea: Bada element har pass me "bubble" hoke end me chala jata hai.
 * ⇝ TC = O(N^2), SC = O(1).
 */
class BubbleSortAlgo implements Sorting {
    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) { // Total passes
            for (int j = 0; j < arr.length - i - 1; j++) { // Comparison
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
        }
    }
}

/**
 * ✳️ Optimized Bubble Sort
 * ⇝ Agar ek pass me koi swap na ho → array already sorted.
 * ⇝ Best case = O(N), Worst/Average = O(N^2).
 */
class ModifiedBubbleSort implements Sorting {
    @Override
    public void sort(int[] arr) {
        boolean swapped;
        for (int i = 0; i < arr.length; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                    swapped = true;
                }
            }
            if (!swapped) break; // Early exit
        }
    }
}

/**
 * ✳️ Insertion Sort
 * ⇝ Idea: Jese hum apne cards arrange karte hain.
 * ⇝ Har naye element ko uske sahi jagah pe insert karte hain.
 * ⇝ TC: Best = O(N), Worst/Average = O(N^2), SC = O(1).
 */
class InsertionSort implements Sorting {
    @Override
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) { // 0th element already sorted
            int key = arr[i]; // Current element
            int j = i - 1;

            // Sorted part me peeche jaate jao jab tak sahi jagah na mil jaye
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key; // Correct position par insert
        }
    }
}

/**
 * ✳️ Quick Sort
 * ⇝ Divide & Conquer technique
 * ⇝ Ek pivot choose karte hain aur elements ko pivot ke left/right me divide karte hain.
 * ⇝ TC: Best/Average = O(NlogN), Worst = O(N^2).
 * ⇝ SC: O(logN) recursion stack ke liye.
 */
class QuickSort implements Sorting {
    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitioning(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);  // Left part
            quickSort(arr, pivotIndex + 1, high); // Right part
        }
    }

    private int partitioning(int[] arr, int low, int high) {
        int pivot = arr[high]; // Last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }

        // Pivot ko correct jagah par daalo
        int t = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = t;

        return i + 1; // Pivot ka final position
    }
}

/**
 * ✳️ Merge Sort
 * ⇝ Divide & Conquer technique.
 * ⇝ Array ko do parts me tod ke recursively sort karo,
 *    fir merge step me dono sorted arrays ko combine karo.
 * ⇝ TC: Always O(NlogN).
 * ⇝ SC: O(N) (temporary arrays ke liye).
 */
class MergeSort implements Sorting {
    @Override
    public void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2; // Middle index
            mergeSort(arr, l, m);    // Left half
            mergeSort(arr, m + 1, r); // Right half
            merge(arr, l, m, r);     // Merge both halves
        }
    }

    private void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[l + i];
        for (int j = 0; j < n2; j++) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
}

/**
 * ✳️ Test Class
 * ⇝ Har algorithm ko alag-alag test karega.
 */
class TestSorting {
    public static void main(String[] args) {
        int[] arr1 = {5, 2, 9, 1, 5, 6};
        int[] arr2 = arr1.clone();
        int[] arr3 = arr1.clone();
        int[] arr4 = arr1.clone();
        int[] arr5 = arr1.clone();

        Sorting bubble = new BubbleSortAlgo();
        Sorting modBubble = new ModifiedBubbleSort();
        Sorting insertion = new InsertionSort();
        Sorting quick = new QuickSort();
        Sorting merge = new MergeSort();

        bubble.sort(arr1);
        modBubble.sort(arr2);
        insertion.sort(arr3);
        quick.sort(arr4);
        merge.sort(arr5);

        System.out.println("Bubble Sort: " + java.util.Arrays.toString(arr1));
        System.out.println("Modified Bubble Sort: " + java.util.Arrays.toString(arr2));
        System.out.println("Insertion Sort: " + java.util.Arrays.toString(arr3));
        System.out.println("Quick Sort: " + java.util.Arrays.toString(arr4));
        System.out.println("Merge Sort: " + java.util.Arrays.toString(arr5));
    }
}
