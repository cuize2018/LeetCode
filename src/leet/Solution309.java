package leet;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Solution309 {
    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        System.out.println(maxProfit4(prices));

    }

    public static int maxProfit(int[] prices) {
        if (prices.length == 0)return 0;
        int n = prices.length;
        int out = 0;
        int[][] dp = new int[prices.length-1][];//dp.length = 5

        for (int i = dp.length-1;i>=0;i--){
            dp[i] = new int[dp.length-i];
            for (int j = 0;j<Math.min(dp[i].length,3);j++){
                dp[i][j] = prices[j+i+1]-prices[i];
                out = Math.max(out, dp[i][j]);
            }
        }


        for (int i = 0;i<dp.length-3;i++){
            for (int j = 3;j<dp[i].length;j++){
                int max_j = (j+1)+(i+1)-3;
                max_j = max_j - (i+1+1);

                int max_val = prices[j+i+1]-prices[i];

                for (int m = 0; m <= max_j;m++){
                    max_val = Math.max(max_val, dp[i][m]+dp[m+i+2+1][(j+i+2-1)-(m+i+2+1)-1]);
                }
                dp[i][j] = max_val;
                out = Math.max(out, dp[i][j]);
            }

        }

        return out;

    }

    /**
     * hold：继续持有股票
     * sold：卖出股票
     * rest：什么都不做
     *
     * hold： 可由两个情况转换来
     * 前一天hold，当日rest
     * 前一天rest，当日买入股票变为hold
     *
     * sold：
     * 前一天hold，当日卖出股票
     *
     * rest：
     * 前一天sold，当日必须rest
     * 前一天rest，当日继续rest
     *
     * sold[i] = hold[i-1] + price[i];
     * hold[i] = max(hold[i-1], rest[i-1] - price[i])
     * rest[i] = max(rest[i-1], sold[i-1])
     *
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        if (prices.length == 0)return 0;
        int n = prices.length;
        int[] sell = new int[n+1];sell[0] = 0;
        int[] rest = new int[n+1];rest[0] = 0;
        int[] hold = new int[n+1];hold[0] = Integer.MIN_VALUE;

        for (int i = 1;i <=n;i++){
            sell[i] = hold[i-1]+prices[i];
            rest[i] = Math.max(sell[i-1], rest[i-1]);
            hold[i] = Math.max(hold[i-1], rest[i-1]-prices[i]);
        }

        return Math.max(hold[n], sell[n]);

    }


    /**
     * 对于股票，每天只有两种状态：手中有股票，手中无股票。
     * 对于手中有股票进行拆解：
         *  1. 前一天手中有股票，今天什么都不做
         *  2. 前一天手中无股票，并且前一天没有卖股票，今天买股票
     * 对于手中无股票进行拆解：
         *  1. 前一天手中无股票，今天什么都不做
         *  2. 前一天手中有股票，今天卖股票
     * @param prices
     * @return
     */
    public  static int maxProfit3(int[] prices) {
        // dpi0表示第i天手中持有股票的最大获利
        // dpi1表示第i天手中无股票的最大获利
        // dpi2表示第i天手中无股票且不卖出股票的最大获利
        int n = prices.length;
        int dpi0 = Integer.MIN_VALUE, dpi1 = 0, dpi2 = 0;
        for (int i = 0; i < n; ++i) {
            int tmp = dpi0;
            dpi0 = Math.max(dpi0, dpi2 - prices[i]);
            dpi2 = dpi1;
            dpi1 = Math.max(dpi1, tmp + prices[i]);
        }

        return Math.max(dpi1, dpi2);
    }

    public static int maxProfit4(int[] prices) {
        int n = prices.length;
        if (n == 0)return 0;

        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i == 0){
                dp[i][0] = 0;
                dp[i][1] = -prices[0];
            }
            else if (i == 1){
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i-1][1], - prices[i]);
            }
            else {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
                //解释：第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 。
                dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i]);
            }
        }
        return dp[n-1][0];
    }

}
