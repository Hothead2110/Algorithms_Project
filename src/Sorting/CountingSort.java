package Sorting;

public class CountingSort implements Sorts {
    public void sort(final Integer[] arr) {
        int n = arr.length;
        // Find the maximum value (k) in the array A
        int k = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > k) {
                k = arr[i];
            }
        }
        //Final sorted array
        Integer[] B = new Integer[n];
        //Array used for counting
        int[] C = new int[k + 1];

        //Initialize array C to 0 to make all counts 0
        for (int i = 0; i <= k; i++) {
            C[i] = 0;
        }

        //Count all the elements in A
        for (int j = 0; j < n; j++) {
            C[arr[j]]++;
        }

        //Complete the commutative counts
        for (int i = 1; i <= k; i++) {
            C[i] += C[i - 1];
        }

        //Build the output array from going from right to left
        for (int j = n - 1; j >= 0; j--) {
            B[C[arr[j]] - 1] = arr[j];
            C[arr[j]]--;
        }
        System.arraycopy(B, 0, arr, 0, n);
    }
}

