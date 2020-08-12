package leet;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class Solution5 {
    public static void main(String[] args) {
        String s = "aaaa";
        String out = longestPalindrome2(s);
        System.out.println(out);
    }

    /**
     * 中心扩展法， 因为回文子串可能为奇数也可能为偶数，所以有 2n-1个中心
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int len = s.length();

        int start = 0;
        int end = 0;
        //每次循环选择一个中心
        for (int i = 0; i < len; i++) {
            int len1 = ExpandFromMiddle(s, i, i);//中心为i
            int len2 = ExpandFromMiddle(s, i, i + 1);//中心为i和i+1之间
            int len_final = Math.max(len1, len2);

            if (len_final > (end - start + 1)) {
                start = i - (len_final - 1) / 2;
                end = i + len_final / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int ExpandFromMiddle(String s, int left, int right) {
        int l = left;
        int r = right;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    public static String longestPalindrome2(String s) {
        int len = s.length();
        if (len == 0)return "";
        int start = 0 ;
        int end = 0;
        for (int i = 0; i < len; i++) {
            int a = expand2(s, i, i);
            int b = expand2(s, i, i+1);
            int max = Math.max(a,b);
            if (max > end-start+1){
                start = i - (max-1)/2;
                end = i + max/2;
            }
        }
        return s.substring(start, end+1);

    }

    private static int expand2(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right - left - 1;
    }
}




