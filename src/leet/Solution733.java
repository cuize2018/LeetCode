package leet;

import java.util.LinkedList;
import java.util.Queue;

public class Solution733 {
    public static void main(String[] args) {

    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image.length == 0) return image;

        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        Queue<Integer> queue = new LinkedList<>();
        queue.add(sr * 999 + sc);

        int color = image[sr][sc];
        if (color == newColor) return image;

        while (!queue.isEmpty()) {
            Integer info = queue.remove();
            int r = info / 999;
            int c = info % 999;

            image[r][c] = newColor;
            for (int[] dir : dirs) {
                int x = r + dir[0];
                int y = c + dir[1];

                if (x >= 0 && x < image.length && y >= 0 && y < image[0].length && image[x][y] == color) {
                    queue.add(x * 999 + y);
                    image[x][y] = -1;
                }
            }
        }
        return image;
    }
}
