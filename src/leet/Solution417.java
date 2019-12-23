package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution417 {
    List<List<Integer>> out = new ArrayList<>();
    int[][] mat;


    public static void main(String[] args) {
        int[][] mat1 = {{1,   2,   2,   3, 5},
                {3,   2,   3,  4, 4},
                {2,   4,  5,  3,   1},
                {6, 7,  1,   4,   5},
                {5,  1,   1,   2,   4 }};
        int[][] mat2 = {{1,   2,   3},
                {8,9,4},
                {7,6,5}};
        int [][] mat3 = {{1,2,3,4},
                {12,13,14,5},
                {11,16,15,6},
                {10,9,8,7}};
        System.out.println(pacificAtlantic(mat3));
    }



    public static List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>>out = new ArrayList<>();

        if (matrix.length == 0)return out;
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] dp1 = new boolean[rows][cols];//到a
        boolean[][] dp2 = new boolean[rows][cols];//到b

        for (int j = 0;j<cols;j++)dp1[0][j] = true;
        for (int i = 0;i<rows;i++)dp1[i][0] = true;

        for (int j = cols-1;j>=0;j--)dp2[rows-1][j] = true;
        for (int i = rows-1;i>=0;i--)dp2[i][cols-1] = true;

        int count = 1;
        while (count != 0) {
            count = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (!dp1[i][j]) {
                        //up
                        if (i!=0 && matrix[i][j] >= matrix[i-1][j]){
                            if (dp1[i-1][j]){
                                dp1[i][j] = dp1[i-1][j];
                                count++;continue;
                            }
                        }
                        //left
                        if (j != 0 && matrix[i][j] >= matrix[i][j-1]){
                            if (dp1[i][j-1]){
                                dp1[i][j] = dp1[i][j-1];
                                count++;continue;
                            }
                        }
                        //down
                        if (i != rows - 1 && matrix[i][j] >= matrix[i + 1][j]) {
                            if (dp1[i + 1][j]) {
                                dp1[i][j] = dp1[i + 1][j];
                                count++;continue;
                            }
                        }
                        //right
                        if (j != cols - 1 && matrix[i][j] >= matrix[i][j + 1]) {
                            if (dp1[i][j + 1]) {
                                dp1[i][j] = dp1[i][j + 1];
                                count++;continue;
                            }
                        }
                    }
                    if (!dp2[i][j]) {
                        //up
                        if (i != 0 && matrix[i][j] >= matrix[i - 1][j]) {
                            if (dp2[i - 1][j]) {
                                dp2[i][j] = dp2[i - 1][j];
                                count++;continue;
                            }
                        }
                        //left
                        if (j != 0 && matrix[i][j] >= matrix[i][j - 1]) {
                            if (dp2[i][j - 1]) {
                                dp2[i][j] = dp2[i][j - 1];
                                count++;continue;
                            }
                        }
                        //down
                        if (i != rows - 1 && matrix[i][j] >= matrix[i + 1][j]) {
                            if (dp2[i + 1][j]) {
                                dp2[i][j] = dp2[i + 1][j];
                                count++;continue;
                            }
                        }
                        //right
                        if (j != cols - 1 && matrix[i][j] >= matrix[i][j + 1]) {
                            if (dp2[i][j + 1]) {
                                dp2[i][j] = dp2[i][j + 1];
                                count++;
                            }
                        }
                    }
                }
            }
        }



        for (int i = 0;i<rows;i++){
            for (int j = 0;j<cols;j++){
                if ((dp1[i][j]) && (dp2[i][j])){
                    out.add(Arrays.asList(i, j));
                }
            }
        }
        return out;
    }

    public static List<List<Integer>> pacificAtlantic2(int[][] matrix) {
        List<List<Integer>>out = new ArrayList<>();

        if (matrix.length == 0)return out;
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] dp1 = new boolean[rows][cols];//到a
        boolean[][] dp2 = new boolean[rows][cols];//到b

        for (int j = 0;j<cols;j++)dp1[0][j] = true;
        for (int i = 0;i<rows;i++)dp1[i][0] = true;

        for (int j = cols-1;j>=0;j--)dp2[rows-1][j] = true;
        for (int i = rows-1;i>=0;i--)dp2[i][cols-1] = true;

        int count = 1;
        while (count != 0) {
            count = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (!dp1[i][j]) {
                        count += process(i,j,dp1,matrix);
                    }
                    if (!dp2[i][j]) {
                        count += process(i,j,dp2,matrix);
                    }
                }
            }
        }

        for (int i = 0;i<rows;i++){
            for (int j = 0;j<cols;j++){
                if ((dp1[i][j]) && (dp2[i][j])){
                    out.add(Arrays.asList(i, j));
                }
            }
        }
        return out;
    }

    private static int process(int i, int j, boolean[][] dp, int[][] matrix){
        int count = 0;
        int rows = dp.length;int cols = dp[0].length;
        //up
        if (i!=0 && matrix[i][j] >= matrix[i-1][j]){
            if (dp[i-1][j]){
                dp[i][j] = dp[i-1][j];
                count++;
            }
        }
        //left
        if (j != 0 && matrix[i][j] >= matrix[i][j-1]){
            if (dp[i][j-1]){
                dp[i][j] = dp[i][j-1];
                count++;
            }
        }
        //down
        if (i != rows - 1 && matrix[i][j] >= matrix[i + 1][j]) {
            if (dp[i + 1][j]) {
                dp[i][j] = dp[i + 1][j];
                count++;
            }
        }
        //right
        if (j != cols - 1 && matrix[i][j] >= matrix[i][j + 1]) {
            if (dp[i][j + 1]) {
                dp[i][j] = dp[i][j + 1];
                count++;
            }
        }
        return count;
    }
}