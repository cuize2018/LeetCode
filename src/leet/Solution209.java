package leet;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Solution209 {
    public static void main(String[] args){
        int[] a = {1,4,4};
        System.out.println(minSubArrayLen2(4,a));
    }

    public static int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0)return 0;
        int minLen = Integer.MAX_VALUE;

        for (int i = 0;i<nums.length;i++){
            int[] dp = new int[nums.length-i];
            for (int j = 0;j<dp.length;j++){
                if (j == 0){
                    dp[j] = nums[i];
                }
                else {
                    dp[j] = dp[j-1] + nums[j+i];
                }
                if (dp[j] >= s){
                    if ((j+1) < minLen){
                        minLen = j+1;
                    }
                }
            }
        }
        if (minLen == Integer.MAX_VALUE)return 0;
        return minLen;
    }

    public static int minSubArrayLen2(int s, int[] nums) {
        if (nums.length == 0)return 0;
        int minLen = Integer.MAX_VALUE;
        int lastLen = 0;
        for (int i = 0;i<nums.length;i++){
            for (int j = 0;j<nums.length-i;j++){
                if (j == 0){
                    lastLen = nums[i];
                }
                else {
                    lastLen = lastLen + nums[j+i];
                }
                if (lastLen >= s){
                    if ((j+1) < minLen){
                        minLen = j+1;
                    }
                }
            }
        }
        if (minLen == Integer.MAX_VALUE)return 0;
        return minLen;
    }

    public static int minSubArrayLen3(int s, int[] nums) {
        if (nums.length == 0)return 0;
        int minLen = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;
        for (int i = 0;i<nums.length;i++){
            sum += nums[i];
            while (sum >= s){
                minLen = Math.min(minLen, i-left+1);
                sum -= nums[left];
                left++;
            }
        }
        if (minLen == Integer.MAX_VALUE)return 0;
        return minLen;
    }
}
