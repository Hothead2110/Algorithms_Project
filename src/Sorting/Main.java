package Sorting;

import java.util.Comparator;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        // initialise sorts to be tested
        Sorts[] sorts = {new BubbleSort(), new SelectionSort(), new InsertionSort(), new CountingSort(), new ARUCountingSort(), new MergeSort(), new QuickSort()};

        // Test each algorithm
        for (Sorts sort : sorts) {
            System.out.println(sort.getClass().getSimpleName() + ": ");
            // test with a generated array - uses method reference
            testArray(Main::generateIntArray, sort::sort);
        }
    }

    static Random rand = new Random(); // random number generator
    static int N = 10000; // array size

    /**
     * Tests a sorting algorithm
     * Generates random array of length N
     * Finds time taken to sort
     * Checks if array is actually sorted
     *
     * @param generator Function creates an Integer array of size N
     * @param sort      Consumer that sorts an Integer array
     */
    public static void testArray(Function<Integer,Integer[]> generator, Consumer<Integer[]> sort) {
        Integer[] input = generator.apply(N); // generates random array
        long start = System.currentTimeMillis(); // start time of sort
        sort.accept(input); // performs sort
        long end = System.currentTimeMillis(); // end time of sort
        // check if array is actually sorted and display results
        System.out.println(sortedArray(input, Comparator.naturalOrder()) + " in " + (end - start) + "ms\n");
    }

    /**
     * Generates array of size N of random positive integers
     * @param n Size of array
     * @return  Integer array containing random values in given range
     */
    public static Integer[] generateIntArray(int n) {
        Integer[] arr = new Integer[n];
        for(int i=0;i<n;i++) {
            arr[i] = Math.abs(rand.nextInt(10000)); // generates random positive numbers for arrays - can specify bound here
        }
        return arr;
    }

    /**
     * Checks if an array is sorted
     * @param input      Array to check
     * @param comparator Comparator defines the order (naturalOrder i.e. ascending in this case)
     * @return  "Sorted" if sorted or "Not Sorted" if unsorted
     */
    public static String sortedArray(final Integer[] input, final Comparator<Integer> comparator) {
        for(int i=1;i<input.length;i++)
            if (comparator.compare(input[i-1], input[i]) > 0)
                return "Not Sorted";
        return "Sorted";
    }
}