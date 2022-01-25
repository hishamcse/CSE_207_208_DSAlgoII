/*
 * Iterative Implementation of QuickSort for sorting integers in ascending order
 */

import java.util.Stack;

public class CustomQuickSort {

    Stack<Integer> stack = new Stack<>();

    public void sort(int[] arr) {
        stack.push(0);
        stack.push(arr.length - 1);
        while (!stack.isEmpty()) {
            int end_id = stack.pop();
            int start_id = stack.pop();
            if (end_id <= start_id) {
                continue;
            }

            int partition_id = partition_pivot(arr, start_id, end_id);
            stack.push(partition_id + 1);
            stack.push(end_id);
            stack.push(start_id);
            stack.push(partition_id - 1);
        }
    }

    private int partition_pivot(int[] arr, int start_id, int end_id) {
        int i = start_id;
        for (int j = start_id; j < end_id; ++j) {
            if (arr[j] <= arr[end_id]) {
                swap(arr, j, i++);
            }
        }
        swap(arr, i, end_id);
        return i;
    }

    private void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}