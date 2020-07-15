package leet;

public class Solution96 {
    public static void main(String[] args) {
        int n = 6;
        System.out.println(numTrees(n));
    }

    /**
     * 动态规划
     * <p>
     * 假设n个节点存在
     * <p>
     * 令G(n)的从1到n可以形成二叉排序树个数
     * <p>
     * 令f(i)为以i为根的二叉搜索树的个数
     * <p>
     * 即有:G(n) = f(1) + f(2) + f(3) + f(4) + ... + f(n)
     * <p>
     * n为根节点，当i为根节点时，其左子树节点个数为[1,2,3,...,i-1]，右子树节点个数为[i+1,i+2,...n]，所以当i为根节点时，
     * 其左子树节点个数为i-1个，右子树节点为n-i，即f(i) = G(i-1)*G(n-i),这是由于 G(n)和序列的内容无关，只和序列的长度有关
     * <p>
     * 上面两式可得:G(n) = G(0)*G(n-1)+G(1)*(n-2)+...+G(n-1)*G(0)
     * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees/solution/dong-tai-gui-hua-by-powcai-4/
     *
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        int[] nums = new int[n + 1];
        nums[0] = 1;
        nums[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                nums[i] += nums[j] * nums[i - (j + 1)];
            }
        }
        return nums[n];
    }

    public static int numTrees2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }


}
