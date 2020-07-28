package leet;

import java.util.Arrays;

public class HeapSort2 {
    public static void main(String[] args) {
        int[] arr = {9, 9, 5, 8, 1, 3, 8, 6, 1, 3, 0, 4, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] nums) {
        int len = nums.length;
        buildMaxHeap(nums, len);

        for (int i = len - 1; i > 0; i--) {
            swap(nums, 0, i);
            len--;
            adjustHeap(nums, 0, len);
        }
    }

    private static void adjustHeap(int[] arr, int i, int len) {
        int left  = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, i, largest);
            adjustHeap(arr, largest, len);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    private static void buildMaxHeap(int[] arr, int len) {
        for (int i = (len / 2 - 1); i >= 0; i--) {
            adjustHeap(arr, i, len);
        }
    }
}
