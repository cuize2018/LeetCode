package leet;

public class Solution63 {
    public static void main(String[] args){
        int[][] a = {{0,0,0},
        {0,1,0},
        {0,0,0}};
        System.out.println(uniquePathsWithObstacles2(a));
    }

    public static int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        if (rows == 0)return 0;
        int cols = obstacleGrid[0].length;

        int[][] dp = new int[rows][cols];

        dp[0][0] = obstacleGrid[0][0]==0?1:0;
        for (int j = 1; j < cols; j++) {
            if (obstacleGrid[0][0] == 1)break;
            if (obstacleGrid[0][j] == 1){
                break;
            }
            dp[0][j] = 1;
        }

        for (int i = 1; i < rows; i++) {
            if (obstacleGrid[0][0] == 1)break;
            if (obstacleGrid[i][0] == 1){
                break;
            }
            dp[i][0] = 1;
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (obstacleGrid[i][j] != 1){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[rows-1][cols-1];
    }
}
