package Sorting;

public class BubbleSort implements Sorts {
    public void sort(final Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                   swap(arr, j, j+1);
                }
            }
        }
    }
}
