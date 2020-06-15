package leet;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 */
public class Solution14 {
    public static void main(String[] args) {
        String[] a = {"dog", "racecar", "car"};
        System.out.println(longestCommonPrefix2(a));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        int num = strs.length;
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < num; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return str.toString();
                }
            }
            str.append(c);
        }
        return str.toString();
    }


    public static String longestCommonPrefix2(String[] strs) {
        StringBuilder res = new StringBuilder();
        if (strs.length == 0) return res.toString();
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }

        for (int i = 0; i < minLen; i++) {
            char c = strs[0].charAt(i);
            boolean flag = true;
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != c) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res.append(c);
            } else {
                return res.toString();
            }
        }
        return res.toString();
    }

}
