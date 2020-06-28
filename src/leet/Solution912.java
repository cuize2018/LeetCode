package leet;

import java.util.*;

public class Solution912 {
    public static void main(String[] args) {
        int[] nums = {5, 1, 1, 2, 0, 0};
        System.out.println(Arrays.toString(sortArray(nums)));
    }


    public static int[] sortArray(int[] nums) {
        if (nums.length <= 1) return nums;

        //quickSort(nums, 0, nums.length - 1);
        //return nums;

//        int[] res = insertSort(nums);
//        return res;

        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length-1, temp);
        return nums;
    }

    private static int[] linkedInsertSort(int[] nums) {
        LinkedList<Integer> out = new LinkedList<>();
        for (int num : nums) {
            insert(out, num);
        }
        return out.stream().mapToInt(a -> a).toArray();
    }

    private static void insert(LinkedList<Integer> out, int num) {
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

    /**
     * 归并排序
     * @param nums
     * @param low
     * @param high
     * @param temp
     */
    private static void mergeSort(int[] nums, int low, int high, int[] temp) {
        if (low < high){
            int middle = (low+high)>>>1;
            mergeSort(nums, low, middle, temp);
            mergeSort(nums, middle+1, high, temp);
            merge(nums, low, middle, high, temp);
        }
    }

    private static void merge(int[] nums, int low, int middle, int high, int[] temp) {
        int i = low;
        int j = middle+1;
        int k = 0;
        while (i <= middle && j <= high){
            if (nums[i] < nums[j]){
                temp[k++] = nums[i++];
            }
            else {
                temp[k++] = nums[j++];
            }
        }

        while (i <= middle){
            temp[k++] = nums[i++];
        }

        while (j <= high){
            temp[k++] = nums[j++];
        }
        System.arraycopy(temp,0, nums, low, high-low+1);
    }

    private static void insertSort(int[] nums){
        if (nums.length <=1)return;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j-1]){
                    int temp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    private static void selectSort(int[] nums){
        if (nums.length <= 1)return;
        for (int i = 0; i < nums.length-1; i++) {
            int min = i;
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] < nums[min]){
                    min = j;
                }
            }
            if (min != i){
                int temp = nums[i];
                nums[i] = nums[min];
                nums[min] = temp;
            }
        }
    }
}
