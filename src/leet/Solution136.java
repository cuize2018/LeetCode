package leet;

import java.util.Arrays;

public class Solution136 {
    public static void main(String[] args) {

    }

    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = nums[nums.length-1];
        for (int i = 0; i <= nums.length-2;) {
            if (nums[i] != nums[i+1]){
                res = nums[i];
                break;
            }
            else {
                i+=2;
            }
        }
        return res;
    }

    /**
     * 异或满足交换律和结合律，所以最终所有相等的元素异或为0，仅存最后一个不同的元素
     * @param nums
     * @return
     */
    public static int singleNumber2(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans = ans ^ nums[i];
        }
        return ans;
    }
}
