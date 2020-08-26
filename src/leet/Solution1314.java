package leet;

import java.util.Arrays;

public class Solution1314 {
    public static void main(String[] args) {
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int K = 1;
        int[][] x = matrixBlockSum(mat, K);
        for (int[] s : x) {
            System.out.println(Arrays.toString(s));
        }
    }

    public static int[][] matrixBlockSum(int[][] mat, int K) {
        int rows = mat.length;
        if (rows == 0) return mat;
        int cols = mat[0].length;

        int[][] ans = new int[rows][cols];
        int[][] dp = new int[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ans[i][j] = get(dp, rows, cols, i + K + 1, j + K + 1) -
                        get(dp, rows, cols, i - K, j + K + 1) -
                        get(dp, rows, cols, i + K + 1, j - K) +
                        get(dp, rows, cols, i - K, j - K);
            }
        }
        return ans;
    }

    private static int get(int[][] dp, int rows, int cols, int i, int j) {
        i = Math.max(Math.min(i, rows), 0);
        j = Math.max(Math.min(j, cols), 0);
        return dp[i][j];
    }
}
