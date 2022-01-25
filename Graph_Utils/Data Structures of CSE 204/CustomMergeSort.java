/*
 * Implementation of MergeSort for sorting integers in ascending order
 */

public class CustomMergeSort {

    public void sort(int[] arr) {
        int arr_len = arr.length;
        int[] copy_Arr = new int[arr_len];
        mergeAndSort(arr, copy_Arr, 0, arr_len - 1);
    }

    // merge two already sorted halves of the array
    private void merge(int[] arr, int[] copy_arr, int start_id, int mid_id, int end_id) {
        // copy items between start and end ids from original array to copied array
        if (end_id + 1 - start_id >= 0) {
            System.arraycopy(arr, start_id, copy_arr, start_id, end_id + 1 - start_id);
        }

        int firstIter = start_id;
        int secondIter = mid_id + 1;

        for (int i = start_id; i <= end_id; i++) {
            if (firstIter > mid_id) {
                arr[i] = copy_arr[secondIter++];
            } else if (secondIter > end_id) {
                arr[i] = copy_arr[firstIter++];
            } else if (copy_arr[firstIter] <= copy_arr[secondIter]) {
                arr[i] = copy_arr[firstIter++];
            } else {
                arr[i] = copy_arr[secondIter++];
            }
        }
    }

    private void mergeAndSort(int[] arr, int[] copy_arr, int start_id, int end_id) {
        if (end_id <= start_id) return;
        int mid_id = (start_id + end_id) / 2;
        mergeAndSort(arr, copy_arr, start_id, mid_id);                 // sorting the first half of the array
        mergeAndSort(arr, copy_arr, mid_id + 1, end_id);        // sorting the second half of the array
        merge(arr, copy_arr, start_id, mid_id, end_id);                // merge
    }
}