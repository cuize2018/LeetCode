package leet;

public class Solution122 {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit3(prices));
    }

    public static int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0)return 0;

        int[][][] dp = new int[n][n / 2 + 1][2];

        dp[0][0][0] = 0;
        dp[0][0][1] = Integer.MIN_VALUE;
        for (int j = 1;j<dp[0].length;j++){
            dp[0][j][0] = 0;
            dp[0][j][1] = -prices[0];
        }

        for (int i = 1;i<dp[0].length;i++){
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }

        return dp[n-1][n/2][0];
    }

    public static int maxProfit3(int[] prices) {
        int n = prices.length;
        if (n == 0)return 0;

        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            if (i == 0){
                dp[0][0] = 0;
                dp[0][1] = -prices[0];
            }
            else {
                int last_dp_i_0 = dp[i-1][0];
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], last_dp_i_0 - prices[i]);
            }
        }

        return dp[n-1][0];
    }

    /**
     * 如果 k 为正无穷，那么就可以认为 k 和 k - 1 是一样的。可以这样改写框架：
     *     dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *     dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *                 = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])
     *
     * 我们发现数组中的 k 已经不会改变了，也就是说不需要记录 k 这个状态了：
     *     dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     *     dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     *
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
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
}
