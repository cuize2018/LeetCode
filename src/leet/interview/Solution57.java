package leet.interview;

import java.util.HashMap;
import java.util.Map;

public class Solution57 {
    public static void main(String[] args) {

    }

    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (num < target && map.containsKey(target - num)) {
                res[0] = num;
                res[1] = target - num;
                return res;
            }
            map.put(num, target - num);
        }
        return res;
    }

    public static int[] twoSum2(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;

        int sum = nums[left] + nums[right];
        while (left < right && sum != target){
            if (sum > target){
                right--;
            }
            else {
                left++;
            }
            sum = nums[left] + nums[right];
        }
        return new int[]{nums[left], nums[right]};
    }
}
