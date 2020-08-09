package leet;

import java.util.HashMap;
import java.util.Map;

public class Solution516 {
    public static void main(String[] args) {
        String a = "abcabcabcabc";
        System.out.println(longestPalindromeSubseq(a));
    }

    /**
     * 我们说这个问题对 dp 数组的定义是：在子串 s[i..j] 中，最长回文子序列的长度为 dp[i][j]。一定要记住这个定义才能理解算法。
     * <p>
     * 为啥这个问题要这样定义二维的 dp 数组呢？
     * 我们前文多次提到，找状态转移需要归纳思维，说白了就是如何从已知的结果推出未知的部分，这样定义容易归纳，容易发现状态转移关系。
     * <p>
     * 具体来说，如果我们想求 dp[i][j]，假设你知道了子问题 dp[i+1][j-1] 的结果（s[i+1..j-1] 中最长回文子序列的长度），
     * 你是否能想办法算出 dp[i][j] 的值（s[i..j] 中，最长回文子序列的长度）呢？
     * <p>
     * 可以！这取决于 s[i] 和 s[j] 的字符：
     * 如果它俩相等，那么它俩加上 s[i+1..j-1] 中的最长回文子序列就是 s[i..j] 的最长回文子序列：
     * 如果它俩不相等，说明它俩不可能同时出现在 s[i..j] 的最长回文子序列中，
     * 那么把它俩分别加入 s[i+1..j-1] 中，看看哪个子串产生的回文子序列更长即可：
     *
     * @param s
     * @return
     */
    public static int longestPalindromeSubseq(String s) {
        if (s.length() == 0) return 0;
        int[][] dp = new int[s.length()][s.length()];

        // 反着遍历保证正确的状态转移
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // 它俩一定在最长回文子序列中
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // s[i+1..j] 和 s[i..j-1] 谁的回文子序列更长？
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][s.length() - 1];
    }

    public static int longestPalindromeSubseq2(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        char[] chars = s.toCharArray();

        for (int i = len - 1; i >= 0; i--) {//重要！
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][len - 1];
    }
}
