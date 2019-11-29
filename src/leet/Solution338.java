package leet;

import java.util.Arrays;

public class Solution338 {
    public static void main(String[] args) {
        int num = 13;
        System.out.println(Arrays.toString(countBits(num)));
    }

    /**
     * 规律如下：
     * 若n为奇数 -> dp[n] = dp[n-1] + 1
     * 若n为偶数 -> 若n为2的k次幂， 则dp[n] = 1
     *          -> 否则, dp[n] = dp[2^(k-1)] + dp[n-2^(k-1)]
     * exp: n = 12, 初始 k = 1
     * 当i = 8时, k = 3, dp[8] = 1, k = k+1 = 4
     * 当i = 12时, k = 4, dp[12] = dp[2^3] + dp[12-2^3] -> dp[12] = dp[8] + dp[4]
     * 12: 1100 -> 1000 + 0100 -> 8 + 4
     * @param num
     * @return
     */
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
