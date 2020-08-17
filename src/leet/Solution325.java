package leet;

import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution325 {
    public static void main(String[] args) {
        int[] nums = {1, -1, 5, -2, 3};
        int k = 3;
        System.out.println(maxSubArrayLen(nums, k));
    }

    public static int maxSubArrayLen(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] dp = new int[n + 1];
        // 求前缀和，并利用 Map<前缀和, 对应索引> 储存
        Map<Integer, Integer> sum2Idx = new HashMap<>();
        int max = 0;

        sum2Idx.put(0, 0);
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + nums[i - 1];
            if (!sum2Idx.containsKey(dp[i])) {
                sum2Idx.put(dp[i], i);
            }
        }
        // 从后向前遍历数组，i为子数组的结尾，寻找符合条件的前缀和及其索引
        int i = n;
        while (i > 0) {
            if (sum2Idx.containsKey(dp[i] - k)) {
                max = Math.max(i - sum2Idx.get(dp[i] - k), max);
            }
            i--;
        }
        return max;
    }
}
