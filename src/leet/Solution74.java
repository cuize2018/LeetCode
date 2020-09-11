package leet;

import java.util.Arrays;

public class Solution74 {
    public static void main(String[] args) {
        int[][] mat = {{1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}};

        int[][] b = {{1}};
        System.out.println(searchMatrix2(b, 3));
    }

    public static boolean searchMatrix2(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) return false;
        if (matrix[0].length == 0) return false;

        int i = 0;
        int j = rows - 1;
        int mid = -1;
        while (i <= j) {
            mid = (i + j) >>> 1;
            if (matrix[mid][0] == target) return true;
            if (mid < rows - 1 && matrix[mid][0] < target && matrix[mid + 1][0] > target) break;

            if (matrix[mid][0] < target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        int idx = Arrays.binarySearch(matrix[mid], target);
        return idx >= 0;
    }
}
