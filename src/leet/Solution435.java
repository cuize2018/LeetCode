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

    /**
     * 如果我们按照起始点对区间进行排序，可以很大程度上简化问题。一旦完成之后，我们就可以使用一个 dp 数组，
     * 其中 dp[i] 存储着只考虑到第i个区间范围内（包括其本身），最大可能的区间数。
     * 现在，当计算 dp[i+1]时，我们不能只考虑 dp[i]的值，因为前面的 i个区间都可能与 第 i+1个区间发生重叠。
     * 因此，我们需要考虑能够使得 j≤i且与第 i+1个区间不发生重叠的所有 dp[j]中的最大值。
     * 状态转移方程如下:
     * dp[i+1] = max(dp[j]) + 1,
     * 其中对于所有 j≤i ，第 i 个和第 j 个区间不发生重叠。
     * 最后，为了计算最终列表中区间的最大区间数量，我们需要找到 dp 数组中的最大值。
     * 最终的结果为区间的总数减去刚刚的结果 (intervals.length−ans)。
     * @param intervals
     * @return
     */
    public  int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0)return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        int[] dp = new int[intervals.length];

        dp[0] = 1;
        int max_dp = dp[0];
        for (int i = 1;i < dp.length;i++){
            int max = 0;
            for (int j = i-1;j >= 0;j--){
                //没有重叠
                if (intervals[j][1] <= intervals[i][0]){
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            max_dp = Math.max(max_dp, dp[i]);
        }
        return intervals.length - max_dp;
    }
}
