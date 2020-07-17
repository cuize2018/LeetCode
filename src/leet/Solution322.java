package leet;

import java.util.Arrays;

public class Solution322 {
    public static void main(String[] args) {
        int[] a = {1,3,7,11,13};
        int amount = 20;
        int[] nums = {1,1,1,1,1};
        System.out.println(coinChange4(a, amount, nums));
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

    public static int coinChange3(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount ; i++) {
                if (dp[i-coin] != -1) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE?-1:dp[amount];
    }

    //带数目限制，多重背包
    public static int coinChange4(int[] coins, int amount, int[] nums) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;

        for (int i = 0; i < coins.length; i++) {
            for (int j = amount; j >= coins[i]; j--) {
                for (int k = 0; k <= Math.min(nums[i], j / coins[i]); k++) {
                    if (dp[j - k * coins[i]] != -1) {
                        dp[j] = Math.min(dp[j], dp[j - k * coins[i]] + k);
                    }
                }
            }
        }
        return dp[amount] == amount+1?-1:dp[amount];
    }
}
