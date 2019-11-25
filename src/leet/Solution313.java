package leet;

public class Solution313 {
    public static void main(String[] args) {
        int n = 2;
        int[] a = {2,3,5};
        System.out.println(nthSuperUglyNumber(n,a));
    }

    public static int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1)return 1;

        int[] dp = new int[n];dp[0] = 1;
        int[] prime_idx = new int[primes.length];

        for (int i = 1;i<n;i++){
            int min_dp = primes[0]*dp[prime_idx[0]];
            for (int j = 1;j<primes.length;j++){
                int tmp = primes[j]*dp[prime_idx[j]];
                min_dp = Math.min(min_dp, tmp);
            }

            for (int j = 0;j<primes.length;j++){
                if (min_dp == primes[j]*dp[prime_idx[j]]){
                    prime_idx[j]++;
                }
            }
            dp[i] = min_dp;
        }
        return dp[n-1];
    }
}
