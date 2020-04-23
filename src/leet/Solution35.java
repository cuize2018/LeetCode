package leet;

public class Solution35 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int target = 7;

        System.out.println(searchInsert(nums, target));
    }

    public static int searchInsert(int[] nums, int target) {
        if (nums.length == 0) return 0;

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        if (target <= nums[left]) {
            return left;
        }
        return left + 1;
    }
}
