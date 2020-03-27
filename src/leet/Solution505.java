package leet;

import java.util.*;

public class Solution505 {
    public static void main(String[] args) {
        int[][] maze = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}};
        int[] start = {0, 4};
        int[] end = {4, 4};
        System.out.println(shortestDistance2(maze, start, end));
    }

    public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length;
        if (rows == 0) return -1;
        int cols = maze[0].length;

        Queue<Pair> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(new Pair(start[0] * 200 + start[1], 0));
        visited.add(start[0] * 200 + start[1]);

        Integer end = destination[0] * 200 + destination[1];

        while (!queue.isEmpty()) {
            Pair curr = queue.remove();
            if (curr.getX().equals(end)) {
                return curr.getY();
            }

            int x = (curr.getX()) / 200;
            int y = (curr.getX()) % 200;
            int len = curr.getY();

            int i = x - 1;
            for (; i >= 0; i--) {
                if (maze[i][y] == 1) {
                    break;
                }
            }
            Pair up = new Pair((i + 1) * 200 + y, len + (x - i - 1));

            if (x > 0 && maze[i + 1][y] != 1 && !visited.contains(up.getX())) {
                visited.add(up.getX());
                queue.add(up);
            }

            i = x + 1;
            for (; i < rows; i++) {
                if (maze[i][y] == 1) {
                    break;
                }
            }
            Pair down = new Pair((i - 1) * 200 + y, len + (i - 1 - x));

            if (x < rows - 1 && maze[i - 1][y] != 1 && !visited.contains(down.getX())) {
                visited.add(down.getX());
                queue.add(down);
            }

            int j = y - 1;
            for (; j >= 0; j--) {
                if (maze[x][j] == 1) {
                    break;
                }
            }
            Pair left = new Pair(x * 200 + j + 1, len + (y - j - 1));

            if (y > 0 && maze[x][j + 1] != 1 && !visited.contains(left.getX())) {
                visited.add(left.getX());
                queue.add(left);
            }


            j = y + 1;
            for (; j < cols; j++) {
                if (maze[x][j] == 1) {
                    break;
                }
            }
            Pair right = new Pair(x * 200 + j - 1, len + (j - 1 - y));

            if (y < cols - 1 && maze[x][j - 1] != 1 && !visited.contains(right.getX())) {
                visited.add(right.getX());
                queue.add(right);
            }
        }
        return -1;
    }

    /**
     * 注意在一般的广度优先搜索中，我们不会经过同一个节点超过一次，
     * 但在这道题目中，只要从起始位置到当前节点的步数 count 小于之前记录的最小步数 distance[i, j]，我们就会把 (i, j) 再次加入队列中。
     *
     * @param maze
     * @param start
     * @param destination
     * @return
     */
    public static int shortestDistance2(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length;
        if (rows == 0) return -1;
        int cols = maze[0].length;

        int[][] distance = new int[rows][cols];
        for (int[] dis : distance) {
            Arrays.fill(dis, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        queue.add(start);

        while (!queue.isEmpty()) {
            int[] temp = queue.remove();

            for (int[] dir : dirs) {
                int x = temp[0] + dir[0];
                int y = temp[1] + dir[1];
                int count = 0;

                while (x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                // 如果步数优于distance中的步数，需要重新入队
                if (distance[temp[0]][temp[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[temp[0]][temp[1]] + count;
                    queue.add(new int[]{x - dir[0], y - dir[1]});
                }
            }
        }

        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
    }
}
