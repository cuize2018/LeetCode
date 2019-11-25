package leet;

public class Solution81 {
    public static void main(String[] args){
        int[] nums = {2,5,6,0,0,1,2};
        int target = 0;
        System.out.println(search(nums,target));
    }

    public static boolean search(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        if (nums.length == 1){
            return nums[0]==target;
        }

        while (low <= high){
            int middle = (high+low)>>>1;
            if (nums[middle] == target) return true;

            if (nums[low] < nums[middle]){
                if (target >= nums[low] && target < nums[middle]){
                    high = middle - 1;
                }
                else {
                    low = middle + 1;
                }
            }
            else if (nums[low] == nums[middle]){
                low++;
            }
            else {
                if (target > nums[middle] && target <= nums[high]){
                    low = middle + 1;
                }
                else {
                    high = middle - 1;
                }
            }
        }
        return false;
    }
}
