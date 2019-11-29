package leet;

import java.util.Arrays;

public class Solution338 {
    public static void main(String[] args) {
        int num = 13;
        System.out.println(Arrays.toString(countBits(num)));
    }

    public static int[] countBits(int num) {
        int[] dp = new int[num+1];
        dp[0] = 0;
        if (num == 0)return dp;
        dp[1] = 1;

        int k = 0;
        for (int i = 2;i <= num;i++){
            if (i%2 != 0){
                dp[i] = dp[i-1] + 1;
            }
            else {
                int up = 2<<k;
                if (i == up){
                    dp[i] = 1;
                    k++;
                }
                else {
                    int max = 2<<(k-1);
                    dp[i] = dp[max] + dp[i-max];
                }
            }
        }
        return dp;
    }
}
