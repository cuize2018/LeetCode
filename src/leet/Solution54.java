package leet;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * <p>
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class Solution54 {
    public static void main(String[] args) {
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        int[][] mat2 = {{1, 2, 3}};
        List<Integer> out = spiralOrder2(mat);

        System.out.println(out);
    }

    public static List<Integer> spiralOrder2(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return new ArrayList<>();
        int cols = matrix[0].length;

        List<Integer> res = new ArrayList<>();
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int up = 0;
        int down = rows - 1;
        int left = 0;
        int right = cols - 1;
        int i = up, j = left;
        while (up <= down && left <= right) {
            res.add(matrix[i][j++]);
            for (int k = 0; k < dirs.length; k++) {
                int[] dir = dirs[k];
                while (i >= up && i <= down && j >= left && j <= right) {
                    if (i == up && j == left) break;
                    res.add(matrix[i][j]);
                    i += dir[0];
                    j += dir[1];
                }
                i = Math.max(up, Math.min(i, down));
                j = Math.max(left, Math.min(j, right));
                if (k < dirs.length - 1) {
                    i += dirs[k + 1][0];
                    j += dirs[k + 1][1];
                }
                if (i < up || i > down || j < left || j > right) break;
            }
            up++;down--;left++;right--;
            i = up;
            j = left;
        }
        return res;
    }
}
