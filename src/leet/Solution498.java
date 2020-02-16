package leet;

import java.util.Arrays;

public class Solution498 {
    public static void main(String[] args) {
        int[][] a = {
        { 1, 2, 3 },
        { 4, 5, 6 },
        { 7, 8, 9 }};
        System.out.println(Arrays.toString(findDiagonalOrder(a)));
    }

    public static int[] findDiagonalOrder(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0)return new int[rows];
        int cols = matrix[0].length;

        int[] out = new int[rows*cols];
        int mov_row = 0;
        int mov_col = 0;
        boolean goUp = true;

        for (int i = 0; i < out.length; i++){
            out[i] = matrix[mov_row][mov_col];
            int temp_row = mov_row;
            int temp_col = mov_col;
            if (goUp){
                if (mov_row-1 < 0 || mov_col+1 >= cols){
                    if (mov_row-1 < 0){
                       if (mov_col+1 < cols)temp_col++;
                       goUp = false;
                    }
                    if (mov_col + 1 >= cols){
                        temp_row++;
                        goUp = false;
                    }
                    mov_row = temp_row;
                    mov_col = temp_col;
                }
                else {
                    mov_row--;
                    mov_col++;
                }
            }
            else {
                if (mov_row+1 >= rows || mov_col-1 < 0){
                    if (mov_row+1 >= rows){
                        temp_col++;
                        goUp = true;
                    }
                    if (mov_col - 1 < 0){
                        if (mov_row+1 < rows)temp_row++;
                        goUp = true;
                    }
                    mov_row = temp_row;
                    mov_col = temp_col;
                }
                else {
                    mov_row++;
                    mov_col--;
                }
            }
        }
        return out;
    }
}
