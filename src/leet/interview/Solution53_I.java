package leet.interview;

import java.util.Arrays;

public class Solution53_I {
    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        System.out.println(search3(nums, 6));
    }

    public static int search(int[] nums, int target) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0] == target ? 1 : 0;

        int left = 0;
        int right = nums.length-1;
        boolean found = false;
        int idx = 0;
        while (left <= right){
            int mid = (left+right)>>>1;
            if (nums[mid] == target){
                found = true;
                idx = mid;
                break;
            }

            if (nums[mid] > target){
                right = mid-1;
            }
            else {
                left = mid+1;
            }
        }


        if (found){
            for (left = idx; left >= 0; left--) {
                if (nums[left] != target)break;
            }
            for (right = idx; right < nums.length; right++) {
                if (nums[right] != target)break;
            }
            return right-left-1;
        }
        return 0;
    }

    public static int search3(int[] nums, int target) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0] == target ? 1 : 0;

        int idx = Arrays.binarySearch(nums, target);
        if (idx >= 0){
            int left,right;
            for (left = idx; left >= 0; left--) {
                if (nums[left] != target)break;
            }
            for (right = idx; right < nums.length; right++) {
                if (nums[right] != target)break;
            }
            return right-left-1;
        }
        return 0;
    }
}
