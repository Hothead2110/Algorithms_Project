public class QuickSort {

    public static void quickSort(int[] arr) {
        int[] stack = new int[arr.length];
        int top = -1;

        // push initial low and high
        stack[++top] = 0;
        stack[++top] = arr.length - 1;

        while (top >= 0) {
            int high = stack[top--];
            int low = stack[top--];

            int pivot = arr[high];
            int i = low;

            for (int j = low; j < high; j++) {
                if (arr[j] <= pivot) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                }
            }

            int temp = arr[i];
            arr[i] = arr[high];
            arr[high] = temp;

            if (i - 1 > low) {
                stack[++top] = low;
                stack[++top] = i - 1;
            }
            if (i + 1 < high) {
                stack[++top] = i + 1;
                stack[++top] = high;
            }
        }
    }
}