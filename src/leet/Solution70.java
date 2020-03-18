package leet;

public class Solution70 {
    public static void main(String[] args) {
        System.out.println(climbStairs(2));
    }

    public static int climbStairs(int n) {
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int climbStairs2(int n) {
        if (n == 1) return 1;
        int dp_i_1 = 1;
        int dp_i_2 = 2;

        int dp_i = dp_i_2;
        for (int i = 3; i <= n; i++) {
            dp_i = dp_i_1 + dp_i_2;
            dp_i_1 = dp_i_2;
            dp_i_2 = dp_i;
        }
        return dp_i;
    }
}
