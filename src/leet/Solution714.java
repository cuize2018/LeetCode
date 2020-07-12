package leet;

public class Solution714 {
    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println(maxProfit2(prices, fee));
    }

    /**
     * 带手续费
     *
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        if (n == 0) return 0;

        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[0];
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][0];
    }

    public static int maxProfit2(int[] prices, int fee) {
        int n = prices.length;
        if (n == 0) return 0;

        long dp_i_0 = 0;
        //避免负值向下溢出
        long dp_i_1 = Integer.MIN_VALUE;

        for (int price : prices) {
            long temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price - fee);
            dp_i_1 = Math.max(dp_i_1, temp - price);
        }
        return (int) dp_i_0;
    }

    public static int maxProfit3(int[] prices, int fee) {
        int n = prices.length;
        if (n == 0) return 0;

        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0] - fee;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }
        return dp[n - 1][0];
    }
}
