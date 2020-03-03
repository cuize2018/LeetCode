package leet;

public class Solution576 {
    public static void main(String[] args) {
        System.out.println(findPaths(2, 2, 2, 0, 0));
    }

    /**
     * 思路其实很简单，dp[0]初始状态下网格状态，此时只有球的起始位置(i,j)
     * 每一次移动后，dp[t]的状态依赖于dp[t-1]，dp[t]中(row,col)位置的数值
     * 等于上一阶段dp[t-1]中(row,col)上下左右四个位置数值之和。
     * <p>
     * 最后的出界数和0～N-1次移动后的网格状态有关。第t（0<=t<=N-1）次移动后，所有在边缘的球，
     * 在第t+1次移动时都可以移动到外界。所以最后总的出界数 等于 0～N-1次移动后所有边缘坐标存在的球的个数的总和。
     * <p>
     * 具体实现为了避免边界判断，上下左右都补了0，1<=row<=m;1<=col<=n,注意下标使用不要出错。
     * 最后为了避免每次运算都%(1000000007),我们使用long来替代int，最后转换为int即可。
     *
     * @param m
     * @param n
     * @param N
     * @param i
     * @param j
     * @return
     */
    public static int findPaths(int m, int n, int N, int i, int j) {
        if (N == 0) return 0;

        long[][][] dp = new long[N][m + 2][n + 2];
        dp[0][i + 1][j + 1] = 1;

        long res = 0;
        if (i == 0) res++;
        if (i == m - 1) res++;

        if (j == 0) res++;
        if (j == n - 1) res++;

        for (int t = 1; t < N; t++) {
            for (int row = 1; row <= m; row++) {
                for (int col = 1; col <= n; col++) {
                    dp[t][row][col] = dp[t - 1][row - 1][col] + dp[t - 1][row][col - 1] +
                            dp[t - 1][row + 1][col] + dp[t - 1][row][col + 1];
                    dp[t][row][col] %= 1000000007;

                    if (row == 1) res += dp[t][row][col];
                    if (row == m) res += dp[t][row][col];

                    if (col == 1) res += dp[t][row][col];
                    if (col == n) res += dp[t][row][col];
                    res %= 1000000007;
                }
            }
        }
        return (int) res;
    }
}
