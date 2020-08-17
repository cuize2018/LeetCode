package leet;

public class solution276 {
    public static void main(String[] args) {

    }

    /**
     * 和前一个颜色相同，此时说明前一个的栅栏的颜色应与更前面一个栅栏的颜色不同，
     * 更前一个栅栏的涂色方法有 F(n - 2) 种，前一个栅栏的涂色方式有 (k - 1) 种，所以此时情况应为 F(n - 2) * (k - 1)
     *
     * 和前一个颜色不同，前一个栅栏的涂色方法有 F(n - 1) 种，当前栅栏的涂色方式有 (k - 1) 种，此时情况应为 F(n - 1) * (k - 1)
     * @param n
     * @param k
     * @return
     */
    public static int numWays(int n, int k) {
        int[] dp = new int[n + 1];
        if (n == 0) return 0;
        if (n == 1) return k;
        if (n == 2) return k + k * (k - 1);

        dp[1] = k;
        dp[2] = k + k * (k - 1);
        for (int i = 3; i <= n; i++) {
            int same = dp[i - 2] * (k - 1);
            int diff = dp[i - 1] * (k - 1);
            dp[i] = same + diff;
        }
        return dp[n];
    }

    public static int numWays2(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;

        int[][] dp = new int[n+1][2];//dp[i][0]代表i和i-1相同；dp[i][1]代表i和i-1不同
        dp[2][0] = k;
        dp[2][1] = k*(k-1);
        for (int i = 3; i <= n; i++) {
            dp[i][0] = dp[i-1][1];                      //i和i-1相同，则i-1必须和i-2不同，即取dp[i-1][1]
            dp[i][1] = (dp[i-1][0] + dp[i-1][1])*(k-1); //i和i-1不同，此时i-1无约束，故取i-1所有情况，且i有k-1种取值情况
        }
        return dp[n][0] + dp[n][1];
    }
}
