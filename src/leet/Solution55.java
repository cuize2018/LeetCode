package leet;

import java.util.Arrays;
import java.util.Map;

public class Solution55 {
    public static void main(String[] args) {
        int[] a = {2, 3, 1, 1, 4};
        int[] b = {3, 2, 1, 0, 4};
        int[] c = {1, 1, 2, 2, 0, 1, 1};
        int[] d = {5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0};
        int[] e = {8, 2, 4, 4, 4, 9, 5, 2, 5, 8, 8, 0, 8, 6, 9, 1, 1, 6, 3, 5, 1, 2, 6, 6, 0, 4, 8, 6, 0, 3, 2, 8, 7, 6, 5, 1, 7, 0, 3, 4, 8, 3, 5, 9, 0, 4, 0, 1, 0, 5, 9, 2, 0, 7, 0, 2, 1, 0, 8, 2, 5, 1, 2, 3, 9, 7, 4, 7, 0, 0, 1, 8, 5, 6, 7, 5, 1, 9, 9, 3, 5, 0, 7, 5};
        System.out.println(canJump(e));
    }

    public static boolean canJump(int[] nums) {
        int last_idx = nums.length - 1;
        int curr_idx = 0;
        int last_time_idx = 0;
        int[] tmp = Arrays.copyOf(nums, nums.length);

        while (curr_idx < last_idx && nums[curr_idx] != 0) {
            int next_idx = curr_idx + nums[curr_idx];

            if (next_idx >= last_idx) {
                return true;
            }

            if (nums[next_idx] > 0) {
                last_time_idx = curr_idx;
                curr_idx = next_idx;
            } else {
                if (nums[last_time_idx] <= 1) {
                    nums[curr_idx]--;
                    // last_time_idx = curr_idx;
                } else {
                    nums[last_time_idx]--;
                    curr_idx = last_time_idx;
                }
            }

            if (nums[curr_idx] == 0 && tmp[curr_idx + tmp[curr_idx]] != 0) {//此时，说明curr_idx位置无论如何减小都无法的到达终点，
                // 所以恢复原始值，强制到达下一个最大位置为起点，重新开始寻找
                nums[curr_idx] = tmp[curr_idx];
                curr_idx += nums[curr_idx];
                last_time_idx = curr_idx;
            }
        }

        if (curr_idx >= last_idx) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean canJump2(int[] nums) {
        int n = nums.length;
        if (n == 0) return true;

        boolean[] dp = new boolean[nums.length];
        dp[n - 1] = true;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] >= n - 1 - i) {
                dp[i] = true;
            } else {
                for (int j = i + 1; j <= Math.min(i + nums[i], n - 1); j++) {
                    if (dp[j]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[0];
    }

    public static boolean canJump3(int[] nums) {
        if (nums.length == 0) return true;
        int max = nums[0];

        for (int j = 1; j < nums.length; j++) {
            if (j > max) break;
            max = Math.max(max, j + nums[j]);
        }
        return max >= nums.length - 1;
    }
}
