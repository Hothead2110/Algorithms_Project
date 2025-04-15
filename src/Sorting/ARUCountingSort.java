package Sorting;

public class ARUCountingSort implements Sorts {
    public void sort(final Integer[] arr) {
        int n = arr.length;
        int k = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > k) {
                k = arr[i];
            }
        }

        int m = (int) Math.ceil(Math.sqrt(k));

        int[] B = new int[n];
        int[] Q = new int[m + 1];
        int[] R = new int[m + 1];

        // Step 1: Initialize Q and R to 0
        for (int i = 0; i <= m; i++) {
            Q[i] = 0;
        }

        for (int i = 0; i <= m; i++) {
            R[i] = 0;
        }

        // Step 2: Count quotients and remainders
        for (int j = 0; j < n; j++) {
            Q[arr[j] / m]++;
            R[arr[j] % m]++;
        }

        // Add up all the iterations to prepare for sorting
        for (int i = 1; i <= m; i++) {
            Q[i] += Q[i - 1];
            R[i] += R[i - 1];
        }

        // Step 4: Sort by remainder into B
        for (int j = n - 1; j >= 0; j--) {
            int d = arr[j] % m;
            R[d]--;
            B[R[d]] = arr[j];
        }

        // Step 5: Sort by quotient into A to receive the fully sorted array
        for (int i = n - 1; i >= 0; i--) {
            int d = B[i] / m;
            Q[d]--;
            arr[Q[d]] = B[i];
        }
    }
}
