package leet;

import java.util.Arrays;
import java.util.Collections;

public class Solution312 {
    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
        System.out.println(maxCoins(nums));
    }

    /**
     * dp[i][j] 表示戳破 [i+1...j-1] 号气球的最大收益.
     * 假设 k 号气球（i+1 <= k <= j-1）是 [i+1...j-1] 中最后一个被戳破的，则
     * dp[i][j] = max {for k = range(i+1, j -1) nums[i] * nums[k] * nums[j] + dp[i][k] + dp[k][j]}
     *
     * @param nums
     * @return
     */
    public static int maxCoins(int[] nums) {
        if (nums.length == 0) return 0;

        int[] vals = new int[nums.length + 2];
        System.arraycopy(nums, 0, vals, 1, nums.length);
        vals[0] = 1;
        vals[vals.length - 1] = 1;

        int[][] dp = new int[vals.length][vals.length];
        // 这里不能按行打表
        // 只能按长度打表, dp[i][j], j-i >= 2, 至少三个元素
        for (int len = 2; len < vals.length; len++) {
            for (int i = 0; i < vals.length - len; i++) {
                int j = i + len;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + vals[i] * vals[j] * vals[k] + dp[k][j]);
                }
            }
        }
        return dp[0][vals.length - 1];
    }
}
