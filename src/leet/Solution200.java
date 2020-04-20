package leet;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class Solution200 {
    public static void main(String[] args) {

    }

    /**
     * BFS
     *
     * @param grid
     * @return
     */
    public static int numIslands(char[][] grid) {
        int rows = grid.length;
        if (rows == 0) return 0;
        int cols = grid[0].length;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    Queue<Integer> queue = new LinkedList<>();
                    queue.add(i * 500 + j);
                    grid[i][j] = '0';
                    while (!queue.isEmpty()) {
                        Integer info = queue.remove();
                        int x = info / 500;
                        int y = info % 500;

                        for (int[] dir : dirs) {
                            int xPrime = x + dir[0];
                            int yPrime = y + dir[1];

                            if (xPrime >= 0 && yPrime >= 0 && xPrime < rows && yPrime < cols && grid[xPrime][yPrime] == '1') {
                                queue.add(xPrime * 500 + yPrime);
                                grid[xPrime][yPrime] = '0';
                            }
                        }
                    }
                    count++;
                }
            }
        }
        return count;
    }
}
