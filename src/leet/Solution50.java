package leet;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * <p>
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * <p>
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 * <p>
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 */
public class Solution50 {
    public static void main(String[] args) {
        double x = -1;
        int m = -2147483647;

        System.out.println(myPow(x, -2147483648));
    }

    /**
     * 假定我们已经得到了 x ^ {n / 2}的结果，并且我们现在想得到 x ^ n的结果。
     * 我们令 A 是 x ^ {n / 2}的结果，我们可以根据 n 的奇偶性来分别讨论 x ^ n的值。
     * 如果 n 为偶数，我们可以用公式 (x ^ n) ^ 2 = x ^ {2 * n}来得到 x ^ n = A∗A。
     * 如果 n 为奇数，那么 A * A = x ^ {n - 1}直观上看，我们需要再乘一次 x ，即 x ^ n = A * A * x该方法可以很方便的使用递归实现。
     * 我们称这种方法为 "快速幂"，因为我们只需最多O(logn) 次运算来得到 x ^ n
     *
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


    public static double myPow2(double x, int n) {
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        return help(x, n);
    }

    private static double help(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;


        double half = myPow2(x, n / 2);
        double res = half * half;

        if (n % 2 != 0) return res * x;
        return res;
    }


}
