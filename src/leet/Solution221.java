package leet;

public class Solution221 {
    public static void main(String[] args){

    }

    /**
     * 我们用 0 初始化另一个矩阵 dp，维数和原始矩阵维数相同；
     * dp[i][j]代表以 i,j为正方形右下角的最大边长是多少 || dp[i][j]指的是该位置向左上角延展为全1正方形最大边长
     * 从 (0,0)开始，对原始矩阵中的每一个 1，我们将当前元素的值更新为
     * dp[i][j] = min(dp[i-1][j], dp[i-1][j-1], dp[i][j-1]) + 1
     * 我们还用一个变量记录当前出现的最大边长，这样遍历一次，找到最大的正方形边长 maxsqlen，那么结果就是 maxsqlen^2。
     * @param matrix
     * @return
     */
    public static int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length:0;

        int[][] dp = new int[rows+1][cols+1];
        int maxlen = 0;

        for (int i = 1;i <= rows;i++){

            for (int j = 1;j <= cols;j++){
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1]))+1;
                    maxlen = Math.max(maxlen, dp[i][j]);
                }
            }
        }

        return maxlen*maxlen;
    }
}
