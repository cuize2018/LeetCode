package leet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Solution416 {
    public static void main(String[] args) {
        int[] a = {2,2,3,5};
        System.out.println(canPartition(a));
    }

    /**
     * 递归回溯
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n];

        Arrays.sort(nums);

        sum[0] = nums[0];
        for (int i = 1;i<n;i++){
            sum[i] = sum[i-1] + nums[i];
        }

        if (sum[n-1] % 2 == 0){
            int half = sum[n-1]/2;
            return helper(nums, half);
        }
        else {
            return false;
        }
    }

    private static boolean helper(int[] list, int target){

        for (int i = list.length-1;i>=0;i--){
            int new_target = target - list[i];
            if (new_target < 0){
                return false;
            }
            if (new_target == 0){
                return true;
            }

            boolean flag = helper(Arrays.copyOfRange(list, 0,i), new_target);
            if (flag)return true;
        }
        return false;
    }

    /**
     * 第一步： 问题转化
     * 要使得两个子集元素和相等，即为从数组中挑出部分元素组成集合A，使得sumA = SUM - sumA；
     * 也就是sumA = SUM / 2；这便是一个经典的0 - 1背包问题。
     * 假如SUM为奇数，直接返回false。
     * 第二步： 动态规划解决0 - 1背包问题
     * 建立dp数组，长度target = sum + 1;
     * dp[i]表示的为nums数组此时能否组成和为i的状态，dp[0] = 1;
     * 我们让num遍历数组nums，对于每一个num，建立内层循环i = target,当i >= num的时候，更新dp[i]
     * 假如dp[i - num]为true，则表示在num之前的数组可以找出和为i - num的集合，那么再加入了num之 后，数组也能找出和为i的集合，于是dp[i]更新为true。
     * 最后，返回dp[target]即可。
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        //转化为经典的01背包问题
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 == 1)
            return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        //建立dp数组，dp[i]表示能否找到和为i的数组元素集合
        for (int num : nums) {
//            for (int i = target; i >= num; i--) {
//                if (dp[i - num])
//                    dp[i] = true;
//            }
                if (dp[target - num])
                    dp[num] = true;
        }
        return dp[target];
    }


}
