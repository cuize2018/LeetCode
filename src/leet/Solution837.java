package leet;

public class Solution837 {
    public static void main(String[] args) {

    }

    /**
     * 令 dp[x] 表示从得分为 x 的情况开始游戏并且获胜的概率，目标是求 dp[0] 的值。
     * 根据规则，当分数达到或超过 K 时游戏结束，游戏结束时，如果分数不超过 N 则获胜，如果分数超过 N 则失败
     * 因此当K ≤ x ≤ min(N,K+W−1) 时有 dp[x]=1, 当x > min(N,K+W−1) 时有dp[x]=0。
     *
     * 为什么分界线是min(N,K+W−1)？首先，只有在分数不超过 N 时才算获胜；
     * 其次，可以达到的最大分数为 K+W-1，即在最后一次抽取数字之前的分数为 K-1，并且抽到了 W。
     *
     * 当0 ≤ x < K 时，如何计算 dp[x] 的值？注意到每次在范围 [1, W] 内随机抽取一个整数，且每个整数被抽取到的概率相等，因此可以得到如下状态转移方程：
     * dp[x] = (dp[x+1] + dp[x+2] + ⋯ + dp[x+W]) / W
     * ​
     * 考虑对 dp 的相邻项计算差分，有如下结果：
     * dp[x] − dp[x+1] = (dp[x+1] − dp[x+W+1]) / W, | 0 ≤ x < K−1
     * 新的方程：
     * ​dp[x] = dp[x+1] − (dp[x+W+1] − dp[x+1]) / W
     * ​注意到上述状态转移方程中 x 的取值范围，当 x = K-1 时不适用。因此对于 dp[K-1] 的值，需要通过
     * dp[K−1] = (dp[K]+dp[K+1]+⋯+dp[K+W−1])/W
     * 注意到只有当 K ≤ x ≤ min(N,K+W−1) 时才有 dp[x] = 1，因此:
     * dp[K−1]= (min(N,K+W−1)−K+1) / W = min(N−K+1,W)/W
     *
     * @param N
     * @param K
     * @param W
     * @return
     */
    public static double new21Game(int N, int K, int W) {
        if (K == 0) {
            return 1.0;
        }

        double[] dp = new double[K + W];
        for (int i = K; i <= N && i < K + W; i++) {
            dp[i] = 1.0;
        }

        dp[K - 1] = 1.0 * Math.min(W, N - K + 1) / W;
        for (int i = K - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] - (dp[i + W + 1] - dp[i + 1]) / W;
        }
        return dp[0];
    }
}
