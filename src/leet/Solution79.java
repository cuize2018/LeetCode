package leet;

import java.io.StringReader;
import java.util.Arrays;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = 'ABCCED', 返回 true.
 * 给定 word = 'SEE', 返回 true.
 * 给定 word = 'ABCB', 返回 false.
 */
public class Solution79 {
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        char[][] b = {{'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        char[][] c = {{'a'}};
        char[][] d = {{'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}};
        char[][] f = {{'C', 'A', 'A'}, {'A', 'A', 'A'}, {'B', 'C', 'D'}};
        Solution79 solution79 = new Solution79();
        System.out.println(solution79.exist(d, "aaaaaaaaaaaaa"));

    }

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        if (rows == 0) return false;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    board[i][j] = '#';
                    boolean flag = dfs(board, word.substring(1), i, j, -2,-2);
                    if (flag) return true;
                    board[i][j] = word.charAt(0);
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int lastX, int lastY) {
        if (word.length() == 0) {
            return true;
        }

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y] == word.charAt(0) &&
                    (lastX != x || lastY != y)) {
                board[x][y] = '#';
                boolean flag = dfs(board, word.substring(1), x, y, i,j);
                if (flag) return true;
                board[x][y] = word.charAt(0);
            }
        }
        return false;
    }
}
