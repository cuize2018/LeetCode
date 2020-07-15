package leet;

import java.util.Arrays;

public class Solution322 {
    public static void main(String[] args) {
        int[] a = {186, 419, 83, 408};
        int amount = 0;
        System.out.println(coinChange2(a, amount));
    }

    public static int coinChange(int[] coins, int amount) {
        if (coins.length == 0) return -1;

        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i >= coin) {
                    int tmp = dp[i - coin];
                    if (tmp != -1) {
                        min = Math.min(min, tmp + 1);
                    }
                }
            }
            if (min == Integer.MAX_VALUE) min = -1;
            dp[i] = min;
        }
        return dp[amount];
    }

    public static int coinChange2(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (coins.length == 0) return 0;

        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != -1) {
                    min = Math.min(min, dp[i - coin] + 1);
                }
            }
            dp[i] = min == Integer.MAX_VALUE ? -1 : min;
        }
        return dp[amount];
    }

}
