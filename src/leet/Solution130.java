package leet;

import java.util.*;

public class Solution130 {

    public static void main(String[] args) {
        char[][] b = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};
        char[][] bb = {
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'}};

        solve(b);
        for (char[] chars : bb) {
            System.out.println(Arrays.toString(chars));
        }
    }

    public static void solve(char[][] board) {
        int rows = board.length;
        if (rows == 0) return;
        int cols = board[0].length;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int x = 0;
        int y = 0;
        for (int[] dir : dirs) {
            while (x >= 0 && x < rows && y >= 0 && y < cols) {
                if (board[x][y] == 'O') {
                    bfs(board, x, y, dirs);
                }
                x += dir[0];
                y += dir[1];
            }
            x = Math.max(0, Math.min(x, rows - 1));
            y = Math.max(0, Math.min(y, cols - 1));
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private static void bfs(char[][] board, int x, int y, int[][] dirs) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x * 500 + y);
        board[x][y] = '#';

        while (!queue.isEmpty()) {
            Integer node = queue.remove();
            int r = node / 500;
            int c = node % 500;
            for (int[] dir : dirs) {
                int i = r + dir[0];
                int j = c + dir[1];
                int key = i * 500 + j;
                if (i >= 0 && i < board.length && j >= 0 && j < board[0].length && board[i][j] == 'O') {
                    queue.add(key);
                    board[i][j] = '#';
                }
            }
        }
    }
}
