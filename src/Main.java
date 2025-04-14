public class Main {
    public static void main(String[] args) {
        int[] input = {4, 2, 2, 8, 3, 3, 1};
        int maxVal = 8;

        int[] sorted = CountingSort.countingSort(input, maxVal);

        System.out.print("Sorted: ");
        for (int num : sorted) {
            System.out.print(num + " ");
        }
    }
}