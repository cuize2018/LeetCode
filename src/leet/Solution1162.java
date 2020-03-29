package leet;

import java.util.*;

public class Solution1162 {
    public static void main(String[] args) {
        int[][] grid = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        int[][] grid2 = {{0, 0, 1, 1, 1}, {0, 1, 1, 0, 0}, {0, 0, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 1}};
        System.out.println(maxDistance2(grid2));
    }

    public static int maxDistance(int[][] grid) {
        int rows = grid.length;
        if (rows == 0) return 0;
        int cols = grid[0].length;

        int max = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    int temp = BFS(grid, i, j);
                    max = Math.max(temp, max);
                }
            }
        }
        return max;
    }

    private static int BFS(int[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int min = 9999;

        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(row * 200 + col);
        visited.add(row * 200 + col);

        while (!queue.isEmpty()) {
            int point = queue.remove();

            for (int[] dir : dirs) {
                int x = point / 200;
                int y = point % 200;
                x += dir[0];
                y += dir[1];

                if (x >= 0 && y >= 0 && x < rows && y < cols) {
                    if (grid[x][y] == 0 && !visited.contains(x * 200 + y)) {
                        queue.add(x * 200 + y);
                        visited.add(x * 200 + y);
                    } else if (grid[x][y] == 1) {
                        int dis = calDis(row, col, x, y);
                        min = Math.min(min, dis);
                    }
                }
            }
        }
        return min == 9999 ? -1 : min;
    }

    private static int calDis(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    /**
     * 相信对于Tree的BFS大家都已经轻车熟路了：要把root节点先入队，然后再一层一层的无脑遍历就行了。
     * <p>
     * 对于图的BFS也是一样滴～ 与Tree的BFS区别如下：
     * 1、tree只有1个root，而图可以有多个源点，所以首先需要把多个源点都入队。
     * 2、tree是有向的因此不需要标志是否访问过，而对于无向图来说，必须得标志是否访问过！
     * 并且为了防止某个节点多次入队，需要在入队之前就将其设置成已访问！
     * <p>
     * 这是一道典型的BFS基础应用，为什么这么说呢？
     * 因为我们只要先把所有的陆地都入队，然后从各个陆地同时开始一层一层的向海洋扩散，那么最后扩散到的海洋就是最远的海洋！
     * 并且这个海洋肯定是被离他最近的陆地给扩散到的！
     *
     * @param grid
     * @return
     */
    public static int maxDistance2(int[][] grid) {
        int rows = grid.length;
        if (rows == 0) return 0;
        int cols = grid[0].length;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    queue.add(i * 200 + j);
                }
            }
        }
        // 从各个陆地开始，一圈一圈的遍历海洋，最后遍历到的海洋就是离陆地最远的海洋。
        boolean hasSea = false;
        int point = -1;
        while (!queue.isEmpty()) {
            point = queue.remove();
            int x = point / 200;
            int y = point % 200;
            // 取出队列的元素，将其四周的海洋入队。
            for (int[] dir : dirs) {
                int new_x = x + dir[0];
                int new_y = y + dir[1];

                if (new_x >= 0 && new_y >= 0 && new_x < rows && new_y < cols && grid[new_x][new_y] == 0) {
                    queue.add(new_x * 200 + new_y);
                    grid[new_x][new_y] = grid[x][y] + 1;// 这里我直接修改了原数组，因此就不需要额外的数组来标志是否访问
                    hasSea = true;
                }
            }
        }
        if (!hasSea || point == -1) return -1;
        // 返回最后一次遍历到的海洋的距离。
        return grid[point / 200][point % 200] - 1;
    }


}
