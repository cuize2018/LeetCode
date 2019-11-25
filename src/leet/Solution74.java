package leet;

public class Solution74 {
    public static void main(String[] args){
        int[][] mat = {  {1,   3,  5,  7},
        {10, 11, 16, 20},
        {23, 30, 34, 50}};

        int [][] b = {};
        System.out.println(searchMatrix(b, 50));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0)return false;
        if (matrix[0].length == 0)return false;

        for (int i = 0;i<matrix.length;i++){
            if (matrix[i][0] < target){
                for (int j = 1;j<matrix[i].length;j++){
                    if (matrix[i][j] == target){
                        return true;
                    }
                }
            }
            else if (matrix[i][0] == target){
                return true;
            }
        }
        return false;
    }
}
