package leet;

import java.util.Arrays;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class Solution33 {
    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 6, 1, 2};
        int target = 11;
        System.out.println(search2(nums, target));
    }

    /**
     * 由于题目说数字了无重复，举个例子
     * 1 2 3 4 5 6 7 可以大致分为两类,
     * 第一类 2 3 4 5 6 7 1这种，也就是nums[start] <= nums[mid]。此例子中就是2 <= 5
     * 这种情况下，前半部分有序。因此如果 nums[start] <=target<nums[mid]。则在前半部分找，
     * 否则去后半部分找。
     * 第二类 6 7 1 2 3 4 5这种，也就是nums[start] > nums[mid]。此例子中就是6 > 2
     * 这种情况下，后半部分有序。因此如果 nums[mid] <target<=nums[end]。则在后半部分找，
     * 否则去前半部分找。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        while (low <= high) {
            int middle = (high + low) >>> 1;
            if (nums[middle] == target) return middle;

            if (nums[low] <= nums[middle]) {
                if (nums[low] <= target && target < nums[middle]) {//去前半部分
                    high = middle - 1;
                } else {//去后半部分
                    low = middle + 1;
                }
            } else {
                if (nums[middle] < target && target <= nums[high]) {//去前半部分
                    low = middle + 1;
                } else {//去后半部分
                    high = middle - 1;
                }

            }
        }
        return -1;
    }

    public static int search2(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int idx = 0;
        boolean foundReverse = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                foundReverse = true;
                idx = i - 1;
                break;
            }
        }

        if (!foundReverse) {
            int res = Arrays.binarySearch(nums, target);
            if (res < 0) return -1;
            return res;
        }
        int res;
        if (target > nums[nums.length - 1]) {
            res = Arrays.binarySearch(Arrays.copyOfRange(nums, 0, idx + 1), target);
        } else {
            if (target == nums[nums.length - 1]) return nums.length - 1;
            res = Arrays.binarySearch(Arrays.copyOfRange(nums, idx + 1, nums.length), target);
            if (res >= 0) {
                res += idx + 1;
            }
        }
        if (res < 0) return -1;
        return res;
    }

}
