package Sorting;

// interface for sorts
public interface Sorts {
    void sort(final Integer[] A); // method signature for sorts

    // swap method for reuse
    default void swap(final Integer[] arr, int a, int b) {
        Integer t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }
}
