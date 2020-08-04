package leet;

import java.util.Arrays;
import java.util.Comparator;

public class Solution435 {
    public int count = Integer.MIN_VALUE;
    public static void main(String[] args) {
        int[][] a = { {1,2}, {2,3}, {3,4}, {1,3} };
        int[][] b = { {1,2}, {1,2},{1,2} };
        int[][] c = { {1,2}, {2,3} };
        int[][] d = {{1,100},{11,22},{1,11},{2,12}};
        int[][] e = {{0,1},{3,4},{1,2}};

        Solution435 sol = new Solution435();
        System.out.println(sol.eraseOverlapIntervals(a));
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0)return 0;
        int n = intervals.length;
        /*--------------------最多不重叠区间数目------------------*/
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int res = 1;
        int end = intervals[0][1];
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= end){
                res++;
                end = intervals[i][1];
            }
        }
        //至少需要去除的区间 = 总区间数目- 不重叠区间数目
        return n - res;
    }
}
