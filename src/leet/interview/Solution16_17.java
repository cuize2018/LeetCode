package leet.interview;

public class Solution16_17 {
    public static void main(String[] args) {
        int[] nums = {1, -1, 1};
        System.out.println(maxSubArray2(nums));
    }
    //动态规划
    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    //分治法
    public static int maxSubArray2(int[] nums) {
        if (nums.length == 1) return nums[0];
        return help(nums, 0, nums.length - 1);
    }

    private static int help(int[] nums, int left, int right) {
        if (left == right) return nums[left];

        int middle = (left + right) >>> 1;
        int sumLeft = help(nums, left, middle); // 1. 最大数列和在左边
        int sumRight = help(nums, middle + 1, right); // 2. 最大数列和在右边
        // 3. 最大数列和在中间
        // 先求左边的最大和
        int sumL = 0;
        int sumLMax = Integer.MIN_VALUE;
        for (int i = middle; i >= left; i--) {
            sumL += nums[i];
            sumLMax = Math.max(sumL, sumLMax);
        }
        // 求右边的最大和
        int sumR = 0;
        int sumRMax = Integer.MIN_VALUE;
        for (int i = middle + 1; i <= right; i++) {
            sumR += nums[i];
            sumRMax = Math.max(sumR, sumRMax);
        }
        return Math.max(sumLeft, Math.max(sumRight, sumLMax + sumRMax));
    }
}
