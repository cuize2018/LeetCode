package leet;

import java.util.Map;

public class Solution695 {
    private int max = 0;
    private int area = 0;
    private enum DIR {UP, DOWN, LEFT, RIGHT}
    ;

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};

        int[][] grid2 = {{1}};

        Solution695 solution695 = new Solution695();
        System.out.println(solution695.maxAreaOfIsland(grid2));
    }

    public int maxAreaOfIsland(int[][] grid) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    area = 0;
                    dfs(i, j, grid, null);
                    max = Math.max(max, area);
                }
            }
        }
        return max;
    }

    private void dfs(int row, int col, int[][] grid, DIR last_dir) {
        if (grid[row][col] == 0) {
            return;
        }

        grid[row][col] = 0;
        area++;
        if (row > 0 && last_dir != DIR.DOWN) {
            dfs(row - 1, col, grid, DIR.UP);
        }

        if (row < grid.length-1 && last_dir != DIR.UP) {
            dfs(row + 1, col, grid, DIR.DOWN);
        }

        if (col > 0 && last_dir != DIR.RIGHT) {
            dfs(row, col - 1, grid, DIR.LEFT);
        }

        if (col < grid[row].length-1 && last_dir != DIR.LEFT) {
            dfs(row, col + 1, grid, DIR.RIGHT);
        }
    }


}
