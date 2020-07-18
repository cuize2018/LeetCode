package leet;

public class Solution97 {
    public static void main(String[] args) {
        String s1 = "aabc";
        String s2 = "abad";
        String s3 = "aabcabad";
        System.out.println(isInterleave(s1, s2, s3));
    }

    /**
     * 我们使用dp[i][j]表示s1的前i个字符和s2的前j个字符是否能构成s3的前i+j个字符。
     * dp[i][j]=(dp[i][j-1] and s2[j-1]==s3[i+j-1]) or (dp[i-1][j] and s1[i-1]==s3[i+j-1])
     * 解释：s1前i位和s2的前j位能否组成s3的前i+j位取决于两种情况：
     * s1的前i个字符和s2的前j-1个字符能否构成s3的前i+j-1位，且s2的第j位（s2[j-1]）是否等于s3的第i+j位（s3[i+j-1]）
     * s2的前j个字符和s1的前i-1个字符能否构成s3的前i+j-1位，且s1的第i位（s1[i-1]）是否等于s3的第i+j位（s3[i+j-1]）
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];

        dp[0][0] = true;
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                        || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public static boolean isInterleave2(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())return false;

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for (int j = 1; j <= s2.length(); j++) {
            dp[0][j] = s2.charAt(j - 1) == s3.charAt(j - 1);
            if (!dp[0][j])break;
        }

        for (int i = 1; i <= s1.length(); i++) {
            dp[i][0] = s1.charAt(i - 1) == s3.charAt(i - 1);
            if (!dp[i][0])break;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                boolean a = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                boolean b = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                dp[i][j] = a || b;
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
