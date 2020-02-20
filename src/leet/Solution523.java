package leet;

public class Solution523 {
    public static void main(String[] args) {
         int[] nums = {2,3,4,5};
         int k = 18;
        System.out.println(checkSubarraySum(nums,k));
    }

    /**
     * 动态规划
     * @param nums
     * @param k
     * @return
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        int[] dp = new int[nums.length];//dp[j]代表到j为止的数字的和

        dp[0] = nums[0];//首先起点从i=0开始

        //初始化[0,j]的每个dp[j]
        for (int j = 1;j<nums.length;j++){
            dp[j] = dp[j-1] + nums[j];
            if (k != 0) {
                if (dp[j] % k == 0) {
                    return true;
                }
            }
            else{
                if (dp[j] == 0)return true;
            }
        }

        //起点i从i=1开始遍历
        for (int i = 1;i<nums.length-1;i++){
            //因为是连续的子数组，所以j > i
            for (int j = i+1;j<nums.length;j++){
                dp[j] = dp[j] - nums[i-1];//dp[i][j] = dp[i-1][j] - nums[i-1],
                                          //即从i到j的和为从i-1到j的和减去nums[i-1]
                                          //exp: {1,2,3,4}, dp[2][3] = dp[1][3] - 1 -> 2+3 = 1+2+3 - 1
                if (k != 0) {
                    if (dp[j] % k == 0) {
                        return true;
                    }
                }
                else {
                    if (dp[j] == 0)return true;
                }
            }
        }
        return false;
    }
}
