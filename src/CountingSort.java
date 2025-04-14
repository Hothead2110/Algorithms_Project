public class CountingSort {

    public static int[] countingSort(int[] A, int k) {
        int n = A.length;
        int[] B = new int[n];       // Output array
        int[] C = new int[k + 1];   // Count array (size k+1)

        // Step 1: Initialize count array C to 0
        for (int i = 0; i <= k; i++) {
            C[i] = 0;
        }

        // Step 2: Count each element in A
        for (int j = 0; j < n; j++) {
            C[A[j]]++;
        }

        // Step 3: Compute cumulative counts
        for (int i = 1; i <= k; i++) {
            C[i] += C[i - 1];
        }

        // Step 4: Build output array B (stable sort, so go right to left)
        for (int j = n - 1; j >= 0; j--) {
            B[C[A[j]] - 1] = A[j];
            C[A[j]]--;
        }

        return B;
    }
}

