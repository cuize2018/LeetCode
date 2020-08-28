package 笔试.didi;

import java.util.Scanner;

public class No2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n <= 1){
            System.out.println(n);
            return;
        }

        long[] dp = new long[n*n];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i-1]+ dp[i-2];
        }

        long[][] mat = new long[n][n];
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        int up = 0;
        int down = n-1;
        int left = 0;
        int right = n-1;

        int i = 0;
        int j = 0;
        int p = dp.length-1;

        while (p >= 0){
            mat[i][j] = dp[p];
            j++;
            p--;
            for (int m = 0;m < 4;m++) {
                int[] dir = dirs[m];
                while (i >= up && i<= down && j>= left && j<= right) {
                    if (i == up && j == left)break;

                    mat[i][j] = dp[p];
                    i += dir[0];
                    j += dir[1];
                    p--;
                    if (p < 0)break;
                }
                if (p < 0)break;

                i = Math.max(up, Math.min(i,down));
                j = Math.max(left, Math.min(j,right));
                if (m < 3){
                    i += dirs[m+1][0];
                    j += dirs[m+1][1];
                }
            }
            up++;
            down--;
            left++;
            right--;

            i = up;
            j = left;
        }

        for (int k = 0; k < n; k++) {
            for (int l = 0; l < n-1; l++) {
                System.out.print(mat[k][l] + " ");
            }
            System.out.println(mat[k][n-1]);
        }
    }
}
