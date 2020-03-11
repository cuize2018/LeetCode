package leet;

public class Solution123 {
    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(maxProfit2(prices));
    }

    public static int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int[][][] dp = new int[n][2 + 1][2];

        for (int i = 0; i < n; i++) {
            for (int j = 2; j >= 1; j--) {
                if (i == 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                } else {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                }
            }
        }
        return dp[n - 1][2][0];
    }

    public static int maxProfit2(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int dp_i_2_0 = 0;
        int dp_i_2_1 = Integer.MIN_VALUE;
        int dp_i_1_0 = 0;
        int dp_i_1_1 = Integer.MIN_VALUE;

        for (int price : prices) {
            dp_i_2_0 = Math.max(dp_i_2_0, dp_i_2_1 + price);
            dp_i_2_1 = Math.max(dp_i_2_1, dp_i_1_0 - price);

            dp_i_1_0 = Math.max(dp_i_1_0, dp_i_1_1 + price);
            //dp_i_0_0 === 0
            dp_i_1_1 = Math.max(dp_i_1_1, -price);
        }
        return dp_i_2_0;
    }
}
