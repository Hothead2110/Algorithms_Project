public class ARUCountingSort {
    public static void aruCountingSort(int[] A) {
        int n = A.length;
        int k = A[0];
        for (int i = 1; i < n; i++) {
            if (A[i] > k) {
                k = A[i];
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
            Q[A[j] / m]++;
            R[A[j] % m]++;
        }

        // Add up all the iterations to prepare for sorting
        for (int i = 1; i <= m; i++) {
            Q[i] += Q[i - 1];
            R[i] += R[i - 1];
        }

        // Step 4: Sort by remainder into B
        for (int j = n - 1; j >= 0; j--) {
            int d = A[j] % m;
            R[d]--;
            B[R[d]] = A[j];
        }

        // Step 5: Sort by quotient into A to receive the fully sorted array
        for (int i = n - 1; i >= 0; i--) {
            int d = B[i] / m;
            Q[d]--;
            A[Q[d]] = B[i];
        }
    }
}
