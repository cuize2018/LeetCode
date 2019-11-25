package leet;

/**
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
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
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 *
 * 示例 1:
 *
 * 输入: 3
 * 输出: "III"
 * 示例 2:
 *
 * 输入: 4
 * 输出: "IV"
 * 示例 3:
 *
 * 输入: 9
 * 输出: "IX"
 * 示例 4:
 *
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * 示例 5:
 *
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class Solution12 {
    public static void main(String[] args){
        int num = 1994;
        System.out.println(intToRoman2(num));
    }

    /**
     * 暴力求解
     * @param num
     * @return
     */
    public static String intToRoman(int num){
           String s = Integer.toString(num);
           StringBuilder str_out = new StringBuilder();
           int count = s.length()-1;
           for (int j = 0;j<s.length();j++){
                int val = Integer.parseInt(s.substring(j,j+1));
                if (count == 0) {
                    if (val < 4) {
                        for (int i = 0; i < val; i++) {
                            str_out.append("I");
                        }
                    } else if (val == 4) {
                        str_out.append("IV");
                    } else if (val == 5) {
                        str_out.append("V");
                    } else if (val > 5 && val < 9){
                        str_out.append("V");
                        for (int i = 0;i<val-5;i++){
                            str_out.append("I");
                        }
                    } else if (val == 9){
                        str_out.append("IX");
                    }
                }
                else if (count == 1){
                    if (val < 4) {
                        for (int i = 0; i < val; i++) {
                            str_out.append("X");
                        }
                    } else if (val == 4) {
                        str_out.append("XL");
                    } else if (val == 5) {
                        str_out.append("L");
                    } else if (val > 5 && val < 9){
                        str_out.append("L");
                        for (int i = 0;i<val-5;i++){
                            str_out.append("X");
                        }
                    } else if (val == 9){
                        str_out.append("XC");
                    }
                }
                else if (count == 2){
                    if (val < 4) {
                        for (int i = 0; i < val; i++) {
                            str_out.append("C");
                        }
                    } else if (val == 4) {
                        str_out.append("CD");
                    } else if (val == 5) {
                        str_out.append("D");
                    } else if (val > 5 && val < 9){
                        str_out.append("D");
                        for (int i = 0;i<val-5;i++){
                            str_out.append("C");
                        }
                    } else if (val == 9){
                        str_out.append("CM");
                    }
                }
                else if (count == 3){
                    for (int i = 0; i < val; i++) {
                        str_out.append("M");
                    }
                }
                count--;
           }
           return str_out.toString();
    }

    /**
     * 贪心算法
     * @param num
     * @return
     */
    public static String intToRoman2(int num){
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder str = new StringBuilder();

        int index = 0;
        while (index < nums.length){
            while (num >= nums[index]){
                str.append(romans[index]);
                num -= nums[index];
            }
            index++;
        }
        return str.toString();
    }
}
