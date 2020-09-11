package leet;

public class Solution486 {

    public static void main(String[] args) {
        int[] nums = {1, 5, 2};

        Solution486 sol = new Solution486();
        System.out.println(sol.PredictTheWinner(nums));
    }

    public boolean PredictTheWinner(int[] nums) {

        return helper(nums, 0, nums.length - 1, 0, 0, true);
    }

    //分别为nums数组, 左边可取的位置，右边可取的位置，A的得分,B的得分,轮到谁选牌
    public boolean helper(int[] nums, int left, int right, int player1, int player2, boolean isPlayer1) {
        if (left > right) {//左右越界，没有牌了，比较得分，判断胜负（以A为主角）
            return player1 >= player2;
        }

        if (isPlayer1) {//轮到A选牌,A是主角，只要左边或者右边有一种必胜情况，就说明可以必胜
            return helper(nums, left + 1, right, player1 + nums[left], player2, false) ||
                    helper(nums, left, right - 1, player1 + nums[right], player2, false);
        } else { //轮到B选牌，不管B怎么选，此时只有左右两边都保证A是必胜的，才能保证A最终必胜！
            return helper(nums, left + 1, right, player1, player2 + nums[left], true) &&
                    helper(nums, left, right - 1, player1, player2 + nums[right], true);
        }
    }

    /**
     * dp[i][i]意味着仅剩一个分数，这时当前轮次玩家的相对得分即为nums[i]
     * 甲先拿一个分数，如果是nums[i]，那么甲对乙的相对分数为nums[i]-dp[i+1][j]，即本轮分数-乙对甲的相对分数
     * 如果先拿的分数是nums[j]，那么甲对乙的相对分数为nums[j]-dp[i][j-1]
     *
     * 因此dp[i][j]就等于上面2、3两种情况的最大值，因为每个人都能作出最优决策。
     * dp数组定义成功，接下来就是填表，最后我们需要判断在区间0到n-1中，先手的相对得分是否大于等于0。
     * @param nums
     * @return
     */
    public static boolean PredictTheWinner2(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];//dp[i][j] 当前玩家在数组[i:j]中先手，赢过对方的分数。

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = nums[i];
            for (int j = i + 1; j < n; j++) {
                //用当前选择的分数，减去，接下来对手赢过自己的分数（剩余数组的递归结果）
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }
}
