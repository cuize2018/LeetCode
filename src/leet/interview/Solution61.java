package leet.interview;

import java.util.Arrays;

public class Solution61 {
    public static void main(String[] args) {
        int[] nums = {0,0,2,2,5};
        System.out.println(isStraight(nums));
    }

    public static boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int zeroNums;
        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] != 0)break;
        }
        zeroNums = i;
        while (i < nums.length-1){
            if (nums[i]+1 != nums[i+1]){
                int dis = nums[i+1] - nums[i] -1;
                if (dis <= zeroNums){
                    zeroNums -= dis;
                }
                else return false;
            }
            i++;
        }
        return true;
    }
}
