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

        Solution130 solution130 = new Solution130();
        solution130.solve(bb);
        for (char[] chars : bb) {
            System.out.println(Arrays.toString(chars));
        }
    }

    public void solve(char[][] board) {
        int rows = board.length;
        if (rows == 0) return;
        int cols = board[0].length;

        for (int i = 0; i < cols; i++) {
            if (board[0][i] == 'O') {
                BFS(board, 0, i);
            }
        }

        for (int i = 0; i < cols; i++) {
            if (board[rows - 1][i] == 'O') {
                BFS(board, rows - 1, i);
            }
        }

        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') {
                BFS(board, i, 0);
            }
        }

        for (int i = 0; i < rows; i++) {
            if (board[i][cols - 1] == 'O') {
                BFS(board, i, cols - 1);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void BFS(char[][] board, int row, int col) {
        Queue<Pair> queue = new ArrayDeque<>();
        Pair start = new Pair(row, col);

        queue.add(start);
        board[row][col] = '#';

        while (!queue.isEmpty()) {
            Pair point = queue.remove();
            int x = point.getX();
            int y = point.getY();

            if (x > 0 && board[x - 1][y] == 'O') {
                Pair temp = new Pair(x - 1, y);
                queue.add(temp);
                board[x - 1][y] = '#';
            }

            if (x < board.length - 1 && board[x + 1][y] == 'O') {
                Pair temp = new Pair(x + 1, y);
                queue.add(temp);
                board[x + 1][y] = '#';
            }

            if (y > 0 && board[x][y - 1] == 'O') {
                Pair temp = new Pair(x, y - 1);
                queue.add(temp);
                board[x][y - 1] = '#';
            }

            if (y < board[row].length - 1 && board[x][y + 1] == 'O') {
                Pair temp = new Pair(x, y + 1);
                queue.add(temp);
                board[x][y + 1] = '#';
            }
        }
    }
}
