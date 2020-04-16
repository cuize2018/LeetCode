package leet;

import java.util.*;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class Solution56 {
    public static void main(String[] args) {
        int[][] a = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] b = {{4, 6}, {1, 2}, {1, 3}};
        int[][] c = {{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}};
        int[][] d = {{2, 3}, {5, 5}, {2, 2}, {3, 4}, {3, 4}};
        int[][] e = {{0, 2}, {2, 3}, {4, 4}, {0, 1}, {5, 7}, {4, 5}, {0, 0}};
        int[][] f = {{0, 0}, {1, 2}, {5, 5}, {2, 4}, {3, 3}, {5, 6}, {5, 6}, {4, 6}, {0, 0}, {1, 2}, {0, 2}, {4, 5}};
        int[][] out = merge(a);

        for (int i = 0; i < out.length; i++) {
            System.out.println(Arrays.toString(out[i]));
        }
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return intervals;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> out = new ArrayList<>();

        int[] temp = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > temp[1]) {
                out.add(temp);
                temp = intervals[i];
            } else {
                temp[0] = Math.min(temp[0], intervals[i][0]);
                temp[1] = Math.max(temp[1], intervals[i][1]);
            }
        }
        out.add(temp);

        int[][] res = new int[out.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i] = out.get(i);
        }
        return res;

    }
}
