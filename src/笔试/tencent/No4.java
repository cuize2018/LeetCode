package 笔试.tencent;

import java.util.Scanner;

public class No4 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        int n = scanner.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextLong();
        }

        long res = func(0,n-1, a);
        System.out.println(res);

    }

    private static long func(long i, long j, long[] nums) {
        if (i == j)return 1;
        long min = Long.MAX_VALUE;
        for (int k = (int) i; k <= j; k++) {
            min = Math.min(min, nums[k]);
        }

        for (int k = (int) i; k <= j; k++) {
            nums[k] -= min;
        }
        long sum = 0;
        long temp = i;

        for (int k = (int) i; k <= j; k++) {
            if (nums[k] > 0 && (k == j || nums[k+1] == 0)){
                sum += func(temp, k, nums);
            }
            if (nums[k] == 0 && k < j && nums[k+1] > 0){
                temp = k+1;
            }
        }
        return Math.min(sum + min, j-i+1);
    }
}
