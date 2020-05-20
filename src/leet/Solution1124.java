package leet;

import java.util.HashMap;
import java.util.Map;

public class Solution1124 {
    public static void main(String[] args) {
        int[] hours = {9, 9, 6, 0, 6, 6, 9};
        System.out.println(longestWPI(hours));
    }

    /**
     * 同方法一中用1表示当天劳累，-1表示当前悠闲。
     * 我们在累加中，使用sum[i]表示[0,i]的累加和。如果sum[j] - sum[i] > 0，说明[i + 1, j]是劳累天数更多，这是一个表现良好时间段。
     * 我们可以在计算累加和的过程中记录累加和的值对应的索引信息，但是累加和可能会重复，使用哈希表记录时为了避免覆盖出差，就需要仔细考虑什么时候该记录，什么时候不需要记录。
     *
     * 如果sum[i] > 0，说明从0到i这一天，是表现良好时间段。累加到i的过程中，这个区间一定是目前最长的良好时间段，所以res = i + 1。
     * 在这个前提下，如果后序sum[j] - sum[i] > 0，说明sum[j] > 0且j > i ，那么区间[0,j]的长度大于[0,i]，直接更新res = j + 1就可以。所以累加和为正的索引不需要记录，因为左边界固定为0，直接更新就可以。
     *
     * 如果sum[i] <= 0，是否就需要记录呢？如果有x < i且sum[x] == sum[i]，那么再往后如果有sum[j] - sum[i] > 0，
     * 也一定会有sum[j] - sum[x] > 0，且区间长度是[x,j]大于[i,j]，所以i这个信息是没有意义的，因为答案一定不是[i,j]。
     * 所以当sum[i] <= 0时，只记录第一次出现该值的索引。
     * 所以要先判断，如果!sumToIndex.containsKey(sum)，才记录前缀和的值及对应的索引 sumToIndex.put(sum, i);
     *
     * 另一个关键点如果sum[i] <= 0，要去查看是否有x < i，且sum[i] - sum[x] > 0，这样的区间是满足题意的时间段，
     * 但是这样的区间不止一个，要找最长的一个。其实最长的一个就是sum[i] - sum[x] == 1的[x, i]。理由如下
     *
     * 假设sum[i] = -1，如果sum[i] - sum[y] == 2，那么sum[y] = -3。
     * 我们记录的是某个累加和第一次出现的索引，如果要能累加得到sum[y] = -3，前面一定会先出现sum[x] = -2（不然怎么可能靠每次-1得到-3），
     * 那此时区间[x, i]长度肯定大于[y, i]。
     * 所以sum[i] - sum[x] == 1，就是能满足sum[i] - sum[x] > 0的最长区间。
     * 所以if(sumToIndex.containsKey(sum - 1))，就更新res = Math.max(res, i - sumToIndex.get(sum - 1));
     *
     * @param hours
     * @return
     */
    public static int longestWPI(int[] hours) {
        if (hours.length == 0) return 0;
        int res = 0;
        int preSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < hours.length; i++) {
            preSum += hours[i] > 8 ? 1 : -1;

            if (preSum > 0) {
                res = i + 1;
            } else  {
                if (!map.containsKey(preSum)) {
                    map.put(preSum, i);
                }
                if (map.containsKey(preSum - 1)) {
                    res = Math.max(res, i - map.get(preSum - 1));
                }
            }
        }
        return res;
    }
}
