package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution315 {
    int[] temp;
    int[] indexes;
    int[] counts;

    public static void main(String[] args) {
        int[] nums = {-1, -1};
        System.out.println(countSmaller2(nums));
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length == 0) return res;

        temp = new int[nums.length];
        indexes = new int[nums.length];
        counts = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            indexes[i] = i;
        }

        mergedAndCountSampler(nums, 0, nums.length - 1);
        for (int count : counts) {
            res.add(count);
        }
        return res;
    }

    /**
     * 针对数组 nums 指定的区间 [l, r] 进行归并排序，在排序的过程中完成统计任务
     *
     * @param nums
     * @param left
     * @param right
     */
    private void mergedAndCountSampler(int[] nums, int left, int right) {
        if (left == right) return;

        int middle = (left + right) >>> 1;
        mergedAndCountSampler(nums, left, middle);
        mergedAndCountSampler(nums, middle + 1, right);

        // 归并排序的优化，同样适用于该问题
        // 如果索引数组有序，就没有必要再继续计算了
        if (nums[indexes[middle]] > nums[indexes[middle + 1]]) {
            mergeOfTwoSortedArrAndCountSmaller(nums, left, middle, right);
        }
    }

    /**
     * [l, mid] 是排好序的
     * [mid+1, r] 是排好序的
     *
     * @param nums
     * @param left
     * @param middle
     * @param right
     */
    private void mergeOfTwoSortedArrAndCountSmaller(int[] nums, int left, int middle, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = indexes[i];
        }
        int i = left;
        int j = middle + 1;
        // 左边出列的时候，计数
        for (int k = left; k <= right; k++) {
            if (i > middle) {
                indexes[k] = temp[j];
                j++;
            } else if (j > right) {
                indexes[k] = temp[i];
                i++;
                // 此时 j 用完了，[7,8,9 | 1,2,3], right = 5, middle = 2
                // 之前的数就和后面的区间长度构成逆序
                counts[indexes[k]] += right - middle;
            } else if (nums[temp[i]] <= nums[temp[j]]) {
                indexes[k] = temp[i];
                i++;
                // 此时 [4,5, 6   | 1,2,3 10 12 13], middle = 2, j = 6
                //           mid          j
                counts[indexes[k]] += j - middle - 1;
            } else {
                // nums[indexes[i]] > nums[indexes[j]] 构成逆序
                indexes[k] = temp[j];
                j++;
            }
        }

    }


    public static List<Integer> countSmaller2(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = quickSort(Arrays.copyOfRange(nums, i, nums.length));
            res.add(num);
        }
        return res;
    }

    private static int quickSort(int[] nums) {
        if (nums.length == 1) return 0;
        int low = 0;
        int high = nums.length - 1;

        int i = low;
        int j = high + 1;
        int v = nums[0];
        int count = 0;
        while (true) {
            while (nums[++i] <= v) {
                if (nums[i] == v) count++;
                if (i == high) break;
            }
            while (nums[--j] >= v) {
                if (j == low) break;
            }
            if (i >= j) break;
            swap(nums, i, j);
        }

        swap(nums, low, j);
        return Math.max(j - low - count, 0);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public List<Integer> countSmaller3(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();

        indexes = new int[nums.length];
        temp = new int[nums.length];
        counts = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            indexes[i] = i;
        }

        mergeWay(nums, 0, nums.length-1);
        for (int count : counts) {
            res.add(count);
        }
        return res;
    }

    private void mergeWay(int[] nums, int low, int high) {
        if (low < high){
            int mid = (low+high)>>>1;
            mergeWay(nums, low, mid);
            mergeWay(nums, mid+1, high);

            if (nums[indexes[mid]] > nums[indexes[mid+1]]){
                mergeCount(nums, low, mid, high);
            }
        }
    }

    private void mergeCount(int[] nums, int low, int mid, int high) {
        for (int i = low; i <= high; i++) {
            temp[i] = indexes[i];
        }

        int i = low;
        int j = mid+1;

        for (int k = low; k <= high; k++) {
            if (i > mid){
                indexes[k] = temp[j];
                j++;
            }
            else if (j > high){
                indexes[k] = temp[i];
                i++;
                counts[indexes[k]] += high - mid;
            }
            else if (nums[temp[i]] <= nums[temp[j]]){
                indexes[k] = temp[i];
                i++;
                counts[indexes[k]] += j-mid-1;
            }
            else {
                indexes[k] = temp[j];
                j++;
            }
        }
    }
}
