package leet.interview;

import java.math.BigInteger;

public class Solution14_II {
    public static void main(String[] args) {
        int n = 120;
        System.out.println(cuttingRope(n));
    }

    /**
     * 贪心
     * 我们首先考虑对于一段长n的绳子，我们可以切出的结果包含什么？
     *
     * 1 会包含吗？ 不会，因为1 * (k - 1) < k, 只要把1和任何一个其他的片段组合在一起就有个更大的值
     * 2 可以
     * 3 可以
     * 4 可以吗？ 它拆成两个2的效果和本身一样，因此也不考虑
     * 5 以上可以吗？ 不可以，这些绳子必须拆，因为总有一种拆法比不拆更优，比如拆成 k / 2 和 k - k / 2
     *
     * 综上, 最后的结果只包含2和3(当然当总长度为2和3时单独处理), 那么很显然n >= 5时， 3*(n - 3) >= 2 * (n - 2) ，
     * 因此我们优先拆成3，最后剩余的拆成2。最后的结果一定是由若干个3和1或2个2组成.
     * @param n
     * @return
     */
    public static int cuttingRope(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;

        double mod = 1e9 + 7;
        double res = 1;
        while (n > 4) {
            res *= 3;
            res %= mod;
            n -= 3;
        }
        return (int)((res * n) % mod);
    }
}
