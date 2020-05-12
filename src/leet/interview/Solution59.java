package leet.interview;

import java.util.Arrays;

public class Solution59 {
    public static void main(String[] args) {

    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return new int[0];

        int[] res = new int[n - k + 1];
        Arrays.fill(res, Integer.MIN_VALUE);

        for (int l = 0; l < k; l++) {
            res[0] = Math.max(res[0], nums[l]);
        }

        int j = 1;
        for (int i = 1; i <= n - k; i++) {
            if (nums[i - 1] == res[j - 1]) {
                if (nums[i + k - 1] > res[j - 1]) res[j] = nums[i + k - 1];
                else {
                    for (int l = i; l < i + k; l++) {
                        res[j] = Math.max(res[j], nums[l]);
                    }
                }
            } else {
                res[j] = Math.max(nums[i + k - 1], res[j - 1]);
            }
            j++;
        }
        return res;
    }
}
