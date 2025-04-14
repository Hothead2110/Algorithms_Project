public class CountingSort {

    public static int[] countingSort(int[] A, int k) {
        int n = A.length;
        //Final sorted array
        int[] B = new int[n];
        //Array used for counting
        int[] C = new int[k + 1];

        //Initialize array C to 0 to make all counts 0
        for (int i = 0; i <= k; i++) {
            C[i] = 0;
        }

        //Count all the elements in A
        for (int j = 0; j < n; j++) {
            C[A[j]]++;
        }

        //Complete the commutative counts
        for (int i = 1; i <= k; i++) {
            C[i] += C[i - 1];
        }

        //Build the output array from going from right to left
        for (int j = n - 1; j >= 0; j--) {
            B[C[A[j]] - 1] = A[j];
            C[A[j]]--;
        }

        return B;
    }
}

