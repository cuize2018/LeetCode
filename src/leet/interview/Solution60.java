package leet.interview;

import java.util.Arrays;

public class Solution60 {
    public static void main(String[] args) {
        int n = 2;
        double[] res = twoSum(n);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 单单看第 n 枚骰子，它的点数可能为 1 , 2, 3, ... , 6，
     * 因此投掷完 n 枚骰子后点数 j 出现的次数，
     * 可以由投掷完 n-1 枚骰子后，对应点数 j-1, j-2, j-3, ... , j-6 出现的次数之和转化过来。
     * <p>
     * for (第n枚骰子的点数 i = 1; i <= 6; i ++) {
     * dp[n][j] += dp[n-1][j - i]
     * }
     *
     * @param n
     * @return
     */
    public static double[] twoSum(int n) {
        //dp[i][j] ，表示投掷完 i 枚骰子后，点数 j 的出现次数。
        int[][] dp = new int[n + 1][6 * n + 1];

        for (int j = 1; j <= 6; j++) {
            dp[1][j] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= 6 * i; j++) {
                for (int currPoint = 1; currPoint <= 6; currPoint++) {
                    if (j - currPoint <= 0) break;
                    dp[i][j] += dp[i - 1][j - currPoint];
                }
            }
        }
        double all = Math.pow(6, n);//投掷 n 个骰子，所有点数出现的总次数是 6^n，
        //因为一共有 n 枚骰子，每枚骰子的点数都有 6 种可能出现的情况。
        double[] res = new double[6 * n - (n - 1)];
        int k = 0;
        for (int j = n; j <= 6 * n; j++) {
            res[k++] = dp[n][j] / all;
        }
        return res;
    }


    public static double[] twoSum2(int n) {
        //dp[i][j] ，表示投掷完 i 枚骰子后，点数 j 的出现次数。
        int[] dp = new int[6 * n + 1];
        for (int j = 1; j <= 6; j++) {
            dp[j] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 6 * i; j >= i; j--) {
                dp[j] = 0;
                for (int currPoint = 1; currPoint <= 6; currPoint++) {
                    if (j - currPoint < i - 1) break;
                    dp[j] += dp[j - currPoint];
                }
            }
        }
        double all = Math.pow(6, n);//投掷 n 个骰子，所有点数出现的总次数是 6^n，
        //因为一共有 n 枚骰子，每枚骰子的点数都有 6 种可能出现的情况。
        double[] res = new double[6 * n - (n - 1)];
        int k = 0;
        for (int j = n; j <= 6 * n; j++) {
            res[k++] = dp[j] / all;
        }
        return res;
    }


}
