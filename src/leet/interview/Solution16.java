package leet.interview;

public class Solution16 {
    public static void main(String[] args) {

    }

    /**
     * 假定我们已经得到了 x ^ {n / 2}的结果，并且我们现在想得到 x ^ n的结果。
     * 我们令 A 是 x ^ {n / 2}的结果，我们可以根据 n 的奇偶性来分别讨论 x ^ n的值。
     * 如果 n 为偶数，我们可以用公式 (x ^ n) ^ 2 = x ^ {2 * n}来得到 x ^ n = A∗A。
     * 如果 n 为奇数，那么 A * A = x ^ {n - 1}直观上看，我们需要再乘一次 x ，即 x ^ n = A * A * x该方法可以很方便的使用递归实现。
     * 我们称这种方法为 "快速幂"，因为我们只需最多O(logn) 次运算来得到 x ^ n
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        return fastPow(x, n);
    }

    private static double fastPow(double x, int n) {
        if (n == 0) return 1D;

        double half = fastPow(x, n / 2);

        if ((n & 1) == 1) return half * half * x;
        return half * half;
    }
}
