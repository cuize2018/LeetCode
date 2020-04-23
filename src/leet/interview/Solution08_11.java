package leet.interview;

public class Solution08_11 {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(waysToChange(n));
    }

    public static int waysToChange(int n) {
        if (n == 0) return 0;
        int[] dp = new int[n + 1];
        dp[0] = 1;

        int[] coins = {1, 5, 10, 25};

        for (int j = 1;j<4;j++) {
            for (int i = coins[j]; i <= n; i++) {
                dp[i] += dp[i - j] % 1000000007;
            }
        }
        return dp[n] % 1000000007;
    }

    /**
     * dp[i][j]=dp[i-1][j]+dp[i][j-coins[i]]
     * 令 dp[i][j] 为遍历到当下这个硬币时，组成金额 j 的方法数目
     * 有两种可能性（1）当前这个硬币没有取，dp[i][j] = dp[i-1][j]；
     *           （2）当前这个硬币取了，dp[i][j] = dp[i][j-coins[i]]。最后的结果是两者的和
     *

     * @param n
     * @return
     */
    public static int waysToChange2(int n) {
        int[] coins = {1, 5, 10, 25};
        int[][] dp = new int[4][n+1];

        for (int j = 0; j <= n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 0; i < 4; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < 4; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i-1][j];
                if (j - coins[i] >= 0){
                    dp[i][j] += dp[i][j-coins[i]]%1000000007;
                }
            }
        }
        return dp[3][n];
    }
}
