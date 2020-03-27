package leet;

import java.util.*;

public class Solution490 {
    public static void main(String[] args) {
        int[][] maze = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}};
        int[] start = {0, 4};
        int[] end = {4, 4};
        System.out.println(hasPath(maze, start, end));

    }


    public static boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length;
        if (rows == 0) return false;
        int cols = maze[0].length;

        Queue<Pair> queue = new ArrayDeque<>();
        Set<Pair> visited = new HashSet<>();
        queue.add(new Pair(start));
        visited.add(new Pair(start));

        Pair end = new Pair(destination);
        while (!queue.isEmpty()) {
            Pair curr = queue.remove();
            if (curr.equals(end)) {
                return true;
            }

            int x = curr.getX();
            int y = curr.getY();

            int i = x - 1;
            for (; i >= 0; i--) {
                if (maze[i][y] == 1) {
                    break;
                }
            }
            Pair up = new Pair(new int[]{i + 1, y});

            if (x > 0 && maze[i + 1][y] != 1 && !visited.contains(up)) {
                visited.add(up);
                queue.add(up);
            }

            i = x + 1;
            for (; i < rows; i++) {
                if (maze[i][y] == 1) {
                    break;
                }
            }
            Pair down = new Pair(new int[]{i - 1, y});

            if (x < rows - 1 && maze[i - 1][y] != 1 && !visited.contains(down)) {
                visited.add(down);
                queue.add(down);
            }

            int j = y - 1;
            for (; j >= 0; j--) {
                if (maze[x][j] == 1) {
                    break;
                }
            }
            Pair left = new Pair(new int[]{x, j+1});

            if (y > 0 && maze[x][j + 1] != 1 && !visited.contains(left)) {
                visited.add(left);
                queue.add(left);
            }


            j = y + 1;
            for (; j < cols; j++) {
                if (maze[x][j] == 1) {
                    break;
                }
            }
            Pair right = new Pair(new int[]{x, j - 1});

            if (y < cols - 1 && maze[x][j - 1] != 1 && !visited.contains(right)) {
                visited.add(right);
                queue.add(right);
            }
        }
        return false;
    }
}
