package leet;

import java.util.Arrays;

public class Solution27 {
    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        int res = removeElement2(nums, val);
        System.out.println(Arrays.toString(nums));
        System.out.println(res);
    }

    public static int removeElement(int[] nums, int val) {
        Arrays.sort(nums);
        int left = 0;
        int right = 0;
        int i = 0;
        int res = nums.length;
        while (i < nums.length){
            if (nums[i] == val){
                left = i;
                while (i < nums.length && nums[i] == val){
                    i++;
                    res--;
                }

                right = Math.min(i, nums.length);
                System.arraycopy(nums, right, nums, left, nums.length-right);
                break;
            }
            else i++;
        }
        return res;
    }

    /**
     * 其中 i 是慢指针，j 是快指针。
     * 当 nums[j] 与给定的值相等时，递增 j 以跳过该元素。
     * 只要 nums[j] != val，我们就复制 nums[j]到 nums[i] 并同时递增两个索引。
     * 重复这一过程，直到 j 到达数组的末尾，该数组的新长度为 i。
     *

     * @param nums
     * @param val
     * @return
     */
    public static int removeElement2(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val){
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}
