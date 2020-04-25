package leet.interview;

public class Solution14_1 {
    public static void main(String[] args) {

    }

    /**
     * n可分为:
     * 1 + n-1, 2 + n-2, 3 + n-3, 4 + n-4, ....以此类推, 故我我们可得出,
     * dp[n]的值取决于4种情况：
     * 1: i*j
     * 2: dp[i]*dp[j]
     * 3: i*dp[j]
     * 4: dp[i]*j
     * 取这4种情况的最大值即为答案， 即dp[i] = max(max(m*n, dp[m]*dp[n], m*dp[n], dp[m]*n), max),其中m = 1 ~ i/2; n = i-1 ~ i/2
     * exp: n = 8
     * 分为：1-7; 2-6; 3-5; 4-4;
     * 即我们取
     * max(1*7, dp[1]*dp[7], 1*dp[7], dp[1]*7)
     * max(2*6, dp[2]*dp[6], 2*dp[6], dp[2]*6)
     * max(3*5, dp[3]*dp[5], 3*dp[5], dp[3]*5)
     * max(4*4, dp[4]*dp[4], 4*dp[4], dp[4]*4)
     * 里的max值为答案
     *
     * @param n
     * @return
     */
    public static int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= i / 2; j++) {
                int a = j * (i - j);
                int b = dp[j] * dp[i - j];
                int c = dp[j] * (i - j);
                int d = j * dp[i - j];
                max = Math.max(max, Math.max(a, Math.max(b, Math.max(c, d))));
            }
            dp[i] = max;
        }
        return dp[n];
    }
}