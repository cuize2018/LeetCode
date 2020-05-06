package leet.interview;

import java.util.Arrays;

public class Solution29 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

        int[] res = spiralOrder2(matrix2);
        System.out.println(Arrays.toString(res));

    }

    public static int[] spiralOrder(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return new int[0];
        int cols = matrix[0].length;

        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[] res = new int[rows * cols];
        int k = 0;
        int i = 0;
        int j = 0;
        int startRow;
        int startCol;
        boolean flag;
        while (k < rows * cols) {
            startRow = i;
            startCol = j;
            flag = false;
            for (int[] dir : dirs) {
                while (i >= startRow && i < rows - startRow && j >= startCol && j < cols - startCol) {
                    res[k] = matrix[i][j];
                    i = i + dir[0];
                    j = j + dir[1];
                    k++;
                    if (i == startRow && j == startCol) {
                        flag = true;
                        i = startRow + 1;
                        j = startCol + 1;
                        break;
                    }
                    if (k >= rows * cols) return res;
                }
                if (flag) break;

                if (j >= cols - startCol) {
                    i++;
                    j = cols - startCol - 1;
                } else if (i >= rows - startRow) {
                    i = rows - startRow - 1;
                    j--;
                } else if (j < startCol) {
                    j = startCol;
                    i--;
                }
            }
        }
        return res;
    }

    public static int[] spiralOrder2(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return new int[0];
        int cols = matrix[0].length;

        int[] res = new int[rows * cols];
        int rowLow = 0;
        int rowHigh = rows - 1;
        int colLow = 0;
        int colHigh = cols - 1;
        int k = 0;
        while (rowLow <= rowHigh && colLow <= colHigh) {
            for (int j = colLow; j <= colHigh; j++) {
                res[k] = matrix[rowLow][j];
                k++;
            }
            if (k == rows * cols) break;
            for (int i = rowLow + 1; i <= rowHigh; i++) {
                res[k] = matrix[i][colHigh];
                k++;
            }
            if (k == rows * cols) break;
            for (int j = colHigh - 1; j >= colLow; j--) {
                res[k] = matrix[rowHigh][j];
                k++;
            }
            if (k == rows * cols) break;
            for (int i = rowHigh - 1; i >= rowLow + 1; i--) {
                res[k] = matrix[i][colLow];
                k++;
            }
            if (k == rows * cols) break;
            rowLow++;
            rowHigh--;
            colLow++;
            colHigh--;
        }
        return res;
    }
}
