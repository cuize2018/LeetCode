package leet.interview;

public class Solution42 {
    public static void main(String[] args) {

    }

    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int ans = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static int maxSubArray2(int[] nums) {
        if (nums.length == 0) return 0;

        int dp = nums[0];
        int ans = dp;

        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(dp + nums[i], nums[i]);
            ans = Math.max(ans, dp);
        }

        return ans;
    }
}
