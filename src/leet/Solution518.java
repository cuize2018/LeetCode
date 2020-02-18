package leet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution518 {
    public static void main(String[] args) {
        int amount = 5;
        int[] a = {1,2,5};
        System.out.println(change(amount, a));
    }


    public static int change(int amount, int[] coins) {
        int len = coins.length;
        if (len == 0){
            if (amount == 0) {
                return 1;
            }
            return 0;
        }

        int[][]dp = new int[coins.length][amount+1];

        dp[0][0] = 1;
        for (int j = coins[0];j<dp[0].length;j+=coins[0]){
            dp[0][j] = 1;
        }
        //dp[i][j] = dp[i - 1][j - 0 * coins[i]] +
        //           dp[i - 1][j - 1 * coins[i]] +
        //           dp[i - 1][j - 2 * coins[i]] +
        //           ... +
        //           dp[i - 1][j - k * coins[i]]
        //
        for (int i = 1;i < len;i++){
            for (int j = 0;j <= amount;j++){
                for (int k = 0;j-k*coins[i] >= 0;k++){
                    dp[i][j] += dp[i-1][j-k*coins[i]];
                }
            }
        }

        return dp[len-1][amount];
    }

    public static int change2(int amount, int[] coins) {
        int len = coins.length;
        if (len == 0){
            if (amount == 0) {
                return 1;
            }
            return 0;
        }

        int[][]dp = new int[coins.length][amount+1];

        dp[0][0] = 1;
        for (int j = coins[0];j<dp[0].length;j+=coins[0]){
            dp[0][j] = 1;
        }

        //dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]]
        //即为在前i个coin的目标值为j的时候，其方法数目为：
        //前i-1个coin的目标值为{j}方法数 + 前i个coin的目标值为{j-coin[i]}的方法数
        for (int i = 1;i < len;i++){
            for (int j = 0;j <= amount;j++){
                dp[i][j] = dp[i-1][j];
                if (j - coins[i] >= 0){
                    dp[i][j] += dp[i][j-coins[i]];
                }
            }
        }

        return dp[len-1][amount];
    }

    public static int change3(int amount, int[] coins) {
        int len = coins.length;
        if (len == 0){
            if (amount == 0) {
                return 1;
            }
            return 0;
        }

        int[]dp = new int[amount+1];
        dp[0] = 1;

        for (int i = coins[0]; i <= amount; i += coins[0]) {
            dp[i] = 1;
        }

        //dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]]
        //即为在前i个coin的目标值为j的时候，其方法数目为：
        //前i-1个coin的目标值为{j}方法数 + 前i个coin的目标值为{j-coin[i]}的方法数

        //计算前i个coin的时候
        for (int i = 1;i < len;i++){
          //计算前i个coin的时候目标值为j的方法数， 初始化为dp[i][j] = dp[i-1][j]
            for (int j = coins[i];j <= amount;j++){
                //当j - coin[i] >= 0 的时候， dp[i][j] += dp[i][j-coins[i]] -->此时使用coins[i]
                if (j - coins[i] >= 0){
                    dp[j] += dp[j-coins[i]];
                }
            }
        }

        return dp[amount];
    }

}
