package leet;

import java.util.Arrays;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class Solution33 {
    public static void main(String[] args){
        int[] nums = {3,4,5,6,1,2};
        int target = 6;
        System.out.println(search(nums, target));
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
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        if (nums.length == 1){
            return nums[0]==target?0:-1;
        }

        while (low <= high){
            int middle = (high+low)>>>1;
            if (nums[middle] == target)return middle;

            if (nums[low] <= nums[middle]) {
                if (nums[low] <= target && target < nums[middle]) {//去前半部分
                    high = middle - 1;
                } else {//去后半部分
                    low = middle + 1;
                }
            }
            else {
                if (nums[middle] < target && target <= nums[high]) {//去前半部分
                    low = middle + 1;
                } else {//去后半部分
                   high = middle-1;
                }

            }
        }
        return -1;
    }
}
