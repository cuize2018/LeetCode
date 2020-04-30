package leet.interview;

public class Solution49 {
    public static void main(String[] args) {

    }

    /**
     * pi的含义是有资格同i相乘的最小丑数的位置。
     * 这里资格指的是：如果一个丑数nums[pi]通过乘以i可以得到下一个丑数，那么这个丑数nums[pi]就永远失去了同i相乘的资格（没有必要再乘了），
     * 我们把pi++让nums[pi]指向下一个丑数即可。
     *
     * exp:
     * 一开始，丑数只有{1}，1可以同2，3，5相乘，取最小的1×2=2添加到丑数序列中。
     * 现在丑数中有{1，2}，在上一步中，1已经同2相乘过了，所以今后没必要再比较1×2了，我们说1失去了同2相乘的资格。
     * 现在1有与3，5相乘的资格，2有与2，3，5相乘的资格，
     * 但是2×3和2×5是没必要比较的，因为有比它更小的1可以同3，5相乘，所以我们只需要比较1×3，1×5，2×2。
     *
     * "依此类推，每次我们都分别比较有资格同2，3，5相乘的最小丑数，选择最小的那个作为下一个丑数，"
     * "假设选择到的这个丑数是同i（i = 2，3，5）相乘得到的，所以它失去了同i相乘的资格，把对应的pi++，让pi指向下一个丑数即可。"
     *
     * @param n
     * @return
     */
    public static int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;

        int idx2 = 0;//idx[i]的含义是有资格同i相乘的最小丑数的位置。
        int idx3 = 0;
        int idx5 = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(Math.min(2 * dp[idx2], 3 * dp[idx3]), 5 * dp[idx5]);
            if (dp[i] == 2 * dp[idx2]) idx2++;
            if (dp[i] == 3 * dp[idx3]) idx3++;
            if (dp[i] == 5 * dp[idx5]) idx5++;
        }
        return dp[n - 1];
    }
}
