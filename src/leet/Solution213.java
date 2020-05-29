package leet;

import java.util.Arrays;

public class Solution213 {
    public static void main(String[] args) {
        int[] a = {2, 3, 2};
        System.out.println(rob(a));
    }

    /**
     * 数组是个环，也就是说偷第一家，最后一家就不能偷；偷最后一家，第一家就不能偷。
     * <p>
     * 所以，我们问题分成求 nums[0:n - 2]或者 nums[1:n-1]
     */
    public static int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int robFirst = robOrigin(Arrays.copyOfRange(nums, 0, n - 1));
        int notRobFirst = robOrigin(Arrays.copyOfRange(nums, 1, n));

        return Math.max(robFirst, notRobFirst);
    }

    private static int robOrigin(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }




}
