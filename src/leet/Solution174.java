package leet;

import java.util.*;

public class Solution174 {
    public static void main(String[] args) {

    }

    /**
     * 我们令 dp[i][j] 表示从 (i, j) 这个格子走到右下角所需要的最小生命值，
     * 同样我们选择两个去向中的较小值min{dp[i+1][j],dp[i][j+1]} 。
     * 然后考虑了格子 (i, j) 之后， dp[i][j]就更新为：
     * dp[i][j] = max{1, min{dp[i+1][j], dp[i][j+1]} − dungeon[i][j]}
     * <p>
     * 边界条件为，当 i=n-1 或者 j=m-1时，dp[i][j] 转移需要用到的 dp[i][j+1]和 dp[i+1][j]中有无效值，因此代码实现中给无效值赋值为极大值。
     * 特别地，dp[n-1][m-1]转移需要用到的 dp[n-1][m] 和 dp[n][m-1] 均为无效值，因此我们给这两个值赋值为 1。
     *
     * @param dungeon
     * @return
     */
    public static int calculateMinimumHP(int[][] dungeon) {
        int rows = dungeon.length;
        if (rows == 0) return 0;
        int cols = dungeon[0].length;

        int[][] dp = new int[rows + 1][cols + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[rows][cols - 1] = 1;
        dp[rows - 1][cols] = 1;

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                int min = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(min - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }

    public static int calculateMinimumHP2(int[][] dungeon) {
        int rows = dungeon.length;
        if (rows == 0) return 0;
        int cols = dungeon[0].length;

        int[][] dp = new int[rows][cols];
        dp[rows-1][cols-1] = Math.max(0, -dungeon[rows-1][cols-1]);

        for (int i = rows-2; i>= 0 ; i--) {
            int needMin = dp[i+1][cols-1] - dungeon[i][cols-1];
            dp[i][cols-1] = Math.max(0, needMin);
        }

        for (int j = cols-2; j >=0 ; j--) {
            int needMin = dp[rows-1][j+1] - dungeon[rows-1][j];
            dp[rows-1][j] = Math.max(needMin, 0);
        }

        for (int i = rows-2; i >= 0 ; i--) {
            for (int j = cols-2; j >=0 ; j--) {
                int needMin = Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j];
                dp[i][j] = Math.max(0, needMin);
            }
        }
        return dp[0][0] + 1;
    }
}
