package leet;

import java.util.*;

public class Solution892 {
    public static void main(String[] args) {
        int[][] grid = {{1, 2}, {3, 4}};
        int[][] grid2 = {{1, 2}, {3, 1}};
        Solution892 solution892 = new Solution892();
        System.out.println(solution892.surfaceArea(grid2));
    }

    /**
     * 首先，一个柱体一个柱体的看，每个柱体是由：2个底面（上表面/下表面）+ 所有的正方体都贡献了4个侧表面积。
     * 然后，把柱体贴合在一起之后，我们需要把贴合的表面积给减掉，两个柱体贴合的表面积就是 两个柱体高的最小值*2.
     *
     * @param grid
     * @return
     */
    public int surfaceArea(int[][] grid) {
        int sum = 0;
        int rows = grid.length;
        if (rows == 0) return 0;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] > 0) {
                    int nums = grid[i][j];
                    // 一个柱体中：2个底面 + 所有的正方体都贡献了4个侧表面积
                    sum += (nums << 2) + 2;
                    if (i > 0) {
                        // 减掉 i 与 i-1 相贴的两份表面积
                        sum -= Math.min(grid[i - 1][j], nums) << 1;
                    }

                    if (j > 0) {
                        // 减掉 j 与 j-1 相贴的两份表面积
                        sum -= Math.min(grid[i][j - 1], nums) << 1;
                    }
                }
            }
        }
        return sum;
    }


}
