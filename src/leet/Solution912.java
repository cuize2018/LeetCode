package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class Solution912 {
    public static void main(String[] args) {
        int[] nums = {5, 1, 1, 2, 0, 0};
        System.out.println(Arrays.toString(sortArray(nums)));
    }


    public static int[] sortArray(int[] nums) {
        if (nums.length <= 1) return nums;
        quickSort(nums, 0, nums.length - 1);
        return nums;

//        LinkedList<Integer> out = new LinkedList<>();
//        for (int num : nums) {
//            insertSort(out, num);
//        }
//        return out.stream().mapToInt(a ->a.intValue()).toArray();
    }


    private static void insertSort(LinkedList<Integer> out, int num) {
        if (out.isEmpty() || out.getLast() <= num){
            out.add(num);
            return;
        }

        for (ListIterator<Integer> iterator = out.listIterator(); iterator.hasNext();){
            if (num < iterator.next()){
                iterator.previous();
                iterator.add(num);
                break;
            }
        }
    }

    /**
     * 快速排序
     *
     * @param nums
     * @param low
     * @param high
     */
    private static void quickSort(int[] nums, int low, int high) {
        if (low >= high) return;

        int m = partition(nums, low, high);
        quickSort(nums, low, m - 1);
        quickSort(nums, m + 1, high);

    }

    private static int partition(int[] nums, int low, int high) {
        int i = low;
        int j = high + 1;
        int v = nums[low];

        while (true) {
            while (nums[++i] < v) {
                if (i == high) {
                    break;
                }
            }
            while (nums[--j] > v) {
                if (j == low) {
                    break;
                }
            }
            if (i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, low, j);
        return j;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static int[] sortArray2(int[] nums) {
        if (nums.length <= 1) return nums;
        quickSort2(nums, 0, nums.length - 1);
        return nums;
    }

    private static void quickSort2(int[] nums, int low, int high) {
        if (low >= high) return;
        int m = partition2(nums, low, high);
        quickSort2(nums, low, m - 1);
        quickSort2(nums, m + 1, high);
    }

    private static int partition2(int[] nums, int low, int high) {
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
}
