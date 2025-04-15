package Sorting;

public class SelectionSort implements Sorts {
    public void sort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;

                }
            }
            swap(arr, i, minIndex);
        }
    }
}
