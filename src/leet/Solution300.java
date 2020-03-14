package leet;

import java.util.Arrays;

public class Solution300 {
    public static void main(String[] args) {
//        int[] a = {10,9,2,5,3,7,101,18};
//        int[] a = {1,3,6,7,9,4,10,5,6};
        int[] a = {4, 10, 4, 3, 8, 9};
        System.out.println(lengthOfLIS4(a));
    }

    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length + 1];
        if (nums.length > 0) dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {
            if (nums[i - 1] > nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                int count = 1;
                while (i - 1 - count >= 0 && nums[i - 1] <= nums[i - 1 - count]) {
                    count++;
                }
                if (i - count == 0) dp[i] = 1;
                else {
                    dp[i] = Math.max(dp[i - 1] - (count - 1) + 1, dp[i - 1]);
                }
            }
        }
        return dp[nums.length];
    }

    public static int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length + 1];
        if (nums.length > 0) dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {
            if (nums[i - 1] > nums[i - 2]) {
                int k = 1;
                int count = dp[i - 1];
                boolean flag = false;
                while (k <= i - 1) {
                    if (i - 1 - k >= 0 && nums[i - 1] > nums[i - 1 - k]) {
                        count--;
                    }
                    if (count == 0) {
                        flag = true;
                        break;
                    }
                    k++;
                }
                if (flag) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    dp[i] = dp[i - 1];
                }
            } else {
                int count = 1;
                while (i - 1 - count >= 0 && nums[i - 1] <= nums[i - 1 - count]) {
                    count++;
                }
                if (i - count == 0) {
                    if (dp[i - 1] == 1) dp[i] = 1;
                    else dp[i] = dp[i - 1];
                } else {
                    dp[i] = Math.max(dp[i - 1] - (count - 1) + 1, dp[i - 1]);
                }
            }
        }
        return dp[nums.length];
    }

    /**
     * 我们使用 dp 数组来存储所需的数据。 dp[i] 表示到数组元素一直到 i的情况下可能的最长上升子序列的长度，必须包括 i元素。
     * 为了找出 dp[i]，我们需要尝试在每个可能的最长上升子序列中附加当前元素（nums[i]）到 小于nums[i]的元素nums[j]后, j<i
     * 这样通过添加当前元素形成的新序列也是一个上升子序列。
     * 因此，我们可以很容易地确定 dp[i] 使用：
     * dp[i] = max(dp[j]) + 1,∀0≤j<i
     * 最后，确定最终结果的所有 dp[i] 中的最大值。
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS3(int[] nums) {
        int[] dp = new int[nums.length];
        if (nums.length > 0) dp[0] = 1;

        for (int i = 1; i < dp.length; i++) {
            int max_dpj = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max_dpj = Math.max(max_dpj, dp[j]);
                }
            }
            dp[i] = max_dpj + 1;
        }

        int out = 0;
        for (int dpi : dp) {
            out = Math.max(out, dpi);
        }
        return out;

    }


    public static int lengthOfLIS4(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        //dp[i]代表以第i个元素结尾的最长子序列长度
        int[] dp = new int[n];

        dp[0] = 1;
        int out = dp[0];
        for (int i = 1; i < n; i++) {
            int max = 0;
            //为了找出 dp[i]，我们需要尝试在每个可能的最长上升子序列中附加当前元素（nums[i]）到 小于nums[i]的元素nums[j]后, j<i
            //对于所有j, 0<=j<i, dp[i] = max(dp[j])+1, 其中nums[i] > nums[j]
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            out = Math.max(dp[i], out);
        }

        return out;
    }
}
