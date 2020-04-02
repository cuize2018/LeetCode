package leet;

import java.util.*;

public class Solution289 {
    public static void main(String[] args) {

    }

    public static void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = rows > 0 ? board[0].length : 0;

        int[][] new_board = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int count = 0;
                for (int m = i - 1; m <= i + 1; m++) {
                    if (m < 0 || m == rows) continue;

                    for (int n = j - 1; n <= j + 1; n++) {
                        if (n < 0 || n == cols) continue;

                        if (m != i || n != j) {
                            if (board[m][n] == 1) {
                                count++;
                            }
                        }
                    }
                }

                if (board[i][j] == 1) {
                    if (count < 2 || count > 3) new_board[i][j] = 0;
                    else new_board[i][j] = 1;
                } else {
                    if (count == 3) new_board[i][j] = 1;
                }
            }
        }
//        board = new_board;
        for (int i = 0; i < board.length; i++) {
            board[i] = Arrays.copyOfRange(new_board[i], 0, new_board[i].length);
        }
    }


    public static void gameOfLife2(int[][] board) {
        int rows = board.length;
        int cols = rows > 0 ? board[0].length : 0;

        Queue<Integer> ChangedRow = new ArrayDeque<>();
        Queue<Integer> ChangedCol = new ArrayDeque<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int count = 0;
                for (int m = i - 1; m <= i + 1; m++) {
                    if (m < 0 || m == rows) continue;

                    for (int n = j - 1; n <= j + 1; n++) {
                        if (n < 0 || n == cols) continue;

                        if (m != i || n != j) {
                            if (board[m][n] == 1) {
                                count++;
                            }
                        }
                    }
                }

                if (board[i][j] == 1) {
                    if (count < 2 || count > 3) {
                        ChangedRow.add(i);
                        ChangedCol.add(j);
                    }
                } else {
                    if (count == 3) {
                        ChangedRow.add(i);
                        ChangedCol.add(j);
                    }
                }
            }
        }

        while (!ChangedRow.isEmpty()) {
            int m = ChangedRow.remove();
            int n = ChangedCol.remove();
            board[m][n] = board[m][n] == 1 ? 0 : 1;
        }

        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

}
