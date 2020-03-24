package leet.interview;

public class Solution17_16 {
    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        System.out.println(massage(nums));
    }

    public static int massage(int[] nums) {
        int n = nums.length;
        if (n == 0)return 0;

        int[][] dp = new int[n+1][2];

        for (int i = 1; i <= n; i++) {
            //不接单的情况有两种：1.前一单不接，这一单也不接 2.前一单接完这一单休息
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            //接单的情况只能是在上一单没接的情况下接单
            dp[i][1] = dp[i-1][0] + nums[i-1];
        }

        return Math.max(dp[n][1], dp[n][0]);
    }
}
