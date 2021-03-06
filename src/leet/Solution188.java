package leet;

public class Solution188 {
    public static void main(String[] args) {

        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int k = 2;
        System.out.println(maxProfit(k, prices));
    }

    public static int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        if (k > n/2){
            return maxProfit_kInf(prices);
        }

        int[][][] dp = new int[n][k+1][2];

        for (int i = 0; i < n; i++) {
            for (int j = k; j >= 1; j--) {
                if (i == 0) {
                    dp[0][j][0] = 0;
                    dp[0][j][1] = -prices[i];
                } else {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                }
            }
        }
        return dp[n - 1][k][0];
    }

    public static int maxProfit_kInf(int[] prices) {
        int n = prices.length;
        if (n == 0)return 0;

        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int last_dp_i_0 = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, last_dp_i_0 - prices[i]);
        }

        return dp_i_0;
    }

    public static int maxProfit2(int k, int[] prices) {
        int n = prices.length;
        if (n == 0)return 0;
        if (k > n/2)return kInf(prices);

        int[][][] dp = new int[n][k+1][2];
        for (int i = 0; i < n; i++) {
            for (int t = k; t >= 1; t--) {
                if (i == 0){
                    dp[i][t][0] = 0;
                    dp[i][t][1] = -prices[0];
                }
                else {
                    dp[i][t][0] = Math.max(dp[i - 1][t][0], dp[i - 1][t][1] + prices[i]);
                    dp[i][t][1] = Math.max(dp[i - 1][t][1], dp[i - 1][t - 1][0] - prices[i]);
                }
            }
        }
        return dp[n-1][k][0];
    }

    private static int kInf(int[] prices) {
        int n = prices.length;
        if (n == 0)return 0;

        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i == 0){
                dp[i][0] = 0;
                dp[i][1] = -prices[0];
            }
            else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }

        }
        return dp[n-1][0];
    }
}
