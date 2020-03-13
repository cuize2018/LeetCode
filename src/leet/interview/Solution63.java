package leet.interview;

public class Solution63 {
    public static void main(String[] args) {
        int[] a ={7,1,5,3,6,4};
        System.out.println(maxProfit(a));
    }

//    dp[i][1][1] =  Math.max(dp[i-1][1][1], dp[i-1][0][0] - price[i]);
//                =  Math.max(dp[i-1][1][1], 0 - price[i]);
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;

        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int price : prices) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);
            dp_i_1 = Math.max(dp_i_1, -price);
        }
        return dp_i_0;
    }
}
