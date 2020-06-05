package leet.interview;

import java.util.Arrays;

public class Solution29 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}};
        int[][] matrix3 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};

        int[] res = spiralOrder3(matrix3);
        System.out.println(Arrays.toString(res));

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

    public static int[] spiralOrder3(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return new int[0];
        }
        int cols = matrix[0].length;

        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int nums = rows * cols;
        int[] res = new int[nums];
        int i = 0;
        int j = 0;
        int k = 0;
        int top = 0;
        int down = rows - 1;
        int left = 0;
        int right = cols - 1;

        while (k < nums) {
            int startRow = i;
            int startCol = j;
            for (int m = 0; m < dirs.length; m++) {
                int[] dir = dirs[m];
                while (i >= top && i <= down && j >= left && j <= right && k < nums) {
                    res[k] = matrix[i][j];
                    i += dir[0];
                    j += dir[1];
                    k++;
                    if (i == startRow && j == startCol) {
                        break;
                    }
                }
                i = Math.max(Math.min(i, down), top);
                j = Math.max(Math.min(j, right), left);
                if (m < dirs.length - 1) {
                    i += dirs[m + 1][0];
                    j += dirs[m + 1][1];
                }
            }
            i++;
            j++;
            top++;
            down--;
            left++;
            right--;
        }
        return res;
    }

}
