package leet;

public class Solution357{
    
    public static void main(String[] args) {
        System.out.println(countNumbersWithUniqueDigits(2));
    }
    /**
     * 写下前几个，就能看出规律了。
        n=1: res=10
        n=2: res=9*9+10=91 # 两位数第一位只能为1-9，第二位只能为非第一位的数，加上一位数的所有结果
        n=3: res=9*9*8+91=739 # 三位数第一位只能为1-9，第二位只能为非第一位的数，第三位只能为非前两位的数，加上两位数的所有结果
        n=4: res=9*9*8*7+739=5275 # 同上推法

     * @param n
     * @return
     */
    public static int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }

        int[] dp = new int[10+1];
        dp[0] = 1;
        dp[1] = 10;
        for (int i = 2; i < dp.length; i++) {
            int tmp = 9;
            int k = 9;
            for (int j = 0; j < i-1; j++) {
                tmp = tmp*k;
                k--;
            }
            dp[i] = tmp + dp[i-1];
        }
        if (n < 10) {
            return dp[n];
        }
        else return dp[10];
        
    }

}