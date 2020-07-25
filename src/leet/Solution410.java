package leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution410 {

    public static void main(String[] args) {

    }

    /**
     * f[i][j] 表示将数组的前 i 个数分割为 j 段所能得到的最大连续子数组和的最小值
     * 在进行状态转移时，我们可以考虑第 j 段的具体范围，即我们可以枚举 k，
     * 其中前 k 个数被分割为 j-1 段，而第 k+1 到第 i 个数为第j段。
     * 此时，这 j 段子数组中和的最大值，就等于 f[k][j-1] 与 sub(k+1,i) 中的较大值，其中 sub(i,j) 表示数组nums中下标落在区间[i,j]内的数的和。
     *
     * @param nums
     * @param m
     * @return
     */
    public static int splitArray(int[] nums, int m) {
        if (nums.length == 1) return nums[0];
        int[][] dp = new int[nums.length + 1][m + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }

        int[] sum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sum[i] += sum[i - 1] + nums[i - 1];
        }

        dp[0][0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sum[i] - sum[k]));
                }
            }
        }
        return dp[nums.length][m];
    }

    /**
     * 本题中，我们注意到：当我们选定一个值 x，我们可以线性地验证是否存在一种分割方案，满足其最大分割子数组和不超过 x。策略如下：
     * 贪心地模拟分割的过程，从前到后遍历数组，用 sum 表示当前分割子数组的和，cnt 表示已经分割出的子数组的数量（包括当前子数组），
     * 那么每当 sum 加上当前值超过了 x，我们就把当前取的值作为新的一段分割子数组的开头，并将cnt 加 1。遍历结束后验证是否 cnt 不超过m。
     *
     * 这样我们可以用二分查找来解决。
     * 二分的上界为数组nums 中所有元素的和，下界为数组 nums 中所有元素的最大值。
     * 通过二分查找，我们可以得到最小的最大分割子数组和，这样就可以得到最终的答案了。
     * @param nums
     * @param m
     * @return
     */
    public static int splitArray2(int[] nums, int m) {
        int left = 0;
        int right = 0;
        for (int num : nums) {
            right += num;
            left = Math.max(left, num);
        }
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (check(nums, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean check(int[] nums, int sum, int m) {
        int s = 0;
        int count = 1;
        for (int num : nums) {
            if (s + num > sum) {
                count++;
                s = num;
            } else {
                s += num;
            }
        }
        return count <= m;

    }
}
