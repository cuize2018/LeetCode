package leet;

public class Solution712 {
    public static void main(String[] args) {

    }

    public static int minimumDeleteSum(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int len1 = s1.length();
        int len2 = s2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int j = 1; j <= len2; j++) {
            dp[0][j] = dp[0][j - 1] + c2[j - 1];
        }
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = dp[i - 1][0] + c1[i - 1];
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (c1[i - 1] == c2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int deleteBoth = dp[i - 1][j - 1] + c1[i - 1] + c2[j - 1];
                    int deleteS1 = dp[i - 1][j] + c1[i - 1];
                    int deleteS2 = dp[i][j - 1] + c2[j - 1];
                    dp[i][j] = Math.min(deleteBoth, Math.min(deleteS1, deleteS2));
                }
            }
        }
        return dp[len1][len2];
    }
}
