package leet;

public class Solution1262 {
    public static void main(String[] args) {
        int[] nums = {3, 6, 5, 1, 8};
        System.out.println(maxSumDivThree(nums));
    }

    /**
     * dp[i][0]表示nums[0...i]模三余零的最大和
     * dp[i][1]表示nums[0...i]模三余一的最大和
     * dp[i][2]表示nums[0...i]模三余二的最大和
     * <p>
     * 对于任意一种状态，下一步我们都有两种选择，一是选择当前元素，二是不选择当前元素
     * dp[i][*] = max{dp[i-1][*],dp[i-1][*] + nums[i]}  (* 取值为 0,1,2)
     *
     * @param nums
     * @return
     */
    public static int maxSumDivThree(int[] nums) {
        if (nums.length == 0) return 0;
        int[][] dp = new int[nums.length + 1][3];

        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        dp[0][2] = Integer.MIN_VALUE;

        for (int i = 1; i <= nums.length; i++) {
            if (nums[i - 1] % 3 == 0) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][0] + nums[i - 1]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][1] + nums[i - 1]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][2] + nums[i - 1]);
            } else if (nums[i - 1] % 3 == 1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] + nums[i - 1]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + nums[i - 1]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + nums[i - 1]);
            } else if (nums[i - 1] % 3 == 2) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + nums[i - 1]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] + nums[i - 1]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] + nums[i - 1]);
            }
        }
        return dp[nums.length][0];
    }
}
