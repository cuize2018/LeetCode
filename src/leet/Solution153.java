package leet;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Arrays;

public class Solution153 {
    public static void main(String[] args){
        int[] a = {4,5,6};
        System.out.println(findMin(a));
    }

    public static int findMin(int[] nums) {
        if (nums.length == 0)return 0;
        if (nums.length == 1)return nums[0];
        if (nums.length == 2)return Math.min(nums[0], nums[1]);

        int out = 0;
        int low = 0;
        int high = nums.length-1;
        int mid = (low+high) >>> 1;

        if (nums[low] <= nums[mid] && nums[mid] > nums[high]){
            low = mid;
            int[] tmp = Arrays.copyOfRange(nums, low, high+1);
            out = findMin(tmp);
        }
        if (nums[low] > nums[mid] && nums[mid] <= nums[high]){
            high = mid;
            int[] tmp = Arrays.copyOfRange(nums, low, high+1);
            out = findMin(tmp);
        }
        if (nums[low] <= nums[mid] && nums[mid] < nums[high]){
            out = nums[low];
        }

        return out;
    }
}
