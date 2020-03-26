package leet;

public class Solution999 {
    public static void main(String[] args) {
        char[][] p = {
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'p', 'p', '.', 'R', '.', 'p', 'B', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'B', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}};

        System.out.println(numRookCaptures(p));
    }

    public static int numRookCaptures(char[][] board) {
        int rows = board.length;
        if (rows == 0) return 0;
        int cols = board[0].length;

        int totalCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'R') {
                    if (i > 0) {
                        for (int k = i - 1; k >= 0; k--) {
                            if (board[k][j] == 'B') break;
                            if (board[k][j] == 'p') {
                                totalCount++;
                                break;
                            }
                        }
                    }

                    if (i < rows - 1) {
                        for (int k = i + 1; k < rows; k++) {
                            if (board[k][j] == 'B') break;
                            if (board[k][j] == 'p') {
                                totalCount++;
                                break;
                            }
                        }
                    }

                    if (j > 0) {
                        for (int k = j - 1; k >= 0; k--) {
                            if (board[i][k] == 'B') break;
                            if (board[i][k] == 'p') {
                                totalCount++;
                                break;
                            }

                        }
                    }

                    if (j < cols - 1) {
                        for (int k = j + 1; k < cols; k++) {
                            if (board[i][k] == 'B') break;
                            if (board[i][k] == 'p') {
                                totalCount++;
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }

        return totalCount;
    }
}
