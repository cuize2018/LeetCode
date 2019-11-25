package leet;

import java.util.Arrays;

public class Solution75 {
    public static void main(String[] args){
        int[] nums = {2,0,2,1,1,0};
        int[] nums2 ={2,0,1};
        sortColors2(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sortColors(int[] nums) {
        int left = 0;
        int curr = 0;
        int right = nums.length-1;

        while (curr <= right && curr >= left){
            if (nums[curr] == 2){
                int tmp = nums[curr];
                nums[curr] = nums[right];
                nums[right] = tmp;
                right--;

            }
            else if (nums[curr] == 0){
                int tmp = nums[left];
                nums[left] = nums[curr];
                nums[curr] = tmp;
                left++;
                curr++;
            }
            else {
                curr++;
            }
        }
    }

    public static void sortColors2(int[] nums) {
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        for (int i = 0;i<nums.length;i++){
            if (nums[i] == 0)num1++;
            else if (nums[i] == 1)num2++;
            else num3++;
        }

        for (int i = 0;i<num1;i++){
            nums[i] = 0;
        }
        for (int i = num1;i<num1+num2;i++){
            nums[i] = 1;
        }
        for (int i = num1+num2;i<num1+num2+num3;i++){
            nums[i] = 2;
        }
    }

}
