package leet;

import java.util.Arrays;

public class Solution162 {
    public static void main(String[] args){
        int[] a = {3,2,1};
        System.out.println(findPeakElement(a));
    }

    public static int findPeakElement(int[] nums) {
        if (nums.length == 1)return 0;

        int low = 0;
        int high = nums.length-1;
        int mid = (low+high)>>>1;
        int out_idx = -2;

        if (mid != 0 && mid != nums.length-1) {
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                out_idx = mid;
            }
            else {
                int[] left = Arrays.copyOfRange(nums, low, mid+1);
                int[] right = Arrays.copyOfRange(nums, mid, high+1);

                out_idx = findPeakElement(right);
                out_idx = mid+out_idx;
                if (out_idx != mid) {
                    return out_idx;
                }

                out_idx = findPeakElement(left);
                if (out_idx != low) {
                    return out_idx;
                }
            }
        }
        else if (mid == 0){
            if (nums[mid] > nums[1]) {
                out_idx = mid;
            }
            else if (nums[mid+1] > nums[nums.length-2]) {
                out_idx = mid+1;
            }
        }
        return out_idx;
    }
}
