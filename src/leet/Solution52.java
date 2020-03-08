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
