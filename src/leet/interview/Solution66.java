package leet.interview;

import java.util.Arrays;

public class Solution66 {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int[] res = constructArr(a);
        System.out.println(Arrays.toString(res));
    }

    public static int[] constructArr(int[] a) {
        if (a.length == 0) return new int[0];

        int[] dp = new int[a.length];
        dp[a.length - 1] = 1;

        for (int i = a.length - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] * a[i + 1];
        }

        int LeftProduct = a[0];
        for (int i = 1; i < a.length; i++) {
            dp[i] *= LeftProduct;
            LeftProduct *= a[i];
        }
        return dp;
    }
}
