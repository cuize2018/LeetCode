package leet;

import java.util.Arrays;

/**
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 *
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 *
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *
 * 说明:
 *
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 */
public class Solution41 {
    public static void main(String[] args){
        int[] a = {5,6,8,7};
        System.out.println(firstMissingPositive(a));
    }

    public static int firstMissingPositive(int[] nums) {
        int out = 1;
        if (nums.length == 0)return out;
        Arrays.sort(nums);
        for (int i = 0;i<nums.length;i++){
            if (nums[i] > 0){
               int k = 1;
               int last = -1;
               for (int j = i;j<nums.length;j++){
                   if (nums[j] != last && nums[j] != k){
                       break;
                   }
                   else{
                       if (nums[j] != last) {
                           k++;
                       }
                       last = nums[j];
                   }
               }
               out = k;
               break;
            }
        }
        return out;
    }
}
