package leet;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 */
public class Solution64 {
    public static void main(String[] args){
        int[][] a = {{1,3,1},
        {1,5,1},
        {4,2,1}};
        System.out.println(minPathSum(a));
    }

    public static int minPathSum(int[][] grid) {
        int[][] len = grid.clone();
        for (int j = 1;j<grid[0].length;j++){
            len[0][j] = len[0][j] + len[0][j-1];
        }
        for (int j = 1;j<grid.length;j++){
            len[j][0] = len[j][0] + len[j-1][0];
        }

       for (int i = 1;i<grid.length;i++){
           for (int j = 1;j<grid[0].length;j++){
               len[i][j] = Math.min(len[i][j] + len[i-1][j], len[i][j]+len[i][j-1]);
           }
       }
       return len[grid.length-1][grid[0].length-1];
    }
}
