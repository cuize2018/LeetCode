package leet;

import javax.swing.*;
import java.util.HashMap;

/**
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 *
 * 示例 1:
 *
 * 输入: "III"
 * 输出: 3
 * 示例 2:
 *
 * 输入: "IV"
 * 输出: 4
 * 示例 3:
 *
 * 输入: "IX"
 * 输出: 9
 * 示例 4:
 *
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例 5:
 *
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class Solution13 {
    public static void main(String[] args){
        String s = "II";
        System.out.println(romanToInt2(s));
    }

    /**
     * 自己的，速度较慢
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        HashMap<String, Integer> hmap = new HashMap<>();
        for (int i = 0;i<nums.length;i++){
            hmap.put(romans[i],nums[i]);
        }

        int num = 0;
        for (int i = 0;i<s.length();i++){
            String c = s.substring(i,i+1);
            String n = "";
            if (i+2 <= s.length()) {
                n = s.substring(i+1,i+2);
            }

            if (hmap.containsKey(c+n)){
                num += hmap.get(c+n);
                i++;
            }
            else {
                num += hmap.get(c);
            }
        }
        return num;
    }

    /**
     * 判断后一个数字是否比前一个数字大，若是，则用后一个数字减去前一个
     * @param s
     * @return
     */
    public static int romanToInt2(String s) {
        int num = 0;
        HashMap<Character, Integer> romaNumber = new HashMap<>();
        romaNumber.put('I', 1);
        romaNumber.put('V', 5);
        romaNumber.put('X', 10);
        romaNumber.put('L', 50);
        romaNumber.put('C', 100);
        romaNumber.put('D', 500);
        romaNumber.put('M', 1000);

        for (int i = 0;i<s.length();i++){
            Character c = s.charAt(i);
            Character n = '0';
            if (i+1 < s.length()){
                n = s.charAt(i+1);
            }

            if (n!='0' && (romaNumber.get(n) > romaNumber.get(c))){
                num += romaNumber.get(n) - romaNumber.get(c);
                i++;
            }
            else {
                num += romaNumber.get(c);
            }
        }
        return num;

    }
}
