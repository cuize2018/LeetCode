package leet.interview;

public class Solution10 {
    public static void main(String[] args) {

    }

    public static int numWays(int n) {
        if (n <= 1) return 1;
        int[] dp = new int[n + 1];
        int mod = 1000000007;
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] % mod) + (dp[i - 2] % mod);
        }
        return dp[n] % mod;
    }
}
