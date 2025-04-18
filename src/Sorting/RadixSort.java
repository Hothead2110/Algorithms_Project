package Sorting;

public class RadixSort implements Sorts {
    public void sort(final Integer[] A) {
        if (A == null || A.length == 0) return;

        // Step 1: Find the maximum number to determine number of digits
        int max = A[0];
        for (int num : A) {
            if (num > max) max = num;
        }

        // Step 2: Perform counting sort for every digit (LSD to MSD)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            int[] count = new int[10]; // 0-9 for decimal digits
            Integer[] output = new Integer[A.length];

            // Count occurrences of digits
            for (int i = 0; i < A.length; i++) {
                int digit = (A[i] / exp) % 10;
                count[digit]++;
            }

            // Convert count[] to actual positions
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            // Build output array (backward for stability)
            for (int i = A.length - 1; i >= 0; i--) {
                int digit = (A[i] / exp) % 10;
                output[count[digit] - 1] = A[i];
                count[digit]--;
            }

            // Copy output back to original array
            System.arraycopy(output, 0, A, 0, A.length);
        }
    }
}
