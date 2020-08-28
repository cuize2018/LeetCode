package 笔试.bytedance;

/**
 * 给出数组的个数n，上下边界l和r（包括l，r），要求满足所有数组中元素之和满足被3整除的情况有多少种。
 */
public class CodeForces_1105C {
    public static void main(String[] args) {


    }

    /**
     * l到r中被3整除的数有mod0个，被3整除余1的有mod1个，被3整除余2的有mod2个。
     * dp[i][j]表示数组中前i个数的和被3整除余j的个数，我们要求的就是dp[n][0];
     *
     * @param n
     * @param l
     * @param r
     * @return
     */
    public int func(int n, int l, int r) {
        int[][] dp = new int[n][3];

        int mod0 = r / 3 - l / 3;
        if (l % 3 == 0) mod0++;

        int mod1 = (r + 2) / 3 - (l + 2) / 3;
        if ((l + 2) % 3 == 0) mod1++;

        int mod2 = r - l + 1 - mod0 - mod1;

        dp[0][0] = mod0;
        dp[0][1] = mod1;
        dp[0][2] = mod2;
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] * mod0 + dp[i - 1][1] * mod2 + dp[i - 1][2] * mod1;
        }
        return dp[n - 1][0];
    }
}
