package leet;

import java.util.Arrays;

public class Solution322 {
    public static void main(String[] args) {
        int[] a = {186,419,83,408};
        int amount = 6249;
        System.out.println(coinChange(a,amount));
    }

    public static int coinChange(int[] coins, int amount) {
        if (coins.length == 0)return -1;

        int[] dp = new int[amount+1];
        dp[0] = 0;
        for (int i = 1;i<=amount;i++){
            int min = Integer.MAX_VALUE;
            for (int j = 0;j<coins.length;j++){
                if (i  >= coins[j]) {
                    int tmp = dp[i - coins[j]];
                    if (tmp != -1){
                        min = Math.min(min, tmp + 1);
                    }
                }
            }
            if (min == Integer.MAX_VALUE)min=-1;
            dp[i] = min;
        }
        return dp[amount];
    }
}
