package leet;

public class Solution740 {
    public static void main(String[] args) {

    }

    public static int deleteAndEarn(int[] nums) {
        if (nums.length == 0) return 0;

        int max = -1;//找出最大值
        for (int num : nums) {
            max = Math.max(max, num);
        }

        int[] count = new int[max + 1];//按数字大小统计出现次数，第i个数和第i-1个数具有天然的+1性能，所以可以省去很多判断。
        for (int num : nums) {
            count[num]++;
        }

        int[][] dp = new int[max + 1][2];//dp[i][0]表示第i个数不拿能获得的最大点数；dp[i][1]表示第i个数拿能获得的最大点数
        for (int i = 1; i <= max; i++) {
            dp[i][1] = dp[i - 1][0] + i * count[i];
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        }
        return Math.max(dp[max][1], dp[max][0]);
    }
}
