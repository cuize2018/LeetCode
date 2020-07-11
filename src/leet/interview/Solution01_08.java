package leet.interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution01_08 {
    public static void main(String[] args) {

    }

    public static void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0)return;
        int cols = matrix[0].length;

        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0){
                    rowSet.add(i);
                    colSet.add(j);
                }
            }
        }

        for (int row : rowSet) {
            Arrays.fill(matrix[row], 0);
        }
        for (int col : colSet) {
            for (int i = 0; i < rows; i++) {
                matrix[i][col] = 0;
            }
        }
    }
}
