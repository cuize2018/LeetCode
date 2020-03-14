package leet;

import java.util.*;

public class Solution1035 {
    public static void main(String[] args) {
        int[] a = {1,1,2,1,2};
        int[] b = {1,3,2,3,1};
        System.out.println(maxUncrossedLines(a,b));

    }

    //最长公共子序列
    public static int maxUncrossedLines(int[] A, int[] B) {
        //dp[i][j]表示A的[1,i]的范围和B的[1,j]的范围中最长公共子序列的长度
        int[][] dp = new int[A.length+1][B.length+1];

        // 状态转移说简单些就是做选择，比如说这个问题，是求 s1 和 s2 的最长公共子序列，不妨称这个子序列为 lcs。
        // 那么对于 s1 和 s2 中的每个字符，有什么选择？很简单，两种选择，要么在 lcs 中，要么不在。
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                // 用两个指针 i 和 j 从后往前遍历 s1 和 s2，
                // 如果 s1[i]==s2[j]，那么这个字符一定在 lcs 中；
                // 否则的话，s1[i] 和 s2[j] 这两个字符至少有一个不在 lcs 中，需要丢弃一个。
                if (A[i-1] == B[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[A.length][B.length];
    }
}
