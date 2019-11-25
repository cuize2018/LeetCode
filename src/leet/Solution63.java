package leet;

public class Solution63 {
    public static void main(String[] args){
        int[][] a = {{0,0,0},
        {0,1,0},
        {0,0,0}};
        System.out.println(uniquePathsWithObstacles(a));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] res = new int[obstacleGrid.length][obstacleGrid[0].length];

        boolean stop = false;
        for (int i = 0;i<res[0].length;i++){
            if (obstacleGrid[0][i] != 1 && !stop){
                res[0][i] = 1;
            }
            else {
                res[0][i] = 0;
                stop = true;
            }
        }

        stop = false;
        for (int i = 0;i<res.length;i++){
            if (obstacleGrid[i][0] != 1 && !stop){
                res[i][0] = 1;
            }
            else {
                res[i][0] = 0;
                stop = true;
            }
        }

        for (int i = 1;i<res.length;i++){
            for (int j = 1;j<res[i].length;j++){
                if (obstacleGrid[i][j] == 1){
                    res[i][j] = 0;
                }
                else {
                    res[i][j] = res[i-1][j] + res[i][j-1];
                }
            }
        }

        return res[obstacleGrid.length-1][obstacleGrid[0].length-1];

    }
}
