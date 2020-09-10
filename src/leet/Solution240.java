package leet;

import java.util.Arrays;

public class Solution240 {
    public static void main(String[] args) {
        int[][] mat = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        //int[][] mat = {{}};
        int target = 20;

        System.out.println(searchMatrix3(mat, target));

    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = 0;
        if (rows != 0) cols = matrix[0].length;
        else return false;

        if (cols == 0) return false;

        for (int i = 0; i < rows; i++) {
            if (target >= matrix[i][0] && target <= matrix[i][cols - 1]) {
                int idx = Arrays.binarySearch(matrix[i], target);
                if (idx >= 0) return true;
            }
        }
        return false;
    }

    public static boolean searchMatrix3(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) return false;
        int cols = matrix[0].length;

        int i = rows - 1;
        int j = 0;

        while (i >= 0 && j < cols) {
            if (matrix[i][j] == target) return true;

            if (matrix[i][j] < target) {
                j++;
            } else {
                i--;
            }
        }
        return false;
    }
}
