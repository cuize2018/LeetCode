package leet;

public class Solution279 {
    public static void main(String[] args) {
        System.out.println(numSquares2(13));
    }

    public static int numSquares(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1;i < n;i++){
            int num = i+1;
            int k = num;
            int one_min = Integer.MAX_VALUE;
            while (k >= 1){
                if (isSqrt(k)){
                    if (k == num){
                        one_min = 1;
                        break;
                    }
                    else{
                        one_min = Math.min(one_min, 1 + dp[i-k]);
                        if (one_min == 2)break;
                    }
                }
                k--;
            }
            dp[i] = one_min;
        }
        return dp[n-1];
    }

    public static boolean isSqrt(int n){
        int sqrt = (int) Math.sqrt(n);
        return  (sqrt * sqrt == n);
    }

    public static int numSquares2(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;dp[1] = 1;
        for (int i = 2;i <= n;i++){
            dp[i] = i;
            for (int j = 1;i - j*j >= 0;j++){
                dp[i] = Math.min(dp[i], dp[i-j*j]+1);
            }
        }
        return dp[n];
    }
}
