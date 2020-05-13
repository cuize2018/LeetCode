package leet.interview;

public class Solution04 {
    public static void main(String[] args) {

    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) return false;
        int cols = matrix[0].length;

        int i = rows - 1;
        int j = 0;

        while (i >= 0 && i < rows && j >= 0 && j < cols) {
            if (matrix[i][j] == target) return true;

            if (matrix[i][j] > target) {
                i--;
            } else {
                j++;
            }
        }
        return false;
    }
}
