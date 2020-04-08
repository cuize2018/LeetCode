package leet.interview;

import java.util.LinkedList;
import java.util.Queue;

public class Solution13 {
    public static void main(String[] args) {
        int m = 3;
        int n = 1;
        int k = 0;
        System.out.println(movingCount(m,n,k));
    }

    /**
     * BFS
     * @param m
     * @param n
     * @param k
     * @return
     */
    public static int movingCount(int m, int n, int k) {
        if (k == 0) return 1;

        int[][] map = new int[m][n];
        int count = 1;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        map[0][0] = 1;

        while (!queue.isEmpty()) {
            Integer info = queue.remove();
            int x = info / 100;
            int y = info % 100;

            for (int[] dir : dirs) {
                int new_x = x + dir[0];
                int new_y = y + dir[1];
                if (new_x >= 0 && new_y >= 0 && new_x < m && new_y < n && map[new_x][new_y] != 1) {
                    int va1 = new_x/10 + new_x%10;
                    int va2 = new_y/10 + new_y%10;
                    if (va1 + va2 <= k) {
                        queue.add(new_x * 100 + new_y);
                        map[new_x][new_y] = 1;
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
