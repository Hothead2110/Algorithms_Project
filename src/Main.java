 class Main {
    public static void main(String[] args) {
        int[] counting = {4, 2, 2, 8, 3, 3, 1};
        int[] bubble = {3, 6, 1, 0, 4, 10, 3, 6};
        int[] selection = {6, 2, 0, 7, 8, 3, 3, 2, 1};
        int[] insertion = {5, 7, 2, 0, 1, 232, 456, 3, 2};
        int[] merge = {1, 4, 2, 5, 7, 4, 8, 34, 573, -3};
        int[] quick = {3, 1, 5, 7634, 45, -55, 3, -2, 7};

        int[] sorted = CountingSort.countingSort(counting);
        BubbleSort.bubbleSort(bubble);
        SelectionSort.selectionSort(selection);
        InsertionSort.insertionSort(insertion);
        MergeSort.mergeSort(merge);
        QuickSort.quickSort(quick);

        System.out.print("Sorted through CountingSort : ");
        PrintArray.printArray(sorted);
        System.out.print("Sorted through BubbleSort : ");
        PrintArray.printArray(bubble);
        System.out.print("Sorted through SelectionSort : ");
        PrintArray.printArray(selection);
        System.out.print("Sorted through InsertionSort : ");
        PrintArray.printArray(insertion);
        System.out.print("Sorted through MergeSort : ");
        PrintArray.printArray(merge);
        System.out.print("Sorted through QuickSort : ");
        PrintArray.printArray(quick);
    }
}