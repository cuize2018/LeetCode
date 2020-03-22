package leet;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution200 {
    public static void main(String[] args) {

    }

    /**
     * BFS
     * @param grid
     * @return
     */
    public static int numIslands(char[][] grid) {
        int rows = grid.length;
        if (rows == 0) return 0;
        int cols = grid[0].length;

        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    Queue<Pair> queue = new ArrayDeque<>();
                    grid[i][j] = '0';//标记为'0'表示访问过了
                    queue.add(new Pair(i, j));

                    while (!queue.isEmpty()) {
                        Pair point = queue.remove();
                        int x = point.getX();
                        int y = point.getY();

                        if (x > 0 && grid[x - 1][y] == '1') {
                            queue.add(new Pair(x - 1, y));
                            grid[x - 1][y] = '0';
                        }

                        if (x < rows - 1 && grid[x + 1][y] == '1') {
                            queue.add(new Pair(x + 1, y));
                            grid[x + 1][y] = '0';
                        }

                        if (y > 0 && grid[x][y - 1] == '1') {
                            queue.add(new Pair(x, y - 1));
                            grid[x][y - 1] = '0';
                        }

                        if (y < cols - 1 && grid[x][y + 1] == '1') {
                            queue.add(new Pair(x, y + 1));
                            grid[x][y + 1] = '0';
                        }
                    }
                }
            }
        }
        return count;
    }
}
