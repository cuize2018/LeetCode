package leet;

import java.util.Arrays;

public class Solution10_01 {
    public static void main(String[] args) {
        int[]A = {2,0};int m = 1;
        int[]B = {1};       int n = 1;

        merge(A,m,B,n);
        System.out.println(Arrays.toString(A));
    }

    public static void merge(int[] A, int m, int[] B, int n) {
        if (B.length == 0)return;
        int mov_a = 0;
        int mov_b = 0;
        int[] out = new int[m+n];
        int mov_out = 0;
        while (mov_out < m+n){
            int min;
            if (mov_b >= n || (mov_a < m && A[mov_a] < B[mov_b])){
                min = A[mov_a];
                mov_a++;
            }
            else {
                min = B[mov_b];
                mov_b++;
            }
            out[mov_out] = min;
            mov_out++;
        }
        if (m + n >= 0) System.arraycopy(out, 0, A, 0, m + n);
    }
}
