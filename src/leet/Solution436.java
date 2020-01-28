package leet;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Solution436 {
    public static void main(String[] args) {
        int[][] exp = { {3,4}, {2,3}, {1,2} };
        int[][] exp3 = { {1,4}, {2,3}, {3,4} };
        int[][] exp2 = {{1,2}};
        System.out.println(Arrays.toString(findRightInterval2(exp3)));
    }

    public static int[] findRightInterval(int[][] intervals) {
        int len = intervals.length;
        if (len == 0)return null;

        int[] ans = new int[len];

        for (int i = 0;i < len;i++){
            int[] curr = intervals[i];
            int min_left = Integer.MAX_VALUE;
            int min_index = Integer.MAX_VALUE;

            for (int j = 0;j < len;j++){
                if (i != j){
                    int[] tmp = intervals[j];
                    //check if right
                    if (tmp[0] >= curr[1]){
                        if (tmp[0] < min_left){
                            min_left = tmp[0];
                            min_index = j;
                        }
                    }
                }
            }
            if (min_left == Integer.MAX_VALUE)min_index=-1;
            ans[i] = min_index;
        }
        return ans;
    }

    /**
     * 二分查找
     * @param intervals
     * @return
     */
    public static int[] findRightInterval2(int[][] intervals) {
        int len = intervals.length;
        if (len == 0)return null;

        int[] ans = new int[len];
        Map<int[], Integer> map = new HashMap<>();
        for (int i = 0;i < len;i++){
            map.put(intervals[i], i);
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        for (int i = 0;i < len;i++){
            int[] curr = intervals[i];
            int left = i + 1;
            int right = len - 1;

            while (left < right) {
                int mid = (left + right) >>> 1;

                int[] tmp = intervals[mid];
                if (tmp[0] >= curr[1]){
                    right = mid;
                }
                else {
                    left = mid + 1;
                }
            }

            if (left == right){
                if (intervals[left][0] >= intervals[i][1]){
                    ans[map.get(curr)] = map.get(intervals[left]);
                    continue;
                }
            }
            ans[map.get(curr)] = -1;
        }
        return ans;
    }
}
