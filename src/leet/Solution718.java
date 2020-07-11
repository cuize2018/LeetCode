package leet;

public class Solution718 {
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 2, 1};
        int[] B = {3, 2, 1, 4, 7};

        System.out.println(findLength(A, B));

    }

    /**
     * dp[i][j]代表以A[i-1]与B[j-1]结尾的公共字串的长度
     * 公共字串必须以A[i-1]，B[j-1]结束
     * @param A
     * @param B
     * @return
     */
    public static int findLength(int[] A, int[] B) {
        int[][] dp = new int[A.length + 1][B.length + 1];
        int res = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }
}
