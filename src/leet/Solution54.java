package leet;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 *
 * 输入:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class Solution54 {
    public static void main(String[] args){
        int[][] mat = {};
        List<Integer> out = spiralOrder(mat);

        System.out.println(out);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> out = new ArrayList<>();
        if (matrix.length == 0){return out;}

        int numRows = matrix.length;
        int numCols = matrix[0].length;

        int curRow = 0;
        int curCol = 0;

        boolean goDown = false;
        boolean goUp = false;

        boolean goLeft = false;
        boolean goRight = true;
        int count = 0;

        int upRow = numRows;
        int lowRow = 0;
        int upCol = numCols;
        int lowCol = 0;

        while (count < numCols*numRows) {
            while (curRow < upRow && curRow >=lowRow && curCol < upCol && curCol >= lowCol) {
                if (goRight) {
                    out.add(matrix[curRow][curCol]);
                    curCol++;
                    count++;
                } else if (goDown) {
                    out.add(matrix[curRow][curCol]);
                    curRow++;
                    count++;
                } else if (goUp) {
                    out.add(matrix[curRow][curCol]);
                    curRow--;
                    count++;
                } else if (goLeft) {
                    out.add(matrix[curRow][curCol]);
                    curCol--;
                    count++;
                }
            }

            if (goRight){
                goDown = !goDown;
                goRight = !goRight;
                curCol--;
                curRow++;
            }
            else if (goDown){
                goDown = !goDown;
                goLeft = !goLeft;
                curRow--;
                curCol--;
            }
            else if (goLeft){
                goLeft = !goLeft;
                goUp = !goUp;
                curCol++;
                curRow--;

                lowRow++;
            }
            else if (goUp){
                goUp = !goUp;
                goRight = !goRight;
                curRow++;
                curCol++;

                upCol--;
                upRow--;
                lowCol++;
            }
        }

        return out;
    }
}
