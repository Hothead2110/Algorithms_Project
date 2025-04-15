package Sorting;

public class InsertionSort implements Sorts {
    public void sort(final Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Integer key = arr[i];
            int j = i - 1;

            // Shift elements to the right to make space for key
            while (j >= 0 && (arr[j] > key)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
