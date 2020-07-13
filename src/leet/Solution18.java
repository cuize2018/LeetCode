package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution18 {
    public static void main(String[] args) {
        int[] nums = {0, -1, -3, 5, -5};
        int target = 1;
        System.out.println(fourSum(nums, target));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (right < nums.length - 1 && nums[right] == nums[right + 1] || sum > target) {
                        right--;
                    } else if (left > j + 1 && nums[left] == nums[left - 1] || sum < target) {
                        left++;
                    } else {
                        List<Integer> one = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        res.add(one);
                        left++;
                        right--;
                    }
                }
            }
        }
        return res;
    }
}
