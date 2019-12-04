package leet;

import java.util.*;

public class Solution368 {
    public static void main(String[] args) {
        int[] a = {1,4,16,8};
        System.out.println(largestDivisibleSubset(a));
    }

    /**
     * 做题前的思考：假如已经有一个两两倍数关系的序列2 4 8,若此时来一个8的倍数x，那么x一定是8 4 2的倍数，
     * 2 4 8 x依然保持了两两倍数关系的序列，到了此处就发现可以大问题转换成子问题，于是联想到动态规划。
     * 并且在扩展序列的时候是有序的，所以我们需要给nums排序.
     *
     * 对于已经排序后的nums
     * 设定状态: dp[i]: 以nums[i]结尾的序列最大长度
     * path[i]: 在最大序列中 nums[i]的上一个元素在nums出现的下标
     * 状态转移方程：
     * 使用二重循环,对于每一个nums[i]，看他可以接在之前的哪个序列dp[j]上，使得dp[i]最长
     * nums[i]%nums[j] == 0是可以接的条件，dp[i]<=dp[j]是使得dp[i]变长的条件
     * 初始状态：dp[i] = 1 (i:1 - n) 每一个只有自己的序列长度为1
     * @param nums
     * @return
     */
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums.length == 0)return new ArrayList<>();

        int len = nums.length;
        int[] dp = new int[len];
        for (int i = 0;i<len;i++){
            dp[i] = 1;
        }
        int[] path = new int[len];
        for (int i = 0;i<len;i++){
            path[i] = -1;
        }

        int max_path = 0;
        int max_path_last_idx = 0;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++){
            for (int j = 0; j < i; j++){
                if (nums[i]%nums[j] == 0 && dp[i] <= dp[j]){
                    dp[i] = dp[j] + 1;
                    path[i] = j;
                }
            }

            if (dp[i] > max_path){
                max_path = dp[i];
                max_path_last_idx = i;
            }
        }

        List<Integer> out = new ArrayList<>();
        int idx = max_path_last_idx;

        while (idx != -1){
            out.add(nums[idx]);
            idx = path[idx];
        }
        Collections.reverse(out);
        return out;
    }
}
