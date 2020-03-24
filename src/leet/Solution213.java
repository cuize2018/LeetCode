package leet;

import java.util.Arrays;

public class Solution213 {
    public static void main(String[] args){
        int[] a = {2,3,2};
        System.out.println(rob(a));
    }

    /**
     * 数组是个环，也就是说偷第一家，最后一家就不能偷；偷最后一家，第一家就不能偷。
     *
     * 所以，我们问题分成求 nums[0:n - 2]或者 nums[1:n-1]
     */
    public static int rob(int[] nums) {
        if (nums.length == 0)return 0;
        if (nums.length == 1)return nums[0];
        int[] nums1 = Arrays.copyOfRange(nums, 0, nums.length-1);
        int[] nums2 = Arrays.copyOfRange(nums, 1, nums.length);

        return Math.max(rob2(nums1), rob2(nums2));

    }

    public static int rob_no_circle(int[] nums) {
        if (nums.length == 0)return 0;
        int n = nums.length;
        int[] dp = new int[n];

        for (int i = 0;i<n;i++){
            if (i == 0)dp[i] = nums[i];
            else if (i == 1)dp[i] = Math.max(nums[i-1], nums[i]);

            else dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
        }
        return dp[n-1];
    }


    public static int rob2(int[] nums) {
        int n = nums.length;
        if (n == 0)return 0;

        int[][] dp = new int[n+1][2];

        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i-1];
        }

        return Math.max(dp[n][0],dp[n][1]);
    }
}
