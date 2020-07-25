package leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution410 {

    public static void main(String[] args) {

    }

    /**
     * f[i][j] 表示将数组的前 i 个数分割为 j 段所能得到的最大连续子数组和的最小值
     * 在进行状态转移时，我们可以考虑第 j 段的具体范围，即我们可以枚举 k，
     * 其中前 k 个数被分割为 j-1 段，而第 k+1 到第 i 个数为第j段。
     * 此时，这 j 段子数组中和的最大值，就等于 f[k][j-1] 与 sub(k+1,i) 中的较大值，其中 sub(i,j) 表示数组nums中下标落在区间[i,j]内的数的和。
     * @param nums
     * @param m
     * @return
     */
    public static int splitArray(int[] nums, int m) {
        if (nums.length == 1) return nums[0];
        int[][] dp = new int[nums.length + 1][m + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }

        int[] sum = new int[nums.length+1];
        for (int i = 1; i <= nums.length; i++) {
            sum[i] += sum[i-1] + nums[i-1];
        }

        dp[0][0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sum[i] - sum[k]));
                }
            }
        }
        return dp[nums.length][m];
    }
}
