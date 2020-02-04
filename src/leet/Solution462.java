package leet;

import java.util.Arrays;

public class Solution462 {
    public static void main(String[] args) {
        int[] a = {1,2,3};
        System.out.println(minMoves2(a));
    }

    public static int minMoves2(int[] nums) {

        Arrays.sort(nums);
        int middle = nums[nums.length/2];

        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(num - middle);
        }

        return sum;

    }
}
