package leet;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 *
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 *
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 *
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 *
 * 输入: m = 7, n = 3
 * 输出: 28
 *
 */
public class Solution62 {
    public static void main(String[] args){
        System.out.println(uniquePaths3(50,50));
    }

    public static int uniquePaths(int m, int n) {
        if (m == 1 && n == 1)return 1;
        int count = 0;

        if (0 != n-1) {
            count += isSuccess(true, 0, 0, n, m);
        }
        if (0 != m-1) {
            count += isSuccess(false, 0, 0, n, m);
        }

        return count;
    }

    public static int uniquePaths2(int m, int n) {
        if (m==1 && n == 1)return 1;
        int count = 0;

        if (0 != n-1) {
            count += isSuccess2( 0+1, 0, n, m);
        }
        if (0 != m-1) {
            count += isSuccess2( 0, 0+1, n, m);
        }

        return count;
    }

    /**
     * 动态规划
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths3(int m, int n) {
        int [][] map = new int[m][n];
        for (int i = 0;i < m;i++){
            for (int j = 0;j < n;j++){
                if (i == 0){
                    map[i][j] = 1;
                }
                else if (j == 0){
                    map[i][0] = 1;
                }
                else {
                    map[i][j] = map[i-1][j] + map[i][j-1];
                }
            }
        }
        return map[m-1][n-1];
    }

    public static int isSuccess(boolean goDown, int start_row, int start_col, int rows, int cols){
        int count = 0;
        int curr_row = start_row;
        int curr_col = start_col;
        if (goDown){
            curr_row++;
            if (curr_row == rows-1 && curr_col == cols-1){
                count++;
            }
            else {
                if (curr_row != rows-1) {
                    count += isSuccess(true, curr_row, curr_col, rows, cols);
                }
                if (curr_col != cols-1) {
                    count += isSuccess(false, curr_row, curr_col, rows, cols);
                }
            }
        }
        else {
            curr_col++;
            if (curr_row == rows-1 && curr_col == cols-1){
                count++;
            }
            else {
                if (curr_row != rows - 1) {
                    count += isSuccess(true, curr_row, curr_col, rows, cols);
                }
                if (curr_col != cols - 1) {
                    count += isSuccess(false, curr_row, curr_col, rows, cols);
                }
            }
        }
        return count;
    }

    public static int isSuccess2( int start_row, int start_col, int rows, int cols){
        int count = 0;

        if (start_row == rows-1 && start_col == cols-1){
            count++;
            return count;
        }

        if (start_row != rows - 1) {
            count += isSuccess2(start_row+1, start_col, rows, cols);
        }
        if (start_col != cols - 1) {
            count += isSuccess2(start_row, start_col+1, rows, cols);
        }
        return count;
    }
}
