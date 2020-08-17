package leet;

public class Solution303 {
    public static void main(String[] args) {

    }


    private class NumArray {
        int[]dp;
        public NumArray(int[] nums) {
            int n = nums.length;
            if (n > 0) {
                dp = new int[n];
                dp[0] = nums[0];

                for (int i = 1; i < n; i++) {
                    dp[i] = dp[i - 1] + nums[i];
                }
            }
        }

        public int sumRange(int i, int j) {
            if (i == 0)return dp[j];
            return dp[i] - dp[i-1];
        }
    }
}
