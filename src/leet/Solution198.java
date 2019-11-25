package leet;

public class Solution198 {
    public static void main(String[] args){
        int[] a = {1,2,3,1};
        System.out.println(rob(a));
    }

    /**
     * 考虑所有可能的抢劫方案过于困难。一个自然而然的想法是首先从最简单的情况开始。记：
     *
     * f(k) = 从前 k 个房屋中能抢劫到的最大数额，A_i= 第 i 个房屋的钱数。
     *
     * 首先看 n = 1 的情况，显然 f(1) = A_1。
     *
     * 再看 n = 2，f(2) = max(A_1,A_2)。
     *
     * 对于 n = 3，有两个选项:
     *
     * 抢第三个房子，将数额与第一个房子相加。
     *
     * 不抢第三个房子，保持现有最大数额。
     *
     * 显然，你想选择数额更大的选项。于是，可以总结出公式：
     *
     * f(k) = max(f(k – 2) + A_k, f(k – 1))
     *
     * 我们选择 f(–1) = f(0) = 0 为初始情况，这将极大地简化代码。
     *
     * 答案为 f(n)。可以用一个数组来存储并计算结果。不过由于每一步你只需要前两个最大值，两个变量就足够用了。
     */

    /**
     * 由于不可以在相邻的房屋闯入，所以在当前位置 n 房屋可盗窃的最大值，
     * 若不盗窃n房屋，则结果就是 n-1 房屋可盗窃的最大值，
     * 若盗窃n房屋，则结果就是 n-2 房屋可盗窃的最大值加上当前房屋的值，
     * 二者之间取最大值
     */
    public static int rob(int[] nums) {
        if (nums.length == 0)return 0;
        int n = nums.length;
        int[] dp = new int[n];

        for (int i = 0;i<n;i++){
            if (i == 0)dp[i] = nums[i];
            else if (i == 1)dp[i] = Math.max(nums[i-1], nums[i]);
            else dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
        }
        return dp[n-1];
    }
}
