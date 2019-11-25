package leet;

import java.util.Arrays;

public class Solution238 {
    public static void main(String[] args){
        int[] a = {1,2,3,4};
        System.out.println(Arrays.toString(productExceptSelf(a)));
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        int[] out = new int[nums.length];

        left[0] = 1;
        right[nums.length-1] = 1;

        for (int i = 1;i<nums.length;i++){
            left[i] = left[i-1]*nums[i-1];
        }

        for (int i = nums.length-2;i >= 0;i--){
            right[i] = right[i+1]*nums[i+1];
        }

        for (int i = 0;i<nums.length;i++){
            out[i] = left[i]*right[i];
        }
        return out;
    }

}
