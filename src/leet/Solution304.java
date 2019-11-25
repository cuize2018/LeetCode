package leet;

public class Solution304 {
    public static void main(String[] args) {
        int[][] mat = {{-4,-5}};
        NumMatrix a = new NumMatrix(mat);
//        a.sumRegion(0,0,0,0);
//        a.sumRegion(0,0,0,1);
        a.sumRegion(0,1,0,1);
    }
}

class NumMatrix {
    private int[][] mat;
    private int[][] dp;

    public NumMatrix(int[][] matrix) {
        this.mat = matrix;

        int rows = mat.length;
        int cols = rows==0?0:mat[0].length;
        this.dp = new int[rows][cols];

        helper(rows,cols);
    }

    public void helper(int rows, int cols){
        if (rows==0 || cols == 0)return;
        dp[0][0] = mat[0][0];

        for (int j = 1;j<cols;j++){
            dp[0][j] = dp[0][j-1] + mat[0][j];
        }
        for (int i = 1;i<rows;i++){
            dp[i][0] = dp[i-1][0] + mat[i][0];
        }

        for (int i = 1;i<rows;i++){
            for (int j = 1;j<cols;j++){
                dp[i][j] = mat[i][j] + dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int up = (row1==0)? 0:dp[row1-1][col2];
        int left = (col1==0)? 0:dp[row2][col1-1];
        int left_up = (row1==0||col1==0)? 0:dp[row1-1][col1-1];

        return dp[row2][col2] - up - left + left_up;
    }
}
