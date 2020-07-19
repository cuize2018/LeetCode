package leet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution518 {
    public static void main(String[] args) {
        int amount = 5;
        int[] a = {1, 2, 5};
        System.out.println(change2(amount, a));
    }

    //dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]]
    //即为在到第i个coin的目标值为j的时候，其方法数目为：
    //到第i-1个coin的目标值为j方法数 + 到第i个coin的目标值为j-coin[i]的方法数
    public static int change(int amount, int[] coins) {
        if (coins.length == 0 && amount == 0) return 1;
        if (coins.length == 0) return 0;

        int[][] dp = new int[coins.length][amount + 1];

        for (int j = 0; j < amount + 1; j++) {
            dp[0][j] = j % coins[0] == 0 ? 1 : 0;
        }

        for (int i = 0; i < coins.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - coins[i] >= 0) {
                    dp[i][j] += dp[i][j - coins[i]];
                }
            }
        }
        return dp[coins.length - 1][amount];
    }

    public static int change2(int amount, int[] coins) {
        if (amount == 0) return 1;
        if (coins.length == 0) return 0;

        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }
        return dp[amount];
    }

}
