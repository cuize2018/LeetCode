package leet;

public class Solution264 {
    public static void main(String[] args){

    }
    /**
     * 丑数求解过程：首先除2，直到不能整除为止，然后除5到不能整除为止，然后除3直到不能整除为止。
     * 最终判断剩余的数字是否为1，如果是1则为丑数，否则不是丑数
     * <p>
     * 解题思路：
     * 从1开始遍历，按丑数求解过程找出满足条件的第n个丑数（提交超时）
     * 思路优化（如何利用之前的计算）
     * 解题二：动态规划+三指针
     * dp保存按序排列的丑数，三指针分别是*2，*3，*5，找出下一个丑数
     *
     * @param n
     * @return
     */
    public static int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;

        int idx_2 = 0;
        int idx_3 = 0;
        int idx_5 = 0;
        for (int i = 1;i<n;i++){
            dp[i] = Math.min(Math.min(2*dp[idx_2], 3*dp[idx_3]), 5*dp[idx_5]);
            if (dp[i] == 2*dp[idx_2])idx_2++;
            if (dp[i] == 3*dp[idx_3])idx_3++;
            if (dp[i] == 5*dp[idx_5])idx_5++;
        }

        return dp[n-1];
    }
}
