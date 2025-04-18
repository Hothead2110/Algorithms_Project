package Sorting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        // initialise sorts to be tested
        Sorts[] sorts = {new CountingSort(), new ARUCountingSort(), new MergeSort(), new QuickSort(), new RadixSort()};

        // values of n and k to be tested
        int[][] testVals = {
                {1000,    1000, 10000, 100000, 1000000, 10000000},
                {10000,   10000, 100000, 1000000, 10000000, 1000000000},
                {100000,  100000, 1000000, 10000000, 100000000, 1000000000},
                {1000000, 100000, 1000000, 10000000, 100000000, 1000000000}
        };

        // opens file at given path for writing
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("analysis\\sort_results.csv")))) {
            // header in csv
            writer.println("Algorithm,N,K,Sorted?,AvgTime(s)");

            // for each algorithm
            for (Sorts sort : sorts) {
                System.out.println("\nTesting " + sort.getClass().getSimpleName() + ":\n"); // print which algorithm is being tested
                // test each N value
                for (int[] arr : testVals) {
                    int n = arr[0];
                    // test each k value
                    for (int i = 1; i < arr.length; i++) {
                        int k = arr[i];
                        System.out.printf("N = %d, k = %d -> ", n, k); // display current N and k
                        testArray(Main::generateIntArray, sort::sort, n, k, sort.getClass().getSimpleName(), writer); // test the algorithm
                    }
                }
            }
            System.out.println("\nResults written to sort_results.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Random rand = new Random(); // random number generator
    static final int NUM_TESTS = 5; // defines number of test to run

    /**
     * Tests a sorting algorithm
     * Generates random array of length N
     * Finds time taken to sort
     * Checks if array is actually sorted
     *
     * @param generator Function creates an Integer array of size N
     * @param sort      Consumer that sorts an Integer array
     */
    public static void testArray(BiFunction<Integer, Integer, Integer[]> generator, Consumer<Integer[]> sort, int n, int k, String algorithmName, PrintWriter writer) {
        long totalTime = 0;
        Integer[] lastArray = null;

        // loop for number of tests
        for (int i = 0; i < NUM_TESTS; i++) {
            Integer[] input = generator.apply(n, k); // generate integer array based on n and k
            long start = System.nanoTime(); // get start time
            sort.accept(input); // sort the array
            long end = System.nanoTime(); // get end time

            totalTime += (end - start); // find time taken
            lastArray = input;
        }

        String sortedStatus = sortedArray(lastArray, Comparator.naturalOrder()); // check if sorted
        double avgTimeSec = (totalTime / 1_000_000_000.0) / NUM_TESTS; // find average time

        System.out.printf("%s in avg %f s (%d runs)%n", sortedStatus, avgTimeSec, NUM_TESTS); // display time taken

        // write line to csv
        writer.printf("%s,%d,%d,%s,%f%n", algorithmName, n, k, sortedStatus, avgTimeSec);
        writer.flush(); // make sure line is written
    }


    /**
     * Generates array of size N of random positive integers
     * @param n Size of array
     * @return  Integer array containing random values in given range
     */
    public static Integer[] generateIntArray(int n, int k) {
        Integer[] arr = new Integer[n];
        for(int i=0;i<n;i++) {
            arr[i] = Math.abs(rand.nextInt(k+1)); // generates random positive numbers for arrays - can specify bound here
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