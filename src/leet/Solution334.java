package leet;

import java.util.Arrays;

public class Solution334 {
    public static void main(String[] args) {
        int[] a = {2,5,3,4,5};
        int[] b = {0,4,2,1,0,-1,-3};
        System.out.println(increasingTriplet(a));
    }

    public static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3)return false;
        boolean hasIncreasing;
        for (int i = 0;i<nums.length;i++){
            hasIncreasing = helper(nums[i], Arrays.copyOfRange(nums,i+1,nums.length), 2);
            if (hasIncreasing)return true;
        }
        return false;
    }

    public static boolean helper(int last_val, int[] nums, int k) {
        boolean flag;
        for (int i = 0;i<nums.length;i++){
            if (nums[i] > last_val){
                k--;
                if (k == 0)return true;
                flag = helper(nums[i], Arrays.copyOfRange(nums,i+1,nums.length), k);
                if (flag)return true;
                else k++;
            }
        }
        return false;
    }

    /**
     * 先说下这道题的思路： 首先找到一个相对小的值，然后找到比这个小一点的值大的值(中间值)，然后看能够在最后找到比中间值大的值。
     *
     * 我来说下为什么这种思路能保证覆盖所有的情况。
     *
     * 首先，如果只有一个最小值，然后找不到中间值，那么这个数组必然不包含递增的三个数（因为连递增的两个数都找不到）。
     *
     * 然后假设我们找到了两个递增的值，那么如果下一个值小于最小值，我们就应该将最小值的指针定位到这个值上。
     * 我们尽可能的使用最小值，防止后面出现了更小的一对递增值，而即使不出现，也不妨碍我们找到解（因为最终是看能否找到大于中间值的值）。
     * 如果下一个值大于最小值，且小于中间值，则我们使用该值作为中间值(因为如果最小的中间值都得不到解，那么就是false，
     * 这样也保证了覆盖所有的情况)。
     *
     * 最后，如果找到了大于中间值的值，则为true.
     * @param nums
     * @return
     */
    public static boolean increasingTriplet2(int[] nums) {
        int i = 0;
        int small = Integer.MAX_VALUE;
        int big = Integer.MAX_VALUE;

        while (i < nums.length){
            if (nums[i] < small){
                small = nums[i];
            }
            else if (nums[i] > small && nums[i] <= big){
                big = nums[i];
            }
            else if (nums[i] > big){
                return true;
            }
            i++;
        }
        return false;
    }


}
