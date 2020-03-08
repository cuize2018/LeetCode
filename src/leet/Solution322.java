package leet;

import java.util.Arrays;

public class Solution322 {
    public static void main(String[] args) {
        int[] a = {186, 419, 83, 408};
        int amount = 6249;
        System.out.println(coinChange(a, amount));
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
        int[] dp = new int[amount+1];
        dp[0] = 0;

        for (int amt = 1;amt <= amount;amt++){
            int min = Integer.MAX_VALUE;
            for (int i = 0;i<coins.length;i++){
                if (amt > coins[i]) {
                    int tmp =  dp[amt - coins[i]];
                    if (tmp != -1){
                        min = Math.min(min, tmp + 1);
                    }
                }
            }
            if (min == Integer.MAX_VALUE)dp[amt] = -1;
            else dp[amt] = min;
        }

        return dp[amount];
    }

}
