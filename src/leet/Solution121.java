package leet;

public class Solution121 {
    public static void main(String[] args) {

    }

    public static int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            int buy = prices[i];
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > buy) {
                    max = Math.max(max, prices[j] - buy);
                }
            }
        }
        return max;
    }

    /**
     * base case：
     * dp[-1][k][0] = dp[i][0][0] = 0
     * dp[-1][k][1] = dp[i][0][1] = -infinity
     * <p>
     * 状态转移方程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *
     * @return
     */
    public static int maxProfit2(int[] prices) {
        int[][] dp = new int[prices.length][2];
        // 解释：
        //   dp[0][0]
        // = max(dp[-1][0], dp[-1][1] + prices[0])
        // = max(0, -infinity + prices[i]) = 0
        dp[0][0] = 0;

        //解释：
        //   dp[0][1]
        // = max(dp[-1][1], dp[-1][0] - prices[0])
        // = max(-infinity, 0 - prices[i])
        // = -prices[i]
        dp[0][1] = -prices[0];

        //dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
        //dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i]) = max(dp[i-1][1][1], -prices[i])
        //解释：k = 0 的 base case，所以 dp[i-1][0][0] = 0。
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[prices.length - 1][0];
    }

    public static int maxProfit3(int[] prices) {
        if (prices.length == 0) return 0;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int price : prices) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);
            dp_i_1 = Math.max(dp_i_1, -price);
        }
        return dp_i_0;
    }
}
