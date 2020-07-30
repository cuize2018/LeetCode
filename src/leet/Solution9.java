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
        System.out.println(isPalindrome(num));
    }

    public static boolean isPalindrome(int x){
        if (x < 0)return false;
        if (x < 10)return true;

        int div = 1;
        while (x / div >= 10){
            div *= 10;
        }

        while (x > 0){
            int left = x /div;
            int right = x % 10;
            if (left != right)return false;

            x = (x % div)/10;
            div = div / 100;
        }
        return true;
    }
}
