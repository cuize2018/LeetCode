package leet;

public class Solution903 {
    public static void main(String[] args) {

    }

    public static int numPermsDISequence(String S) {
        int n = S.length();
        int mod = (int) 1e9 + 7;

        int[][] dp = new int[n + 1][n + 1];

        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (S.charAt(i - 1) == 'D') {
                    //若为递减，则新添加的数字j必须小于等于[0,i-1]的最后一个数字k
                    for (int k = j; k <= i - 1; k++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][k]) % mod;
                    }
                } else {
                    //若为递增，则新添加的数字j必须大于[0,i-1]的最后一个数字k
                    for (int k = 0; k <= j - 1; k++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][k]) % mod;
                    }
                }
            }
        }
        int sum = 0;
        for (int j = 0; j <= n; j++) {
            sum = (sum + dp[n][j]) % mod;
        }
        return sum;
    }
}
