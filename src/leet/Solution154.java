package leet;

public class Solution154 {
    public int findMin(int[] nums) {
        if (nums.length  == 0)return 0;
        if (nums.length  == 1)return nums[0];

        int left = 0;
        int right = nums.length-1;

        while (left < right){
            int middle = (left+right)>>>1;
            if (nums[middle] < nums[right]){
                right = middle;
            }
            else if (nums[middle] > nums[right]){
                left = middle + 1;
            }
            else {
                right--;
            }
        }
        return nums[left];
    }
}
