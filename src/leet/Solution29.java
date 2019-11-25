package leet;

import org.omg.CORBA.TRANSACTION_MODE;
import sun.nio.cs.ext.MacHebrew;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 示例 1:
 *
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 说明:
 *
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 */
public class Solution29 {
    public static void main(String[] args){
        int a = Integer.MIN_VALUE;int b = -1;
        System.out.println(divide(a,b));
    }

    public static int divide(int dividend, int divisor) {
        long out = 0;
        if (dividend == 0) return 0;
        if (dividend == divisor) return 1;
        boolean Positive;

        if ((dividend > 0 &&divisor > 0) || (dividend < 0 && divisor < 0))Positive= true;
        else Positive = false;

        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);

        while (a >= b) {
            long temp_b = b;
            int count = 1;
            while (a >= temp_b) {
                a = a - temp_b;
                out += count;
                temp_b = temp_b << 1;
                count = count << 1;
            }
        }
        if (!Positive) out = -out;

        if (out > Integer.MAX_VALUE)return Integer.MAX_VALUE;
        return (int)out;
    }

}
