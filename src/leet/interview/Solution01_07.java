package leet.interview;

import java.util.Arrays;

public class Solution01_07 {
    public static void main(String[] args) {
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] mat2 = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        rotate2(mat);
        for (int[] ints : mat) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void rotate(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return;
        int cols = matrix[0].length;

        int startRow = 0;
        int startCol = 0;
        int endRow = rows / 2;

        int upRow = startRow;
        int upCol = startCol;
        int downRow = rows - 1;
        int downCol = cols - 1;
        while (startRow != endRow) {
            int[] temp = Arrays.copyOfRange(matrix[startRow], upCol, downCol + 1);

            int val = matrix[startRow][startCol];
            for (int row = downRow; row > upRow; row--) {
                matrix[startRow][startCol] = matrix[row][upCol];
                startCol++;
            }
            matrix[startRow][startCol] = val;

            startRow = downRow;
            startCol = upCol;
            val = matrix[startRow][startCol];
            for (int col = downCol; col > upCol; col--) {
                matrix[startRow][startCol] = matrix[downRow][col];
                startRow--;
            }
            matrix[startRow][startCol] = val;

            startRow = downRow;
            startCol = downCol;
            val = matrix[startRow][startCol];
            for (int row = upRow; row < downRow; row++) {
                matrix[startRow][startCol] = matrix[row][downCol];
                startCol--;
            }
            matrix[startRow][startCol] = val;

            startRow = upRow;
            startCol = downCol;
            for (int idx = 0; idx < temp.length; idx++) {
                matrix[startRow][startCol] = temp[idx];
                startRow++;
            }
            upRow++;
            upCol++;
            downRow--;
            downCol--;

            startRow = upRow;
            startCol = startRow;
        }
    }

    public static void rotate2(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return;
        int cols = matrix[0].length;

        //先按对角线翻转
        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < cols; j++) {
                swap(matrix, i, j, j, i);
            }
        }

        //再按每一行的重点翻转
        for (int j = 0; j < cols / 2; j++) {
            for (int i = 0; i < rows; i++) {
                swap(matrix, i, j, i, cols - 1 - j);
            }
        }
    }

    private static void swap(int[][] matrix, int r1, int c1, int r2, int c2) {
        int temp = matrix[r1][c1];
        matrix[r1][c1] = matrix[r2][c2];
        matrix[r2][c2] = temp;
    }
}
