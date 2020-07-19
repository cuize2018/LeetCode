package leet;

public class Solution494 {
    public static void main(String[] args) {
        int[] nums = {0,0,0,0,0,0,0,0,1};
        int S = 1;
        System.out.println(findTargetSumWays(nums, S));
    }

    /**
     * 状态定义：dp[i][j]为前i+1个数字的和达到j的总方法数,dp[0][j]，为前1个数字达到和为j的总方法数
     *
     * 由于每位数字可取正取负，假如和的范围为[-1000,1000](题目给出和不超过1000),
     * 将其平移至[0,2000]，以方便处理，因为数组下标必须大于0
     *
     *状态转移方程
     * j-nums[i] >= 0时，dp[i][j] += dp[i-1][j-nums[i]];
     * j+nums[i] <= 2000时，dp[i][j] += dp[i-1][j+nums[i]];
     *
     * @param nums
     * @param S
     * @return
     */
    public static int findTargetSumWays(int[] nums, int S) {
        if (S > 1000)return 0;

        int len = nums.length;
        int[][] dp = new int[len][1000*2+1];

        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;

        for (int i = 1;i<dp.length;i++){
            for (int j = -1000;j <= 1000; j++){
                if(j + 1000 - nums[i] >= 0)
                    dp[i][j+1000] += dp[i-1][j-nums[i]+1000];
                if(j + 1000 + nums[i] <= 2000)
                    dp[i][j+1000] += dp[i-1][j+nums[i]+1000];
            }
        }

        return dp[nums.length-1][S + 1000];
    }

    public static int findTargetSumWays2(int[] nums, int S) {
        if (nums.length == 0 && S == 0)return 1;
        if (nums.length == 0 )return 0;
        if (S > 1000)return 0;

        int[][] dp = new int[nums.length][2*1000+1];
        dp[0][nums[0] + 1000] += 1;
        dp[0][-nums[0] + 1000] += 1;

        for (int i = 1; i < nums.length; i++) {
            for (int j = -1000; j <= 1000; j++) {
                if (j + 1000 - nums[i] >= 0) {
                    dp[i][j + 1000] += dp[i - 1][j - nums[i] + 1000];
                }
                if (j + 1000 + nums[i] <= 2000) {
                    dp[i][j + 1000] += dp[i - 1][j + nums[i] + 1000];
                }
            }
        }
        return dp[nums.length-1][S + 1000];
    }
}
