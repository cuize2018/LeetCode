package leet.interview;

public class Solution10_09 {
    public static void main(String[] args) {

    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        if (matrix[0].length == 0) return false;

        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = rows - 1; i >= 0; i--) {
            if (matrix[i][0] < target) {
                for (int j = cols - 1; j >= 0; j--) {
                    if (matrix[i][j] == target) {
                        return true;
                    } else if (matrix[i][j] < target) {
                        break;
                    }
                }
            } else if (matrix[i][0] == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * 比较像走路，每个路口都有两种选择
     * 可以理解为一个二叉查找树，根节点为(row-1,0), 左节点比根节点小，即行减1为(x-1,y), 右节点比根节点大，即列加1为(x,y+1),
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        if (matrix[0].length == 0) return false;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int x = rows - 1;
        int y = 0;
        while (x >= 0 && x <= rows - 1 && y >= 0 && y <= cols - 1) {
            if (matrix[x][y] == target) return true;

            if (matrix[x][y] < target) {
                y++;
            } else {
                x--;
            }
        }
        return false;
    }
}
