package leet;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 */
public class Solution50 {
    public static void main(String[] args){
        double x = -1;
        int m = -2147483647;

        System.out.println(myPow(x,-2147483648));
    }

    /**
     * 递归调用
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n){
        double out = 0;
        int m = n;
        if (n < 0){
            if (m == Integer.MIN_VALUE){
                m = Integer.MAX_VALUE;
            }else {
                m = -m;
            }
            x = 1 /  x;
        }

        out = fastPow(x,m);
        if (n == Integer.MIN_VALUE){
            out = out*x;
        }
        return out;
    }

    static  double fastPow(double x, int n) {
        double out = x;
        if (n == 0) return 1;

        double s = myPow(x, n/2);
        if (n % 2 == 0){
            out = s*s;
        }
        else {
            out = s*s*x;
        }
        return out;
    }

}
