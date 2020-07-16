package leet;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solution474 {
    public static void main(String[] args) {
//        String[]b = {"10", "0001", "111001", "1", "0"};
//        int m = 5;
//        int n = 3;

//        String[] b = {"111", "1000", "1000"};
//        int m = 9;
//        int n = 3;

//        String[] b = {"10","0001","111001","1","0"};
//        int m = 4;
//        int n = 3;

//        String[] b = {"10","0","1"};
//        int m = 1;
//        int n = 1;

//        String[] b = {"1100","100000","011111"};
//        int m = 6;
//        int n = 6;

        String[] b = {"011111", "001", "001"};
        int m = 4;
        int n = 5;
        System.out.println(findMaxForm2(b, m, n));
    }

    /**
     * 这道题和经典的背包问题很类似，不同的是在背包问题中，我们只有一种容量，而在这道题中，我们有 0 和 1 两种容量。
     * 每个物品（字符串）需要分别占用 0 和 1 的若干容量，并且所有物品的价值均为 1。因此我们可以使用二维的动态规划。
     * <p>
     * 我们用 dp(i, j) 表示使用 i 个 0 和 j 个 1，最多能拼出的字符串数目，那么状态转移方程为：
     * dp(i, j) = max(1 + dp(i - cost_zero[k], j - cost_one[k]))
     * if i >= cost_zero[k] and j >= cost_one[k]
     * <p>
     * 其中 k 表示第 k 个字符串，cost_zero[k] 和 cost_one[k] 表示该字符串中 0 和 1 的个数。
     * 我们依次枚举这些字符串，并根据状态转移方程更新所有的 dp(i, j)。
     * 注意由于每个字符串只能使用一次（即有限背包），因此在更新 dp(i, j) 时，i 和 j 都需要从大到小进行枚举。
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm3(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];
        int[] zeros = new int[strs.length];
        int[] ones = new int[strs.length];

        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '0') zeros[i]++;
                else ones[i]++;
            }
        }

        for (int i = 1; i <= len; i++) {
            // 注意：有一位偏移
            int zero = zeros[i - 1];
            int one = ones[i - 1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    // 先把上一行抄下来
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j - zero >= 0 && k - one >= 0) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zero][k - one] + 1);
                    }
                }
            }
        }
        return dp[len][m][n];
    }

    public static int findMaxForm4(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        int[] zeros = new int[strs.length];
        int[] ones = new int[strs.length];

        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '0') zeros[i]++;
                else ones[i]++;
            }
        }

        for (int k = 0; k < strs.length; k++) {
            for (int i = m; i >= zeros[k]; i--) {
                for (int j = n; j >= ones[k]; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros[k]][j - ones[k]] + 1);
                }
            }
        }
        return dp[m][n];
    }

}
