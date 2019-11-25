package leet;

import java.util.*;

/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 示例 2:
 *
 * 输入:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 * 进阶:
 *
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
 */
public class Solution73 {
    public static void main(String[] args){
        int[][] mat = {{0,1,2,0},
        {3,4,5,2},
        {1,3,1,5}};

        setZeroes(mat);
        for (int i = 0;i<mat.length;i++){
            System.out.println(Arrays.toString(mat[i]));
        }
    }

    public static void setZeroes(int[][] matrix) {
        Set<Integer> zeroRow = new HashSet<>();
        Set<Integer> zeroCol = new HashSet<>();

        for (int i = 0;i<matrix.length;i++){
            for (int j = 0;j<matrix[0].length;j++){
                if (matrix[i][j] == 0){
                    zeroRow.add(i);
                    zeroCol.add(j);
                }
            }
        }

        for (Integer row:zeroRow){
            for (int j = 0;j<matrix[0].length;j++){
                matrix[row][j] = 0;
            }
        }
        for (Integer col:zeroCol){
            for (int j = 0;j<matrix.length;j++){
                matrix[j][col] = 0;
            }
        }
    }
}
