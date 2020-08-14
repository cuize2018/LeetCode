package leet;

public class Solution1230 {
    public static void main(String[] args) {

    }

    public static double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;
        if (n == 0) return 0;

        double[][] dp = new double[n][target + 1];
        dp[0][0] = 1 - prob[0];
        if (target >= 1) {
            dp[0][1] = prob[0];
        }

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0]*(1-prob[i]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                double neg = dp[i - 1][j] * (1 - prob[i]);
                double pos = dp[i - 1][j - 1] * prob[i];
                dp[i][j] = neg + pos;
            }
        }
        return dp[n - 1][target];
    }
}
