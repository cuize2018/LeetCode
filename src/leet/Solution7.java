package leet;

import org.omg.PortableInterceptor.INACTIVE;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class Solution7 {
    public static void main(String[] args){
        int a = 1534236469;
        System.out.println(reverse1(a));
    }

    public static int reverse1(int x) {
        String str = Integer.toString(x);
        StringBuilder str_b = new StringBuilder();

        if (str.charAt(0) == '-'){
            str_b.append('-');
        }
        for (int i = str.length()-1; i>=0; i--){
            if (str.charAt(i) != '-') {
                char c = str.charAt(i);
                str_b.append(c);
            }
        }
        String out = str_b.toString();
        int val;
        try {
            val = Integer.parseInt(out);
        }
        catch (NumberFormatException e){
            val = 0;
        }

        return val;
    }
}


