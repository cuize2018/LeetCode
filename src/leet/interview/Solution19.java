package leet.interview;

public class Solution19 {
    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b";
        System.out.println(isMatch(s, p));
    }

    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    if (s.charAt(i - 1) != p.charAt(j - 2) && p.charAt(j - 2) != '.') {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 1] || dp[i][j - 2] || dp[i - 1][j];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];

    }


    public static boolean isMatch2(String s, String p) {

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 1; j <= p.length(); j++) {
            dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }

                if (p.charAt(j - 1) == '*') {
                    char last = p.charAt(j - 2);
                    if (last == s.charAt(i - 1) || last == '.') {
                        dp[i][j] = dp[i - 1][j - 1] || dp[i][j - 2] || dp[i - 1][j];
                    }
                    else {
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
