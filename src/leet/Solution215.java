package leet;

import java.util.PriorityQueue;

public class Solution215 {
    public static void main(String[] args) {
        int[] a = {3, 2, 1, 5, 6, 4};
        int k = 3;
        System.out.println(findKthLargest2(a, k));
    }

    public static int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }

    public static int findKthLargest2(int[] nums, int k) {
        return quickSort(nums, 0, nums.length - 1, nums.length - k);
    }

    private static int quickSort(int[] nums, int low, int high, int k) {
        if (low == high) return nums[low];
        int m = paration(nums, low, high);

        if (k == m) {
            return nums[m];
        }

        if (m > k) {
            return quickSort(nums, low, m - 1, k);
        } else {
            return quickSort(nums, m + 1, high, k);
        }
    }

    private static int paration(int[] nums, int low, int high) {
        int i = low;
        int j = high + 1;
        int v = nums[low];

        while (true) {
            while (nums[++i] < v) {
                if (i == high) break;
            }
            while (nums[--j] > v) {
                if (j == low) break;
            }
            if (i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, low, j);
        return j;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }


}
