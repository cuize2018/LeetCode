package leet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution52 {
    private int count = 0;
    private Set<Integer> colSet = new HashSet<>();
    private Set<Integer> rightAngleSet = new HashSet<>();
    private Set<Integer> leftAngleSet = new HashSet<>();
    private int N;

    public static void main(String[] args) {
        int n = 4;
        Solution52 solution52 = new Solution52();
        int total = solution52.totalNQueens(n);
        System.out.println(total);
    }

    public int totalNQueens(int n) {
        N = n;
        help(0);
        return count;
    }

    private void help(int row) {
        if (row == N) {
            count++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isValid(row, col)) {
                updateInfos(row, col);
                help(row + 1);
                updateInfos(row, col);
            }
        }
    }

    private boolean isValid(int row, int col) {
        return !colSet.contains(col) && !rightAngleSet.contains(row - col) && !leftAngleSet.contains(row + col);
    }

    private void updateInfos(int row, int col) {
        if (!colSet.contains(col)) {
            colSet.add(col);
            rightAngleSet.add(row - col);
            leftAngleSet.add(row + col);
        } else {
            colSet.remove(col);
            rightAngleSet.remove(row - col);
            leftAngleSet.remove(row + col);
        }
    }


    /**
     * 动态规划
     * 如果在（i，j）位置（第i行第j列）放置一个皇后，接下来在哪些位置不能放置皇后呢？
     *     1、整个第i行的位置都不能放置
     *     2、整个第j列的位置都不能放置
     *     3、如果位置（a,b）满足|a-i|==|b-j|,说明（a,b）与（i，j）处在同一条斜线上，也不能放置
     * 把递归过程直接设计成逐行放置皇后的方式，可以避开的那些不能放置的位置。
     *
     * 接下来用一个数组保存已经放置的皇后位置，假设数组为record，record[i]表示第i行皇后所在的列数。
     * 在递归计算到第i行第j列时，查看record[0..k](k<i)的值：
     *     1、看是否有j相等的值，若有说明（i,j）不能放置皇后，
     *     2、再看是否有|k-i|==|record[k]-j|,若有，也说明（i,j）不能放置皇后。
     * @param n
     * @return
     */
    public int totalNQueens2(int n) {
        //每一行放置皇后的列的位置
        int[] temp = new int[n];
        queenHelper(0, temp, n);
        return count;
    }

    private void queenHelper(int row, int[] temp, int n) {
        if (row == n){
           count++;
           return;
        }

        for (int col = 0; col < n; col++) {
            boolean isOk = checkIsValid(temp, row, col);
            if (isOk){
                temp[row] = col;
                queenHelper(row+1, temp, n);
                temp[row] = 0;
            }
        }
    }


    private boolean checkIsValid(int[] temp, int row, int col) {
        for (int k = 0; k < row; k++) {
            if (temp[k] == col){
                return false;
            }

            if (Math.abs(k-row) == Math.abs(temp[k] - col)){
                return false;
            }
        }
        return true;
    }


}
