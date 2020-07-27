package leet;

public class Solution392 {
    public static void main(String[] args) {

    }

    public static boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;

        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }

    /**
     * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
     * 我们需要在匹配前对 T 做预处理，利用一个二维数组记录每个位置的下一个要匹配的字符的位置，
     * 这里的字符是'a' ~ 'z'，所以这个数组的大小是 dp[n][26]，n 为 T 的长度。
     * 那么每处理一个子串只需要扫描一遍 Si 即可，因为在数组的帮助下我们对 T 是“跳跃”扫描的。比如下面匹配 "ada" 的例子，只需要“跳跃”三次。
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubsequence2(String s, String t) {
        t = " " + t;
        int n = t.length();
        int[][] dp = new int[n][26];

        for (int ch = 0; ch < 26; ch++) {
            int p = -1;
            for (int i = n - 1; i >= 0; i--) {
                dp[i][ch] = p;
                if (t.charAt(i) == ch + 'a') {
                    p = i;
                }
            }
        }
        int i = 0;
        for (char c : s.toCharArray()) {
            i = dp[i][c - 'a'];
            if (i == -1) return false;
        }
        return true;
    }
}
