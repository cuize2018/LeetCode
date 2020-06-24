package nowcoder.bytedance;

import java.util.Arrays;
import java.util.Scanner;

public class nor {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        int res = func(n,m,nums);
        System.out.println(res);
    }

    private static int func(int n, int m, int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length-1;

        int idx = (left+right)>>>1;
        if ((nums[idx] ^ nums[idx+1]) >=m){
            while ((nums[idx] ^ nums[idx+1]) >=m) {
                idx--;
            }
        }
        else if ((nums[idx] ^ nums[idx+1]) <= m){
            while ((nums[idx] ^ nums[idx+1]) <= m) {
                idx++;
            }
        }
        else {
            while ((nums[idx] ^ nums[idx+1]) == m) {
                idx--;
            }
        }
        int t = nums.length - (idx+1);
        return t*(t-1)/2;

    }
}
