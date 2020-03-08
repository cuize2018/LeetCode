package leet;

import java.util.*;

public class Solution51 {
    List<List<String>> out = new ArrayList<>();
    private Set<Integer> colSet = new HashSet<>();
    private Set<Integer> rightAngleSet = new HashSet<>();
    private Set<Integer> leftAngleSet = new HashSet<>();
    private String[][] map;

    public static void main(String[] args) {
        int n = 10;
        Solution51 solution51 = new Solution51();
        System.out.println(solution51.solveNQueens(n));
    }

    /**
     * 路径：board 中小于 row 的那些行都已经成功放置了皇后
     * 选择列表：第 row 行的所有列都是放置皇后的选择
     * 结束条件：row 超过 board 的最后一行
     * <p>
     * 优化isValid的查询，通过3个set来分别记录列、主对角线、副对角线上Q的情况，减少迭代的查询
     * Key值：colIndex, [r-c], [r + c] 作为set的key
     */
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) return null;
        map = new String[n][n];
        for (String[] one : map) {
            Arrays.fill(one, ".");
        }
        helper(0);
        return out;
    }

    /**
     * path: board in [0, row -1]
     * choices for a row : every cols
     * time to end: row == board.length
     *
     * @param row
     */
    public void helper(int row) {
        if (row == map.length) {
            List<String> temp = new ArrayList<>();
            for (String[] chars : map) {
                String collect = String.join("", chars);
                temp.add(collect);
            }
            out.add(temp);
            return;
        }

        int cols = map[row].length;
        for (int col = 0; col < cols; col++) {
            if (!isValid(row, col)) continue;
            updateRecords(row, col);
            helper(row + 1);
            updateRecords(row, col);
        }
    }

    private void updateRecords(int row, int col) {
        if (colSet.contains(col)) {
            map[row][col] = ".";
            colSet.remove(col);
            rightAngleSet.remove(row - col);
            leftAngleSet.remove(row + col);
        } else {
            map[row][col] = "Q";
            colSet.add(col);
            rightAngleSet.add(row - col);
            leftAngleSet.add(row + col);
        }
    }

    private boolean isValid(int row, int col) {
        return !colSet.contains(col) && !rightAngleSet.contains(row - col) && !leftAngleSet.contains(row + col);
    }
}
