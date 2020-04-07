package leet;

import java.util.Map;

public class Solution53 {
    public static void main(String[] args) {

    }

    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        if (len == 0)return 0;

        int dp_i_j;
        int dp_i_j_1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            dp_i_j = nums[i];
            dp_i_j_1 = dp_i_j;
            max = Math.max(max, dp_i_j);

            for (int j = i+1; j < len; j++) {
                dp_i_j = dp_i_j_1 + nums[j];
                dp_i_j_1 = dp_i_j;
                max = Math.max(max, dp_i_j);
            }
        }
        return max;
    }

    /**
     * 这道题用动态规划的思路并不难解决，比较难的是后文提出的用分治法求解，但由于其不是最优解法，所以先不列出来
     * 动态规划的是首先对数组进行遍历，当前最大连续子序列和为 sum，结果为 ans
     * 如果 sum > 0，则说明 sum 对结果有增益效果，则 sum 保留并加上当前遍历数字
     * 如果 sum <= 0，则说明 sum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字
     * 每次比较 sum 和 ans的大小，将最大值置为ans，遍历结束返回结果
     * 时间复杂度：O(n)
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        int len = nums.length;
        if (len == 0)return 0;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            if (sum >= 0) {
                sum += num;
            } else {
                sum = num;
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    public static int maxSubArray3(int[] nums) {
        int len = nums.length;
        if (len == 0)return 0;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < len; i++) {
            //要么是当前数字， 要么是紧接着前一个数字的和
            dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
