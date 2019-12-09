package leet;

import java.util.Arrays;

public class Solution377 {
    public static void main(String[] args) {
        int[] a = {1,2};
        int tar = 4;
        System.out.println(new Solution377().combinationSum4(a,tar));
    }

    /**
     * 使用dp数组，dp[i]代表组合数为i时使用nums中的数能组成的组合数的个数
     *dp[i]=dp[i-nums[0]]+dp[i-nums[1]]+dp[i=nums[2]]+...
     *举个例子比如nums=[1,3,4],target=7;
     *dp[7]=dp[6]+dp[4]+dp[3]
     *其实就是说7的组合数可以由三部分组成，1和dp[6]，3和dp[4],4和dp[3];
     *dp[0] = 1是为了算上自己的情况，比如dp[1]可以由dp【0】和1这个数的这种情况组成。
     *如:nums中包含7,nums = [1,3,4,7]->dp[7]=dp[6]+dp[4]+dp[3]+dp[0]
     *
     * @param nums
     * @param target
     * @return
     */
    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1;i<=target;i++){
            int sum = 0;
            for (int num : nums) {
                if (i - num >= 0) {
                    sum += dp[i - num];
                }
            }
            dp[i] = sum;
        }

        return dp[target];

    }
}
