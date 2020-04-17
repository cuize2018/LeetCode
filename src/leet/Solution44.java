package leet;

import java.util.HashMap;
import java.util.Map;

public class Solution44 {
    public static void main(String[] args) {
        String s = "aaaa";
        String p = "******a?";
        Solution44 solution44 = new Solution44();
        System.out.println(solution44.isMatch(s, p));
    }

    /**
     * 回溯法
     *
     * @param s
     * @param p
     * @return
     */
    Map<String, Boolean> map = new HashMap<>();

    public boolean isMatch(String s, String p) {
        p = p.replaceAll("\\*+", "*");
        if (p.equals(s)) return true;
        if (s.length() == 0 && p.length() == 0) return true;
        if (p.length() > 0 && p.equals("*")) return true;
        if (s.length() == 0 || p.length() == 0) return false;

        if (map.containsKey(s + "#" + p)) return map.get(s + "#" + p);
        boolean res = false;
        if (p.charAt(0) == '*') {
            char c = p.charAt(1);
            for (int i = -1; i < s.length(); i++) {
                if (i == -1) {
                    boolean match = isMatch(s, p.substring(1));
                    if (match) {
                        res = true;
                        break;
                    }
                } else if (c == '*' || c == '?' || s.charAt(i) == c) {
                    boolean match = isMatch(s.substring(i), p.substring(1));
                    if (match) {
                        res = true;
                        break;
                    }

                }
            }
        } else if (p.charAt(0) == '?' || p.charAt(0) == s.charAt(0)) {
            res = isMatch(s.substring(1), p.substring(1));
        }
        map.put(s + "#" + p, res);
        return res;
    }

    /**
     * （一）状态
     *      f[i][j]表示s1的前i个字符，和s2的前j个字符，能否匹配
     * （二）转移方程
     *      如果s1的第 i 个字符和s2的第 j 个字符相同，或者s2的第 j 个字符为 “.”
     *      f[i][j] = f[i - 1][j - 1]
     *      如果s2的第 j 个字符为 *
     *      若s2的第 j 个字符匹配空串, f[i][j] = f[i][j - 1]
     *      若s2的第 j 个字符匹配s1的第 i 个字符, f[i][j] = f[i - 1][j]
     *      这里注意不是 f[i - 1][j - 1]， 举个例子就明白了 (abc, a*) f[3][2] = f[2][2]
     * （三）初始化
     *      f[0][i] = f[0][i - 1] && s2[i] == *
     *      即s1的前0个字符和s2的前i个字符能否匹配
     * （四）结果
     *      f[m][n]
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = dp[0][j-1] && p.charAt(j-1)=='*';
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }

                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
