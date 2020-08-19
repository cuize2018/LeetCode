package leet;

public class Solution647 {
    public static void main(String[] args) {
        String s = "aaa";
        System.out.println(countSubstrings(s));
    }

    /**
     * 使用动态规划， dp[i][j] 代表str[i] - str[j]是否是回文子串
     * 考虑单字符和双字符的特殊情况
     * 状态转移方程：dp[i][j] = dp[i+1][j-1] && str[i]==str[j]
     *
     * @param s
     * @return
     */
    public static int countSubstrings(String s) {
        if (s.length() == 0) return 0;
        int count = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int j = 0; j < s.length(); j++) {
            for (int i = j; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }

        return count;
    }

    public static int countSubstrings3(String s) {
        int n = s.length();
        if (n == 0) return 0;
        boolean[][] dp = new boolean[n][n];
        int cnt = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }

}
