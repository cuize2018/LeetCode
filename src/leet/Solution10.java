package leet;

public class Solution10 {
    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b";
        System.out.println(isMatch2(s, p));
    }

    /**
     * 回溯法
     */
    public static boolean isMatch(String s, String p) {
        if (p.length() == 0) return s.length() == 0;

        boolean firstMatch = s.length() != 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');

        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2))//此为第一个元素不匹配且第二个元素为'*'，此时'*'代表为0的情况
                    || (firstMatch && isMatch(s.substring(1), p));//此为第一个元素匹配， 此时'*'代表为多个前一元素的情况
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));//当p的长度小于2时只用检测第一个元素即可；
            //当第二个元素不为'*'时, 此时需要第一个元素匹配，且剩下的p和s都匹配
        }
    }

    public static boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;//dp[i][j] 表示 s 的前 i 个是否能被 p 的前 j 个匹配
        for (int j = 0; j < p.length(); j++) { // here's the p's length, not s's
            if (p.charAt(j) == '*' && dp[0][j - 1]) {
                dp[0][j + 1] = true; // here's y axis should be j+1
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {//如果是任意元素 或者是对于元素匹配
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {//如果前一个元素不匹配 且不为任意元素
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                            /*
                            dp[i][j] = dp[i-1][j] // 多个字符匹配的情况
                            or dp[i][j] = dp[i][j-1] // 单个字符匹配的情况
                            or dp[i][j] = dp[i][j-2] // 没有匹配的情况
                             */
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public static boolean isMatch3(String s, String p) {
        int len1 = s.length();
        int len2 = p.length();

        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        for (int j = 0; j < len2; j++) {
            if (p.charAt(j) == '*' && dp[0][j - 1]) {
                dp[0][j + 1] = true;
            }
        }

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                else if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        dp[i + 1][j + 1] =
                                dp[i + 1][j - 1]    // 0个匹配
                                || dp[i + 1][j]     // 1个匹配
                                || dp[i][j + 1];    // 多个匹配
                    }
                }
            }
        }
        return dp[len1][len2];
    }

}
