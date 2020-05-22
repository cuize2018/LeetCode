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
        int size = nums.length;
        return quickSearch(nums, size - k, 0, nums.length - 1);
    }

    private static int quickSearch(int[] nums, int k, int low, int high) {
        if (low == high) return nums[low];
        int m = partition(nums, low, high);
        if (m == k) return nums[k];

        if (m > k) {
            return quickSearch(nums, k, low, m - 1);
        } else {
            return quickSearch(nums, k, m + 1, high);
        }
    }

    private static int partition(int[] nums, int low, int high) {
        int left = low;
        int right = high + 1;
        int v = nums[low];

        while (true) {
            while (nums[++left] < v) {
                if (left == high) break;
            }
            while (nums[--right] > v) {
                if (right == low) break;
            }
            if (left >= right) break;
            swap(nums, left, right);
        }
        swap(nums, low, right);
        return right;
    }

    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
