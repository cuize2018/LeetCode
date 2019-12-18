package leet;

public class Solution413 {
    public static void main(String[] args) {
        int[] A = {1,2,3,4,5,6};
        int[] b = {1,2,3,8,9,10};
        System.out.println(numberOfArithmeticSlices(A));
    }

    public static int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        if (n <= 2)return 0;

        int[] dp = new int[n];//dp[i]为以第i个元素结尾的等差子数组的个数
        dp[0] = 0;dp[1] = 0;

        for (int i = 2;i < n;i++){
            if (dp[i-1] != 0){
                if ((A[i] - A[i-1]) == (A[i-1]-A[i-2])){
                    dp[i] = dp[i-1] + i-1;
                }
                else {
                    dp[i] = 0;
                }
            }
            else {
                if ((A[i] - A[i-1]) == (A[i-1]-A[i-2])){
                    dp[i] = 1;
                }
                else {
                    dp[i] = 0;
                }
            }
        }

        int sum = 0;
        for (int i = 2;i<n;i++){
             if (i==(n-1) || (dp[i] != 0 && dp[i+1] == 0)){
                 sum += dp[i];
             }
        }
        return sum;
        //return dp[n-1];
    }
}
