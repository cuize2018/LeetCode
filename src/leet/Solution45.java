package leet;

import javax.swing.plaf.IconUIResource;

public class Solution45 {
    public static void main(String[] args) {
        int[] a = {2, 3, 1, 1, 4};
        int[] b = {5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0};
        System.out.println(jump4(b));
    }

    public static int jump(int[] nums) {
        int length = nums.length;
        if (length == 0) return 0;

        int[] dp = new int[length];
        dp[0] = 0;

        for (int i = 1; i < length; i++) {
            int min = dp[i - 1] + 1;
            for (int j = i - 2; j >= 0; j--) {
                if (nums[j] >= i - j) {
                    min = Math.min(dp[j] + 1, min);
                }
            }
            dp[i] = min;
        }
        return dp[length - 1];
    }

    /**
     * 贪婪算法，我们每次在可跳范围内选择可以使得跳的更远的位置。
     * exp：[2,3,1,1,4,2,1,...]
     * 开始的位置是 2，可跳的范围是 [1,2] 。然后因为 在索引 1 处的数值 3 范围为索引[2,4]最大，所以跳到 3 的位置。
     * 然后现在的位置就是 3 了，能跳的范围是[2,4]，然后因为在索引 4 处， 范围为[5,8]最大，所以下次跳到 4 的位置。
     *
     * @param nums
     * @return
     */
    public static int jump2(int[] nums) {
        if (nums.length == 0) return 0;

        int index = 0;
        int count = 0;
        while (index != nums.length - 1) {
            int start = index + 1;
            int end = index + nums[index];

            if (end >= nums.length - 1) {
                count++;
                break;
            }

            int max = 0;
            for (int i = start; i <= end; i++) {
                if (nums[i] + i >= max) {
                    max = nums[i] + i;
                    index = i;
                }
            }
            count++;
        }
        return count;
    }

    public static int jump4(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return 0;

        int i = 0;
        int res = 0;
        while (i < nums.length) {
            int start = i + 1;
            int end = i + nums[i];

            if (end >= nums.length - 1) {
                res++;
                break;
            }

            int max = -1;
            for (int j = start; j <= end; j++) {
                if (j + nums[j] >= max) {
                    i = j;
                    max = j + nums[j];
                }
            }
            res++;
        }
        return res;
    }

}
