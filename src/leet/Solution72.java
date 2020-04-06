package leet;

import java.util.Map;

//编辑距离
public class Solution72 {
    public static void main(String[] args) {
        String a = "horse";
        String ros = "ros";
        System.out.println(minDistance2(a, ros));
    }

    /**
     * dp[i][j] 代表 word1 到 i 位置转换成 word2 到 j 位置需要最少步数
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        if (word1.length() == 0 || word2.length() == 0) {
            return word1.length() + word2.length();
        }
        //dp[i][j]代表word1的前i个字符变为word2的前j个字符的最短距离
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = j;
        }

        //dp[i][j]有三种得到的方法:
        //  1.一个单词的前i-1位变成第二个单词的前j位，然后再删去一个字符（d[i-1][j]+1）--> 第一个单词前i位先删除一个字符（+1），变为前i-1个位，在变换得到第二个单词的j位(dp[i-1][j])
        //  2.第一个单词的前i位变成第二个单词的前j-1位，然后再插入一个字符（d[i][j-1]+1）
        //  3.第一个单词的前i-1位变成第二个单词的前j-1位，然后替换最后一个字符，
        //      如果最后一个字符相同，即第一个单词的第i位和第二个单词的第j位相同的话，就不用替换了（d[i-1][j-1]）；
        //      反之，如果不同就替换最后一位（d[i-1][j-1]+1）
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static int minDistance2(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }


}
