package leet;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 121
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * <p>
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 */
public class Solution9 {
    public static void main(String[] args) {
        int num = 1000000001;
        System.out.println(isPalindrome2(num));
    }

    public static boolean isPalindrome(int x) {
        String str = Integer.toString(x);
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;

    }

    /**
     * isPalindromeWithoutString,不使用字符串
     *
     * @param x 输入整数
     * @return 布尔值判断是否是回文数
     */
    public static boolean isPalindromeWithoutString(int x) {
        List<Integer> list = new ArrayList<>();

        int val = x;
        if (val < 0) return false;
        while (val != 0) {
            list.add(val % 10);
            val = val / 10;
        }
        list.add(val);

        for (int i = 0; i < list.size() / 2; i++) {
            if (!list.get(i).equals(list.get(list.size() - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome2(int x) {
        if (x < 0)return false;
        if (x == 0)return  true;
        long m = 10;
        long k = 0;
        long newNum = 0;
        while (x % m != x) {
            m *= 10;
        }
        m = m / 10;

        long temp = x;
        while (temp != 0) {
            long a = temp / m;
            newNum += a * Math.pow(10, k++);

            temp = temp % m;
            m = m / 10;
        }
        return (newNum ^ x) == 0;
    }
}
