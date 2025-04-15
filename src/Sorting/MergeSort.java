package Sorting;

import java.util.Arrays;

public class MergeSort implements Sorts {
    public void sort(final Integer[] arr) {
        if (arr.length > 1) {
            Integer[] left = Arrays.copyOfRange(arr, 0, arr.length / 2);
            Integer[] right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);

            sort(left);
            sort(right);

            int i = 0, l = 0, r = 0;
            while (l < left.length && r < right.length) {
                arr[i++] = left[l] <= right[r] ? left[l++] : right[r++];
            }
            while (l < left.length) {
                arr[i++] = left[l++];
            }
            while (r < right.length) {
                arr[i++] = right[r++];
            }
        }
    }
}